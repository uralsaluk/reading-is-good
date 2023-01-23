package com.ural.readingisgood.orderservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    BOOK_EXIST("B001", "Book is already exist"),
    ITEM_NOT_FOUND("I001", "Item not found"),
    NOT_ENOUGH_STOCK("I002", "Not Enough Stock"),

    PAYMENT_ERROR("P001", "Payment Error"),
    ORDER_NOT_FOUND("O001", "Order not found.");;


    private final String code;
    private final String message;
}
