package com.neoris.inditex.pricesdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.dao.TransientDataAccessException;
import com.markes96.mktest.template.AbstractMkTestTemplate;
import com.markes96.mktest.template.annotation.MkTestTemplate;
import com.markes96.mktest.template.annotation.MkTestTemplateConfiguration;
import com.markes96.mktest.template.enumeration.TestType;
import com.neoris.inditex.pricesdb.dto.api.PricesDBApiRequestDataDTO;
import com.neoris.inditex.pricesdb.dto.database.RateDTO;
import com.neoris.inditex.pricesdb.exception.RateNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@SpringBootTest
@MkTestTemplateConfiguration(path = "templates/pricesdbqueries", testType = TestType.LOAD_FILE,
    exampleName = "query.json")
public class PricesDBServiceTest extends AbstractMkTestTemplate {

  @Autowired
  PricesDBService service;

  @MkTestTemplate
  RateDTO exampleTest(final PricesDBApiRequestDataDTO data)
      throws QueryTimeoutException, TransientDataAccessException, DataAccessException,
      EntityNotFoundException, RateNotFoundException {
    return this.service.getOperatingProductRate(data);
  }

}
