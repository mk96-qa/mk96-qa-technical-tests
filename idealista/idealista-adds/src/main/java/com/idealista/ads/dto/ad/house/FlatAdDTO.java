package com.idealista.ads.dto.ad.house;

import com.idealista.ads.constant.TypologyEnum;
import com.idealista.ads.dto.ad.HouseAdDTO;
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
public class FlatAdDTO extends HouseAdDTO {

  @Builder.Default
  private final TypologyEnum typology = TypologyEnum.FLAT;

}
