package com.neoris.inditex.pricesdb.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.stereotype.Service;
import com.neoris.inditex.pricesdb.constant.ErrorConstant;
import com.neoris.inditex.pricesdb.dto.api.PricesDBApiRequestDataDTO;
import com.neoris.inditex.pricesdb.dto.database.RateDTO;
import com.neoris.inditex.pricesdb.exception.RateNotFoundException;
import com.neoris.inditex.pricesdb.repository.PricesRepository;
import com.neoris.inditex.pricesdb.service.PricesDBService;
import com.neoris.inditex.pricesdb.utils.DateUtils;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PricesDBServiceImpl implements PricesDBService {

  @Autowired
  private PricesRepository pricesRepository;

  @Override
  public RateDTO getOperatingProductRate(final PricesDBApiRequestDataDTO data)
      throws RateNotFoundException, DataAccessException, TransientDataAccessException,
      QueryTimeoutException, EntityNotFoundException {

    final List<RateDTO> rates =
        this.pricesRepository.findByBrandIdAndProductId(data.getBrandId(), data.getProductId());

    final List<RateDTO> ratesOnDateRange =
        rates.stream().filter(price -> DateUtils.isWithinRange(data.getApplicationDate(),
            price.getStartDate(), price.getEndDate())).collect(Collectors.toList());

    final Optional<RateDTO> rateOnDateRange = ratesOnDateRange.stream().reduce((priorityPrice,
        price) -> price.getPriority() > priorityPrice.getPriority() ? price : priorityPrice);

    if (rateOnDateRange.isPresent()) {
      return rateOnDateRange.get();
    }
    throw new RateNotFoundException(ErrorConstant.RATE_NOT_FOUND_ERROR,
        ErrorConstant.RATE_NOT_FOUND_MESSAGE.apply(data));
  }

  @Override
  public String toString() {
    return "hola";
  }

}
