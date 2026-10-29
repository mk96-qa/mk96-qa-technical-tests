package com.neoris.inditex.pricesdb.dto.database;

import com.neoris.inditex.pricesdb.dto.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RateIdDTO extends AbstractDTO {
  private int brandId;
  private int rateNumber;
  private int productId;
}
