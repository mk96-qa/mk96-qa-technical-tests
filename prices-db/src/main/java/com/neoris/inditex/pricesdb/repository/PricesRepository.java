package com.neoris.inditex.pricesdb.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.neoris.inditex.pricesdb.dto.database.RateIdDTO;
import com.neoris.inditex.pricesdb.dto.database.RateDTO;

@Repository
public interface PricesRepository extends JpaRepository<RateDTO, RateIdDTO> {
  List<RateDTO> findByBrandIdAndProductId(int brandId, int productId);
}
