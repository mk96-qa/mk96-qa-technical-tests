package com.producs.similarproducts.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.producs.similarproducts.dto.productdetail.ProductDetail;
import com.producs.similarproducts.service.SimilarProductsService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
public class SimilarProductsController {

  @Autowired
  private SimilarProductsService service;

  @Operation(summary = "Similar products", operationId = "get-product-similar",
      description = "Returns the similar products to a given one ordered by similarity")
  @GetMapping("/product/{productId}/similar")
  public ResponseEntity<List<ProductDetail>> getSimilarProducts(
      @PathVariable(required = true) final String productId) {
    final List<ProductDetail> response = service.getSimilarProducts(productId);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

}
