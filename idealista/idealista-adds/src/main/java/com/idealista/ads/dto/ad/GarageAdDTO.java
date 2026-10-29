package com.idealista.ads.dto.ad;

import com.idealista.ads.constant.TypologyEnum;
import com.idealista.ads.dto.AdDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class GarageAdDTO extends AdDTO {

  @Builder.Default
  private final TypologyEnum typology = TypologyEnum.GARAGE;
  private Integer garageSize;

}
