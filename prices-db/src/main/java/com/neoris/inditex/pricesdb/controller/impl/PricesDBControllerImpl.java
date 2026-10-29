package com.neoris.inditex.pricesdb.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.markes96.dto.api.ApiResponseDTO;
import com.markes96.dto.api.response.ApiOkResponseDTO;
import com.neoris.inditex.pricesdb.controller.PricesDBController;
import com.neoris.inditex.pricesdb.dto.api.PricesDBApiRequestDataDTO;
import com.neoris.inditex.pricesdb.dto.database.RateDTO;
import com.neoris.inditex.pricesdb.service.PricesDBService;

@RestController
@RequestMapping("/Neoris/Inditex/PricesDB")
public class PricesDBControllerImpl implements PricesDBController {

  @Autowired
  PricesDBService pricesDBService;

  @Override
  @PostMapping("/getOperatingProductRate")
  public ResponseEntity<ApiResponseDTO> getOperatingProductRate(
      @RequestBody(required = true) @Validated final PricesDBApiRequestDataDTO data) {

    if (this.pricesDBService == null) {
      return ResponseEntity.internalServerError().build();
    }

    try {
      final RateDTO rate = this.pricesDBService.getOperatingProductRate(data);
      return ResponseEntity.ok(new ApiOkResponseDTO<>(rate));
    } catch (final Exception e) {
      return null;
    }

  }

}
