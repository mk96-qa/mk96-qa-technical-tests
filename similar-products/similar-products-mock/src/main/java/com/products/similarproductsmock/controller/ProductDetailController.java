package com.products.similarproductsmock.controller;

import java.util.Random;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.products.similarproductsmock.dto.ProductDetail;

@RestController
@RequestMapping("/get-product-productId")
public class ProductDetailController {

  final Random random = new Random();
  final RandomStringGenerator generator =
      new RandomStringGenerator.Builder().withinRange('a', 'z').build();

  @GetMapping("/product/{productId}")
  public ResponseEntity<ProductDetail> getSimilarProducts(
      @PathVariable(required = true) final String productId) {

    final ProductDetail productDetail =
        ProductDetail.builder().id(productId).name(generator.generate(8)).price(random.nextInt(100))
            .availability(random.nextBoolean()).build();

    new ProductDetail(productId, generator.generate(8), random.nextInt(100), random.nextBoolean());

    return new ResponseEntity<>(productDetail, HttpStatus.OK);
  }

}
