package com.idealista.ads.service.component;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import com.idealista.ads.constant.QualityEnum;
import com.idealista.ads.constant.TypologyEnum;
import com.idealista.ads.dto.AdDTO;
import com.idealista.ads.dto.PictureDTO;

@Component
public class ScoreComponent {

  private final int INIT_SCORE = 0;
  private final int MINIMUM_SCORE = 0;
  private final int MAXIMUM_SCORE = 100;
  private final int IRRELEVANT_SCORE = 40;

  // PICTURE SCORES
  private final int NO_PICTURE_PENALIZATION = 10;
  private final int HD_PICTURE_SCORE = 20;
  private final int SD_PICTURE_SCORE = 10;

  // DESCRIPTION SCORES
  private final int NON_EMPTY_DESCRIPTION_SCORE = 5;

  private final int FLAT_MEDIUM_SCORE = 10;
  private final int FLAT_LARGE_SCORE = 30;

  private final int CHALET_LARGE_SCORE = 20;

  private final Predicate<Integer> MEDIUM_RANGE = value -> (value >= 20) && (value < 50);
  private final Predicate<Integer> LARGE_RANGE = value -> value >= 50;

  // KEY WORDS SCORES
  private final Map<String, Integer> KEY_WORDS_SCORES = new HashMap<>();

  {
    this.KEY_WORDS_SCORES.put("luminoso", 5);
    this.KEY_WORDS_SCORES.put("nuevo", 5);
    this.KEY_WORDS_SCORES.put("céntrico", 5);
    this.KEY_WORDS_SCORES.put("reformado", 5);
    this.KEY_WORDS_SCORES.put("ático", 5);
  }

  public void calculeAndAddScore(final AdDTO ad) {

    int score = this.INIT_SCORE;
    score += this.pictureScore(ad);
    score += this.descriptionScore(ad);

    if (score < this.MINIMUM_SCORE) {
      score = this.MINIMUM_SCORE;
    } else if (score > this.MAXIMUM_SCORE) {
      score = this.MAXIMUM_SCORE;
    }

    if (score < this.IRRELEVANT_SCORE) {
      ad.setIrrelevantSince(new Date());
    } else {
      ad.setIrrelevantSince(null);
    }

    ad.setScore(score);
  }

  private int pictureScore(final AdDTO ad) {

    int score = this.INIT_SCORE;
    if (CollectionUtils.isEmpty(ad.getPictures())) {
      score -= this.NO_PICTURE_PENALIZATION;
    } else {
      // quizas un reduce?
      for (final PictureDTO picture : ad.getPictures()) {
        score += QualityEnum.HD.equals(picture.getQuality()) ? this.HD_PICTURE_SCORE
            : this.SD_PICTURE_SCORE;
      }
    }

    return score;
  }

  private int descriptionScore(final AdDTO ad) {

    int score = this.INIT_SCORE;
    final String description = ad.getDescription();
    if (StringUtils.isNotEmpty(description)) {

      score += this.NON_EMPTY_DESCRIPTION_SCORE;

      final List<String> wds = Arrays.asList(description.split(StringUtils.SPACE));

      if (TypologyEnum.FLAT.equals(ad.getTypology())) {
        score += this.flatDescriptionScore(wds.size());
      } else if (TypologyEnum.CHALET.equals(ad.getTypology())) {
        score += this.chaletDescriptionScore(wds.size());
      }

      score += this.keyWordsScore(wds);
    }

    return score;
  }


  private int flatDescriptionScore(final int size) {
    int score = this.INIT_SCORE;

    if (this.MEDIUM_RANGE.test(size)) {
      score += this.FLAT_MEDIUM_SCORE;
    } else if (this.LARGE_RANGE.test(size)) {
      score += this.FLAT_LARGE_SCORE;
    }

    return score;
  }

  private int chaletDescriptionScore(final int size) {
    int score = this.INIT_SCORE;

    if (this.LARGE_RANGE.test(size)) {
      score += this.CHALET_LARGE_SCORE;
    }

    return score;
  }

  private int keyWordsScore(final List<String> wds) {
    int score = this.INIT_SCORE;

    for (final String keyWord : this.KEY_WORDS_SCORES.keySet()) {
      if (wds.contains(keyWord)) {
        score += this.KEY_WORDS_SCORES.get(keyWord);
      }
    }

    return score;

  }


}
