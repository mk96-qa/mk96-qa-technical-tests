package com.neoris.inditex.pricesdb.dto.api;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import com.markes96.dto.api.ApiRequestDataDTO;
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
public class PricesDBApiRequestDataDTO extends ApiRequestDataDTO {

  @NotNull(message = "productId can't be null")
  private int productId;

  @NotNull(message = "brandId can't be null")
  private int brandId;

  @NotNull(message = "applicationDate can't be null")
  @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}",
      message = "applicationDate format error")
  private LocalDateTime applicationDate;
}
