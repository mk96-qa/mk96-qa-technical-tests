package com.idealista.ads.service.impl;

import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.idealista.ads.dto.AdDTO;
import com.idealista.ads.repository.AdRepository;
import com.idealista.ads.service.AdService;
import com.idealista.ads.service.component.ScoreComponent;

@Service
public class AdServiceImpl implements AdService {

  @Autowired
  private AdRepository adRepository;

  @Autowired
  ScoreComponent scoreComponent;

  @Override
  public List<AdDTO> findRelevantAds() {
    final List<AdDTO> ads = this.adRepository.findRelevantAds();
    ads.sort(Comparator.comparing(AdDTO::getScore));
    return ads;
  }

  @Override
  public List<AdDTO> findIrrelevantAds() {
    return this.adRepository.findIrrelevantAds();
  }

  @Override
  public void calculateScores() {
    this.adRepository.findAllAds().forEach(this.scoreComponent::calculeAndAddScore);
  }

}
