package com.producs.similarproducts.constant;

import java.util.function.UnaryOperator;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ServiceConstant {

  public static final UnaryOperator<String> SIMILAR_PRODUCT_IDS_PATH =
      productId -> String.format("http://localhost:3001/product/%s/similarids", productId);

  public static final UnaryOperator<String> PRODUCT_PRODUCTID_PATH =
      productId -> String.format("http://localhost:3001/product/%s", productId);

}
