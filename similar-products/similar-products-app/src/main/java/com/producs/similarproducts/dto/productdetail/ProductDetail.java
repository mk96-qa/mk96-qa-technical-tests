package com.producs.similarproducts.dto.productdetail;

import com.producs.similarproducts.dto.AbstractDto;
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
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class ProductDetail extends AbstractDto {

  private String id;
  private String name;
  private double price;
  private boolean availability;

}
