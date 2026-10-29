package com.producs.similarproducts.service;

import java.io.IOException;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import com.producs.similarproducts.AbstractTest;
import com.producs.similarproducts.constant.TestConstant;
import com.producs.similarproducts.dto.productdetail.ProductDetail;
import com.producs.similarproducts.service.impl.SimilarProductsServiceImpl;
import com.producs.similarproducts.service.impl.component.ProductsComponent;

@SpringBootTest
class SimilarProductServiceTest extends AbstractTest {

  @Mock
  private ProductsComponent SimilarProductsComponentMock;

  @InjectMocks
  private SimilarProductsServiceImpl productSimilarService;

  @Test
  void getSimilarProductTest() throws IOException, TestException {

    final String[] getSimilarProductIdsReturn = this.loadFile(String[].class,
        super.getAbsolutePath(TestConstant.GET_SIMILAR_PRODUCTS_IDS_RETURN_PATH));

    Mockito.when(SimilarProductsComponentMock.getSimilarProductsIds(ArgumentMatchers.anyString()))
        .thenReturn(getSimilarProductIdsReturn);

    final ProductDetail[] getProductDetailFromIdReturn = this.loadFile(ProductDetail[].class,
        super.getAbsolutePath(TestConstant.GET_PRODUCT_DETAIL_FROM_ID_RETURN_PATH));

    Mockito.when(SimilarProductsComponentMock.getProductDetailFromId(ArgumentMatchers.anyString()))
        .thenReturn(getProductDetailFromIdReturn[0], getProductDetailFromIdReturn[1],
            getProductDetailFromIdReturn[2]);

    final List<ProductDetail> result = productSimilarService.getSimilarProducts(StringUtils.EMPTY);

    super.compareResultToFile(result,
        super.getAbsolutePath(TestConstant.GET_SIMILAR_PRODUCT_TEST_RESULT_PATH));
  }

}
