package com.neoris.inditex.pricesdb.controller;

import org.springframework.http.ResponseEntity;
import com.markes96.dto.api.ApiResponseDTO;
import com.markes96.dto.api.response.ApiErrorResponseDTO;
import com.neoris.inditex.pricesdb.constant.PricesDBControllerExamples;
import com.neoris.inditex.pricesdb.dto.api.PricesDBApiRequestDataDTO;
import com.neoris.inditex.pricesdb.dto.database.RateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(description = "Prices database general controller", name = "PricesDatabaseController")
public interface PricesDBController {

  @ApiResponses(value = {

      @ApiResponse(responseCode = "200",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = RateDTO.class),
              examples = @ExampleObject(value = PricesDBControllerExamples.OK_RESPONSE))),

      @ApiResponse(responseCode = "504",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = ApiErrorResponseDTO.class))),

      @ApiResponse(responseCode = "503",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = ApiErrorResponseDTO.class))),

      @ApiResponse(responseCode = "500",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = ApiErrorResponseDTO.class))),

      @ApiResponse(responseCode = "404",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = ApiErrorResponseDTO.class))),

      @ApiResponse(responseCode = "404",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = ApiErrorResponseDTO.class),
              examples = @ExampleObject(value = PricesDBControllerExamples.RATE_NOT_FOUND))),})

  @Operation(summary = "Get operating product rate from Prices database",
      operationId = "get-operating-product-rate",
      description = "Get the operating rate information from Prices database")
  ResponseEntity<ApiResponseDTO> getOperatingProductRate(final PricesDBApiRequestDataDTO data);

}
