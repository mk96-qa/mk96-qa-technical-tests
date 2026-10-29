package com.producs.similarproducts.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TestConstant {

  public static final String SIMILAR_PRODUCT_SERVICE_PATH = "productsimilarservice/";

  public static final String GET_SIMILAR_PRODUCTS_IDS_RETURN_PATH =
      SIMILAR_PRODUCT_SERVICE_PATH + "getSimilarProductIdsReturn.json";

  public static final String GET_PRODUCT_DETAIL_FROM_ID_RETURN_PATH =
      SIMILAR_PRODUCT_SERVICE_PATH + "getProductDetailFromIdReturn.json";

  public static final String GET_SIMILAR_PRODUCT_TEST_RESULT_PATH =
      SIMILAR_PRODUCT_SERVICE_PATH + "getSimilarProductTestResult.json";

  public static final String SIMILAR_PRODUCTS_CONTROLLER_PATH = "similarproductscontroller/";

  public static final String GET_SIMILAR_PRODUCTS_RETURN_PATH =
      SIMILAR_PRODUCTS_CONTROLLER_PATH + "getSimilarProductsReturn.json";

  public static final String SIMILAR_PRODUCTS_CONTROLLER_RESULT_PATH =
      SIMILAR_PRODUCTS_CONTROLLER_PATH + "similarProductsControllerTestResult.json";

}
