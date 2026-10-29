package com.neoris.inditex.pricesdb.exception;

import lombok.Getter;

@Getter
public class RateNotFoundException extends Exception {
  private static final long serialVersionUID = 1L;

  private final String localizedMessage;

  public RateNotFoundException(final String localizedMessage, final String message) {
    super(message);
    this.localizedMessage = localizedMessage;
  }
}
