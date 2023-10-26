//package com.example.sogong.domain.product.exception;
//
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice(basePackages = "com.example.sogong.domain.product")
//public class ProductExceptionHandler {
//
//    @ExceptionHandler(ProductNotFoundException.class)
//    public String handleProductNotFoundException(ProductNotFoundException e) {
//        return e.getMessage();
//    }
//
//    @ExceptionHandler(ProductStockAmountNotEnoughException.class)
//    public String handleProductStockAmountNotEnoughException(ProductStockAmountNotEnoughException e) {
//        return e.getMessage();
//    }
//}
