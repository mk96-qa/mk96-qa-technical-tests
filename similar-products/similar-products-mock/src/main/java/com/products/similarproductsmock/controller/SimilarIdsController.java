package com.products.similarproductsmock.controller;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/get-product-similarids")
public class SimilarIdsController {

  @GetMapping("/product/{productId}/similarids")
  public ResponseEntity<List<String>> getSimilarProductsIds(
      @PathVariable(required = true) final String productId) {

    final String id1 = UUID.randomUUID().toString();
    final String id2 = UUID.randomUUID().toString();
    final String id3 = UUID.randomUUID().toString();

    return new ResponseEntity<>(Arrays.asList(id1, id2, id3), HttpStatus.OK);
  }

}
