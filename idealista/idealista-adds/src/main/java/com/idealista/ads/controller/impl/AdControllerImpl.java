package com.idealista.ads.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.idealista.ads.controller.AdController;
import com.idealista.ads.dto.AdDTO;
import com.idealista.ads.service.AdService;
import com.mk96.dto.api.ApiResponseDTO;
import com.mk96.dto.api.response.ApiOkResponseDTO;

@RestController
@RequestMapping("/idealista/ads")
public class AdControllerImpl implements AdController {

  @Autowired
  private AdService adsService;

  @Override
  @GetMapping("/relevant")
  public ResponseEntity<ApiResponseDTO> findRelevantAds() {
    try {
      final List<AdDTO> ads = this.adsService.findRelevantAds();
      return ResponseEntity.ok(new ApiOkResponseDTO<>(ads));
    } catch (final Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @Override
  @GetMapping("/irrelevant")
  public ResponseEntity<ApiResponseDTO> findIrrelevantAds() {
    try {
      final List<AdDTO> ads = this.adsService.findIrrelevantAds();
      return ResponseEntity.ok(new ApiOkResponseDTO<>(ads));
    } catch (final Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @Override
  @PutMapping("/score")
  public ResponseEntity<Void> calculateScore() {
    this.adsService.calculateScores();
    return ResponseEntity.accepted().build();
  }
}
