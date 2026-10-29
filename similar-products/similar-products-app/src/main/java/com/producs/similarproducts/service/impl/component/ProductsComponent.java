package com.producs.similarproducts.service.impl.component;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.producs.similarproducts.constant.ServiceConstant;
import com.producs.similarproducts.dto.productdetail.ProductDetail;
import com.producs.similarproducts.service.impl.component.exceptions.APIException;
import com.producs.similarproducts.service.impl.component.exceptions.ProductNotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductsComponent {

  private final RestTemplate restTemplate;

  public String[] getSimilarProductsIds(final String productId) {
    final String url = ServiceConstant.SIMILAR_PRODUCT_IDS_PATH.apply(productId);
    return this.callSimilarProdusctsApi(url, String[].class, productId);
  }

  public ProductDetail getProductDetailFromId(final String productId) {
    final String url = ServiceConstant.PRODUCT_PRODUCTID_PATH.apply(productId);
    return this.callSimilarProdusctsApi(url, ProductDetail.class, productId);
  }

  private <T> T callSimilarProdusctsApi(final String url, final Class<T> returnType,
      final String productId) {

    try {
      return restTemplate.getForObject(url, returnType);
    } catch (final HttpClientErrorException ex) {
      if (ex.getRawStatusCode() == 404) {
        throw new ProductNotFoundException(
            "Similar product IDs not found for product ID: " + productId);
      } else {
        throw new APIException(
            "Error while calling the API for similar product IDs: " + ex.getMessage());
      }
    }
  }

}
