package com.ural.readingisgood.orderservice.controller.impl;

import com.ural.readingisgood.orderservice.controller.contract.BookController;
import com.ural.readingisgood.orderservice.controller.mapper.BookControllerMapper;
import com.ural.readingisgood.orderservice.controller.model.request.CreateBookControllerRequest;
import com.ural.readingisgood.orderservice.controller.model.request.UpdateInventoryControllerRequest;
import com.ural.readingisgood.orderservice.controller.model.response.CreateBookControllerResponse;
import com.ural.readingisgood.orderservice.controller.model.response.GetBookControllerResponse;
import com.ural.readingisgood.orderservice.controller.model.response.UpdateInventoryControllerResponse;
import com.ural.readingisgood.orderservice.exception.BusinessException;
import com.ural.readingisgood.orderservice.exception.ErrorCode;
import com.ural.readingisgood.orderservice.service.contract.BookService;
import com.ural.readingisgood.orderservice.service.model.bookservice.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

@RestController
public class BookControllerImpl implements BookController {


    private static final BookControllerMapper mapper = BookControllerMapper.INSTANCE;


    private BookService bookService;

    @Autowired
    public BookControllerImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public ResponseEntity<CreateBookControllerResponse> createBook(@Valid CreateBookControllerRequest request) {

        BookDTO book = bookService.createBook(mapper.toBookDTO(request));


        CreateBookControllerResponse createBookControllerResponse = mapper.toCreateBookControllerResponse(book);

        return new ResponseEntity<>(createBookControllerResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UpdateInventoryControllerResponse> updateInventory(Long id, UpdateInventoryControllerRequest request) {

        UpdateInventoryControllerResponse responseController = mapper.toUpdateInventoryControllerResponse(
                bookService.updateBookInventory(mapper.toUpdateBookInventoryRequestDTO(id, request)));

        return new ResponseEntity<>(responseController, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GetBookControllerResponse> getBook(String name) {


        BookDTO book = bookService.getBookByName(name);

        if (Objects.isNull(book)) {

            throw new BusinessException(ErrorCode.ITEM_NOT_FOUND);
        }


        GetBookControllerResponse getBookControllerResponse = mapper.toGetBookControllerResponse(book);

        return new ResponseEntity<>(getBookControllerResponse, HttpStatus.OK);
    }
}
