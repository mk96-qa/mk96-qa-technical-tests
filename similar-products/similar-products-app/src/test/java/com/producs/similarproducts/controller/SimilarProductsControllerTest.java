package com.producs.similarproducts.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.producs.similarproducts.AbstractTest;
import com.producs.similarproducts.constant.TestConstant;
import com.producs.similarproducts.dto.productdetail.ProductDetail;
import com.producs.similarproducts.service.SimilarProductsService;

@SpringBootTest
class SimilarProductsControllerTest extends AbstractTest {

  @Mock
  private SimilarProductsService productSimilarService;

  @InjectMocks
  private SimilarProductsController similarProductsController;

  @Test
  void similarProductsControllerTest() throws IOException, TestException {

    final List<ProductDetail> getSimilarProductsReturn =
        Arrays.asList(super.loadFile(ProductDetail[].class,
            super.getAbsolutePath(TestConstant.GET_SIMILAR_PRODUCTS_RETURN_PATH)));

    Mockito.when(this.productSimilarService.getSimilarProducts(ArgumentMatchers.anyString()))
        .thenReturn(getSimilarProductsReturn);

    final ResponseEntity<List<ProductDetail>> result =
        this.similarProductsController.getSimilarProducts(StringUtils.EMPTY);


    Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    Assertions.assertEquals(200, result.getStatusCodeValue());
    super.compareResultToFile(result.getBody(),
        super.getAbsolutePath(TestConstant.SIMILAR_PRODUCTS_CONTROLLER_RESULT_PATH));
  }

}
