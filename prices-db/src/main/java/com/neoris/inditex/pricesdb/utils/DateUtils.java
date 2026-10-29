package com.neoris.inditex.pricesdb.utils;

import java.time.LocalDateTime;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DateUtils {

  public boolean isWithinRange(final LocalDateTime targetDate, final LocalDateTime startDate,
      final LocalDateTime endDate) {
    return targetDate.isAfter(startDate) && targetDate.isBefore(endDate);
  }

}
