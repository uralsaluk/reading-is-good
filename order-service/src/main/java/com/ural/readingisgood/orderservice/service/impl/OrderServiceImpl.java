package com.ural.readingisgood.orderservice.service.impl;


import com.ural.readingisgood.orderservice.entity.BookEntity;
import com.ural.readingisgood.orderservice.entity.OrderHeaderEntity;
import com.ural.readingisgood.orderservice.entity.OrderLineEntity;
import com.ural.readingisgood.orderservice.entity.ReservedInventory;
import com.ural.readingisgood.orderservice.entity.constant.LineStatus;
import com.ural.readingisgood.orderservice.entity.constant.OrderStatus;
import com.ural.readingisgood.orderservice.event.OrderCreatedEvent;
import com.ural.readingisgood.orderservice.exception.BusinessException;
import com.ural.readingisgood.orderservice.exception.ErrorCode;
import com.ural.readingisgood.orderservice.repository.BookRepository;
import com.ural.readingisgood.orderservice.repository.OrderRepository;
import com.ural.readingisgood.orderservice.service.contract.InventoryService;
import com.ural.readingisgood.orderservice.service.contract.OrderService;
import com.ural.readingisgood.orderservice.service.contract.PaymentService;
import com.ural.readingisgood.orderservice.service.mapper.OrderServiceMapper;
import com.ural.readingisgood.orderservice.service.model.orderservice.request.CreateOrderRequestDTO;
import com.ural.readingisgood.orderservice.service.model.orderservice.request.GetOrdersByUserIdRequestDTO;
import com.ural.readingisgood.orderservice.service.model.orderservice.request.OrderedProductDTO;
import com.ural.readingisgood.orderservice.service.model.orderservice.response.CreateOrderResponseDTO;
import com.ural.readingisgood.orderservice.service.model.orderservice.response.GetOrderByQueryResponseDTO;
import com.ural.readingisgood.orderservice.service.model.orderservice.response.GetOrderResponseDTO;
import com.ural.readingisgood.orderservice.service.model.orderservice.response.GetOrdersByUserIdResponseDTO;
import com.ural.readingisgood.orderservice.util.ContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    private final static OrderServiceMapper mapper = OrderServiceMapper.INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);


    private BookRepository bookRepository;

    private InventoryService inventoryService;
    private OrderRepository orderRepository;

    private PaymentService paymentService;

    private ApplicationEventPublisher eventPublisher;

    @Autowired
    public OrderServiceImpl(BookRepository bookRepository,
                            ApplicationEventPublisher eventPublisher,
                            InventoryService inventoryService,
                            OrderRepository orderRepository,
                            PaymentService paymentService) {
        this.bookRepository = bookRepository;
        this.eventPublisher = eventPublisher;
        this.inventoryService = inventoryService;
        this.orderRepository = orderRepository;
        this.paymentService = paymentService;
    }

    @Override
    @Transactional(readOnly = true)
    public GetOrderResponseDTO getById(Long id) {
        OrderHeaderEntity orderHeaderEntity = orderRepository.
                findById(id).orElseThrow(() -> new BusinessException(ErrorCode.ORDER_NOT_FOUND));


        return mapper.toGetOrderResponseDTO(orderHeaderEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public GetOrderByQueryResponseDTO getByQuery(Date startDate, Date endDate) {

        List<OrderHeaderEntity> orderHeaderEntityList = orderRepository.findByCreateDateBetween(startDate, endDate);

        List<GetOrderResponseDTO> orderResponseList = mapper.toGetOrderResponseDtoList(orderHeaderEntityList);

        return new GetOrderByQueryResponseDTO(orderResponseList);


    }

    @Override
    public GetOrdersByUserIdResponseDTO getOrdersByUserId(GetOrdersByUserIdRequestDTO requestDTO) {
        Pageable pageable = PageRequest.of(requestDTO.getOffset(), requestDTO.getLimit());

        Page<OrderHeaderEntity> allByUsername = orderRepository.findAllByUserName(requestDTO.getUserName(), pageable);


        List<GetOrderResponseDTO> orderResponseList = mapper.toGetOrderResponseDtoList(allByUsername.getContent());

        return new GetOrdersByUserIdResponseDTO(orderResponseList);
    }

    @Override
    public CreateOrderResponseDTO createOrder(@Valid CreateOrderRequestDTO requestDTO) {


        LOGGER.info("{} started create order process for {}", ContextUtil.getUserId(), requestDTO);

        Set<OrderedProductDTO> productSet = transformListToSet(requestDTO.getOrderedProductList());


        ReservedInventory reservedInventory = inventoryService.reserveInventory(productSet);

        // DUMMY FUNC FOR DESIGN

        String paymentId = paymentService.exchange(reservedInventory.getId());


        CreateOrderResponseDTO responseDTO = createOrder(requestDTO, productSet, paymentId, reservedInventory.getId());

        eventPublisher.publishEvent(new OrderCreatedEvent(ContextUtil.getContextStrategy(), reservedInventory.getId()));

        LOGGER.info("{} created order successfully {}", ContextUtil.getUserId(), responseDTO);

        return responseDTO;

    }


    @Transactional
    public CreateOrderResponseDTO createOrder(CreateOrderRequestDTO requestDTO,
                                              Set<OrderedProductDTO> productSet,
                                              String paymentId,
                                              String reservedStockId) {

        double totalPrice = 0;


        List<OrderLineEntity> orderLineEntityList = new ArrayList<>();

        OrderHeaderEntity orderHeaderEntity = new OrderHeaderEntity();
        orderHeaderEntity.setUserName(ContextUtil.getUserId());
        orderHeaderEntity.setEmail(requestDTO.getMail());
        orderHeaderEntity.setStatus(OrderStatus.CREATED);
        orderHeaderEntity.setPaymentId(paymentId);
        orderHeaderEntity.setReservedStockId(reservedStockId);

        for (OrderedProductDTO orderedProductDTO : productSet) {
            BookEntity bookEntity = bookRepository.findById(orderedProductDTO.getProductId()).get();
            OrderLineEntity orderLineEntity = new OrderLineEntity();
            orderLineEntity.setBookId(bookEntity.getId());
            orderLineEntity.setBookName(bookEntity.getName());
            orderLineEntity.setQuantity(orderedProductDTO.getQuantity());
            orderLineEntity.setPrice(bookEntity.getPrice() * orderedProductDTO.getQuantity());
            orderLineEntity.setOrder(orderHeaderEntity);
            orderLineEntity.setStatus(LineStatus.CREATED);
            orderLineEntityList.add(orderLineEntity);
            totalPrice += (bookEntity.getPrice() * orderedProductDTO.getQuantity());
        }

        orderHeaderEntity.setItems(orderLineEntityList);
        orderHeaderEntity.setTotalPrice(totalPrice);


        OrderHeaderEntity createdEntity = orderRepository.save(orderHeaderEntity);


        return mapper.toCreateOrderResponseDTO(createdEntity);


    }

    private Set<OrderedProductDTO> transformListToSet(List<OrderedProductDTO> orderedProductList) {


        HashMap<Long, Integer> items = new HashMap<>();

        orderedProductList.forEach((product) -> {

            if (items.containsKey(product.getProductId())) {
                Integer mappedQuantity = items.get(product.getProductId());
                items.put(product.getProductId(), mappedQuantity + product.getQuantity());
            } else {
                items.put(product.getProductId(), product.getQuantity());
            }

        });

        Set<OrderedProductDTO> productSet = new HashSet<>();


        items.forEach((id, quantity) -> {

            productSet.add(new OrderedProductDTO(id, quantity));

        });

        return productSet;

    }
}
