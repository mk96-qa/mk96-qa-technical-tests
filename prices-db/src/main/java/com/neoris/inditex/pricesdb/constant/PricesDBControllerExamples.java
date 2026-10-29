package com.neoris.inditex.pricesdb.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PricesDBControllerExamples {

  public final String OK_RESPONSE =

      "{\r\n"

          + "    \"brandId\": 1,\r\n"

          + "    \"rateNumber\": 1,\r\n"

          + "    \"productId\": 35455,\r\n"

          + "    \"startDate\": \"2020-06-14T00:00:00\",\r\n"

          + "    \"endDate\": \"2020-12-31T23:59:59\",\r\n"

          + "    \"price\": 35.5,\r\n"

          + "    \"curr\": \"EUR\"\r\n"

          + "}";

  public final String RATE_NOT_FOUND =

      "{\r\n"

          + "    \"timestamp\": \"2023-10-24T02:14:06.4973808\",\r\n"

          + "    \"status\": 404,\r\n"

          + "    \"error\": \"Operating Product Rate not found\",\r\n"

          + "    \"message\": \"There isn't operating product rate for -> BrandID: 1 ProductId: 35456 ApplicationDate: 2020-06-14 10:00:00\"\r\n"

          + "}";

}
