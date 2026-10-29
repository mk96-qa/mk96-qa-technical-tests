package com.idealista.ads.dto;

import com.idealista.ads.constant.QualityEnum;
import com.mk96.dto.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class PictureDTO extends AbstractDTO {

  private Integer id;
  private String url;
  private QualityEnum quality;

}
