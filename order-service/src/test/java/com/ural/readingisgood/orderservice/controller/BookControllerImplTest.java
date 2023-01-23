package com.ural.readingisgood.orderservice.controller;


import com.ural.readingisgood.orderservice.TestProps;
import com.ural.readingisgood.orderservice.controller.impl.BookControllerImpl;
import com.ural.readingisgood.orderservice.controller.model.request.CreateBookControllerRequest;
import com.ural.readingisgood.orderservice.controller.model.request.UpdateInventoryControllerRequest;
import com.ural.readingisgood.orderservice.controller.model.response.CreateBookControllerResponse;
import com.ural.readingisgood.orderservice.controller.model.response.GetBookControllerResponse;
import com.ural.readingisgood.orderservice.controller.model.response.UpdateInventoryControllerResponse;
import com.ural.readingisgood.orderservice.exception.BusinessException;
import com.ural.readingisgood.orderservice.service.contract.BookService;
import com.ural.readingisgood.orderservice.service.model.bookservice.BookDTO;
import com.ural.readingisgood.orderservice.service.model.bookservice.request.UpdateBookInventoryRequestDTO;
import com.ural.readingisgood.orderservice.service.model.bookservice.response.UpdateBookInventoryResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;


public class BookControllerImplTest {

    @Mock
    private BookService bookService;


    @InjectMocks
    BookControllerImpl bookController;

    private BookDTO bookDto;


    @BeforeEach
    public void init() {
        // mock(BookControllerImplTest.class);

        MockitoAnnotations.openMocks(this);

        bookDto = BookDTO.builder()
                .name(TestProps.TEST_STRING)
                .author(TestProps.TEST_STRING)
                .stock(TestProps.TEST_INTEGER).build();

    }

    @Test
    public void createBookTest() {

        CreateBookControllerRequest createBookControllerRequest = new CreateBookControllerRequest();

        createBookControllerRequest.setName(TestProps.TEST_STRING);
        createBookControllerRequest.setAuthor(TestProps.TEST_STRING);
        createBookControllerRequest.setStock(TestProps.TEST_INTEGER);


        when(bookService.createBook(bookDto)).thenReturn(bookDto);

        ResponseEntity<CreateBookControllerResponse> response = bookController.createBook(createBookControllerRequest);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(TestProps.TEST_STRING, response.getBody().getName());
        Assertions.assertEquals(TestProps.TEST_STRING, response.getBody().getAuthor());
        Assertions.assertEquals(TestProps.TEST_INTEGER, response.getBody().getStock());

    }

    @Test
    public void updateInventoryTest() {

        UpdateBookInventoryRequestDTO updateBookInventoryRequestDTO = new UpdateBookInventoryRequestDTO();
        updateBookInventoryRequestDTO.setId(TestProps.TEST_LONG);
        updateBookInventoryRequestDTO.setStock(TestProps.TEST_INTEGER);

        UpdateInventoryControllerRequest updateInventoryControllerRequest = new UpdateInventoryControllerRequest();
        updateInventoryControllerRequest.setStock(TestProps.TEST_INTEGER);

        UpdateBookInventoryResponseDTO updateBookInventoryResponseDTO = new UpdateBookInventoryResponseDTO();
        updateBookInventoryResponseDTO.setId(TestProps.TEST_LONG);
        updateBookInventoryResponseDTO.setStock(TestProps.TEST_INTEGER);

        when(bookService.updateBookInventory(updateBookInventoryRequestDTO)).thenReturn(updateBookInventoryResponseDTO);


        ResponseEntity<UpdateInventoryControllerResponse> response = bookController
                .updateInventory(TestProps.TEST_LONG, updateInventoryControllerRequest);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(TestProps.TEST_LONG, response.getBody().getId());
        Assertions.assertEquals(TestProps.TEST_INTEGER, response.getBody().getStock());
    }


    @Test
    public void getBookTest() {


        when(bookService.getBookByName(TestProps.TEST_STRING)).thenReturn(bookDto);


        ResponseEntity<GetBookControllerResponse> response = bookController
                .getBook(TestProps.TEST_STRING);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(TestProps.TEST_STRING, response.getBody().getName());
        Assertions.assertEquals(TestProps.TEST_INTEGER, response.getBody().getStock());

    }

    @Test
    public void getBookExceptionTest() {


        when(bookService.getBookByName(TestProps.TEST_STRING)).thenReturn(null);

        Assertions.assertThrows(BusinessException.class, () -> bookController.getBook(TestProps.TEST_STRING));


    }
}
