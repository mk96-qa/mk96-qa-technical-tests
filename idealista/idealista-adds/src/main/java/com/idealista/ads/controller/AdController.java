package com.idealista.ads.controller;

import org.springframework.http.ResponseEntity;
import com.idealista.ads.dto.AdDTO;
import com.mk96.dto.api.ApiResponseDTO;
import com.mk96.dto.api.response.ApiErrorResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(description = "API that manages the idealista ad repository", name = "ADS API")
public interface AdController {


  @ApiResponses(value = {

      @ApiResponse(responseCode = "200",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = AdDTO.class))),

      @ApiResponse(responseCode = "500",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = ApiErrorResponseDTO.class)))})

  @Operation(summary = "find relevant ads from ads reposiroty", operationId = "find-relevant-ads",
      description = "find relevant ads from local and mocked ads reposiroty")
  ResponseEntity<ApiResponseDTO> findRelevantAds();


  @ApiResponses(value = {

      @ApiResponse(responseCode = "200",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = AdDTO.class))),

      @ApiResponse(responseCode = "500",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = ApiErrorResponseDTO.class)))})

  @Operation(summary = "find irrelevant ads from ads reposiroty",
      operationId = "find-irrelevant-ads",
      description = "find irrelevant ads from local and mocked ads reposiroty")
  ResponseEntity<ApiResponseDTO> findIrrelevantAds();

  @Operation(summary = "recalcule reposiroty ads score", operationId = "calcule-scores",
      description = "recalcule and set ads score from local and mocked ads reposiroty")
  ResponseEntity<Void> calculateScore();

}
