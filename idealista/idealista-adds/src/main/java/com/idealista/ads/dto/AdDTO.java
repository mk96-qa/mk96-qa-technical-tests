package com.idealista.ads.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.idealista.ads.constant.TypologyEnum;
import com.idealista.ads.dto.ad.GarageAdDTO;
import com.idealista.ads.dto.ad.house.ChaletAdDTO;
import com.idealista.ads.dto.ad.house.FlatAdDTO;
import com.mk96.dto.AbstractDTO;
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

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "typology")
@JsonSubTypes({

    @JsonSubTypes.Type(value = FlatAdDTO.class, name = "FLAT"),
    @JsonSubTypes.Type(value = ChaletAdDTO.class, name = "CHALET"),
    @JsonSubTypes.Type(value = GarageAdDTO.class, name = "GARAGE")

})
public abstract class AdDTO extends AbstractDTO {

  private int id;
  private TypologyEnum typology;
  @Builder.Default
  private List<PictureDTO> pictures = new ArrayList<>();
  private String description;
  private Integer score;
  private Date irrelevantSince;

}
