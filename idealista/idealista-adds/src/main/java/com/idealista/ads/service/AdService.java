package com.idealista.ads.service;

import java.util.List;
import com.idealista.ads.dto.AdDTO;

public interface AdService {

  List<AdDTO> findRelevantAds();

  List<AdDTO> findIrrelevantAds();

  void calculateScores();
}
