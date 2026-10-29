package com.producs.similarproducts.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.producs.similarproducts.dto.productdetail.ProductDetail;
import com.producs.similarproducts.service.SimilarProductsService;
import com.producs.similarproducts.service.impl.component.ProductsComponent;

@Service
public class SimilarProductsServiceImpl implements SimilarProductsService {

  @Autowired
  private ProductsComponent productsComponent;

  @Override
  public List<ProductDetail> getSimilarProducts(final String productId) {
    final String[] productIds = this.productsComponent.getSimilarProductsIds(productId);
    return Stream.of(productIds).map(this.productsComponent::getProductDetailFromId)
        .collect(Collectors.toList());
  }

}
