package com.producs.similarproducts.service.impl.component.exceptions;

public class ProductNotFoundException extends RuntimeException {
  public ProductNotFoundException(final String message) {
    super(message);
  }
}
