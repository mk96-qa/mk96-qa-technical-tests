package com.producs.similarproducts.service;

import java.util.List;
import com.producs.similarproducts.dto.productdetail.ProductDetail;

public interface SimilarProductsService {

  /**
   * @param productId {@link String}: id of the product for which want to search for similar
   *        products
   * @return List of {@link ProductDetail}: list of nodes with the information of the similar nodes
   */
  List<ProductDetail> getSimilarProducts(String productId);

}
