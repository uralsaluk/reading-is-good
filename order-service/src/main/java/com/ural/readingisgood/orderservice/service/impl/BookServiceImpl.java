package com.ural.readingisgood.orderservice.service.impl;

import com.ural.readingisgood.orderservice.entity.BookEntity;
import com.ural.readingisgood.orderservice.entity.InventoryEntity;
import com.ural.readingisgood.orderservice.exception.BusinessException;
import com.ural.readingisgood.orderservice.exception.ErrorCode;
import com.ural.readingisgood.orderservice.repository.BookRepository;
import com.ural.readingisgood.orderservice.repository.InventoryRepository;
import com.ural.readingisgood.orderservice.repository.InventoryRepositoryImpl;
import com.ural.readingisgood.orderservice.service.contract.BookService;
import com.ural.readingisgood.orderservice.service.mapper.BookServiceMapper;
import com.ural.readingisgood.orderservice.service.model.bookservice.BookDTO;
import com.ural.readingisgood.orderservice.service.model.bookservice.request.UpdateBookInventoryRequestDTO;
import com.ural.readingisgood.orderservice.service.model.bookservice.response.UpdateBookInventoryResponseDTO;
import com.ural.readingisgood.orderservice.util.ContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);


    private Integer testCounter = 0;

    private BookRepository bookRepository;
    private InventoryRepository inventoryRepository;
    private InventoryRepositoryImpl customInventoryRepo;
    private static final BookServiceMapper mapper = BookServiceMapper.INSTANCE;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, InventoryRepository inventoryRepository, InventoryRepositoryImpl customInventoryRepo) {
        this.bookRepository = bookRepository;
        this.inventoryRepository = inventoryRepository;
        this.customInventoryRepo = customInventoryRepo;
    }

    @Override
    @Transactional
    public BookDTO createBook(BookDTO request) {

        if (checkBookExist(request.getName(), request.getAuthor())) {

            throw new BusinessException(ErrorCode.BOOK_EXIST);

        }

        BookEntity bookEntity = mapper.toBookEntity(request);


        BookEntity returnEntity = bookRepository.save(bookEntity);

        LOGGER.info("{} created book {} ", ContextUtil.getUserId(), bookEntity);


        return mapper.toBookDTO(returnEntity);

    }

    @Override
    public BookDTO getBookByName(String name) {

        BookEntity bookEntity = bookRepository.findByName(name);

        return mapper.toBookDTO(bookEntity);
    }

    @Override
    @Transactional
    public UpdateBookInventoryResponseDTO updateBookInventory(UpdateBookInventoryRequestDTO request) {


        InventoryEntity one = inventoryRepository.findById(request.getId())
                .orElseThrow(() -> new BusinessException(ErrorCode.ITEM_NOT_FOUND));

        one.setStock(request.getStock());

        InventoryEntity updatedEntity = inventoryRepository.save(one);

        LOGGER.info("{} updated book {} ", ContextUtil.getUserId(), updatedEntity);


        return mapper.toUpdateBookInventoryResponseDTO(updatedEntity);

    }

    public boolean checkBookExist(String name, String author) {

        return bookRepository.existsByNameAndAuthor(name, author);
    }
}
