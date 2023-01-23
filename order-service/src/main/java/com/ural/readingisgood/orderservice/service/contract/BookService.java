package com.ural.readingisgood.orderservice.service.contract;


import com.ural.readingisgood.orderservice.service.model.bookservice.BookDTO;
import com.ural.readingisgood.orderservice.service.model.bookservice.request.UpdateBookInventoryRequestDTO;
import com.ural.readingisgood.orderservice.service.model.bookservice.response.UpdateBookInventoryResponseDTO;

public interface BookService {

    BookDTO createBook(BookDTO request);

    BookDTO getBookByName(String name);

    UpdateBookInventoryResponseDTO updateBookInventory(UpdateBookInventoryRequestDTO request);
}
