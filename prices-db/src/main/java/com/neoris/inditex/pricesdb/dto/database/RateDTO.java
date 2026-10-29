package com.neoris.inditex.pricesdb.dto.database;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neoris.inditex.pricesdb.dto.AbstractDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "PRICES")
@IdClass(RateIdDTO.class)
public class RateDTO extends AbstractDTO {

  @Id
  private int brandId;
  @Id
  private int rateNumber;
  @Id
  private int productId;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  @JsonIgnore
  private Integer priority;
  private Double price;
  private String curr;

}
