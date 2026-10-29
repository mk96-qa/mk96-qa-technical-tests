package com.idealista.ads.repository;

import java.util.List;
import com.idealista.ads.dto.AdDTO;

public interface AdRepository {

  void save(AdDTO ad);

  List<AdDTO> findAllAds();

  List<AdDTO> findRelevantAds();

  List<AdDTO> findIrrelevantAds();

}
