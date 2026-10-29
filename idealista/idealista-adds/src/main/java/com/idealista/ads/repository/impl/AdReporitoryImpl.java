package com.idealista.ads.repository.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import com.idealista.ads.constant.Constant;
import com.idealista.ads.dto.AdDTO;
import com.idealista.ads.repository.AdRepository;
import com.mk96.utils.LoadFileUtils;
import com.mk96.utils.MapperUtils;

@Repository
public class AdReporitoryImpl implements AdRepository {

  private static final String AD_REPOSITORY_FOLDER = "adrepository";

  private final List<AdDTO> ads = new ArrayList<>();

  public AdReporitoryImpl() throws IOException {

    final File folder = LoadFileUtils.getAbsolutePath(AD_REPOSITORY_FOLDER).toFile();

    if (folder.isDirectory()) {
      for (final File file : folder.listFiles()) {
        this.ads.add(MapperUtils.getMapper().readValue(file, AdDTO.class));
      }
    }
  }

  @Override
  public List<AdDTO> findAllAds() {
    return this.ads;
  }

  @Override
  public void save(final AdDTO ad) {
    this.ads.removeIf(x -> x.getId() == ad.getId());
    this.ads.add(ad);
  }


  @Override
  public List<AdDTO> findRelevantAds() {
    return this.ads.stream().filter(x -> x.getScore() >= Constant.RELEVANT_AD_SCORE)
        .collect(Collectors.toList());
  }

  @Override
  public List<AdDTO> findIrrelevantAds() {
    return this.ads.stream().filter(x -> x.getScore() < Constant.RELEVANT_AD_SCORE)
        .collect(Collectors.toList());
  }

}
