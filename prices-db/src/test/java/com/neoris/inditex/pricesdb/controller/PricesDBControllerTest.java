package com.neoris.inditex.pricesdb.controller;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.markes96.dto.api.ApiResponseDTO;
import com.markes96.mktest.template.AbstractMkTestTemplate;
import com.markes96.mktest.template.annotation.MkTestTemplate;
import com.markes96.mktest.template.annotation.MkTestTemplateConfiguration;
import com.markes96.mktest.template.enumeration.TestType;
import com.neoris.inditex.pricesdb.dto.api.PricesDBApiRequestDataDTO;
import com.neoris.inditex.pricesdb.exception.RateNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@SpringBootTest
@MkTestTemplateConfiguration(path = "templates/pricesdbqueries", testType = TestType.LOAD_FILE,
    exampleName = "query.json")
public class PricesDBControllerTest extends AbstractMkTestTemplate {

  @Autowired
  PricesDBController controller;

  @MkTestTemplate
  ApiResponseDTO exampleTest(final PricesDBApiRequestDataDTO data)
      throws QueryTimeoutException, TransientDataAccessException, DataAccessException,
      EntityNotFoundException, RateNotFoundException {

    final ResponseEntity<ApiResponseDTO> response = this.controller.getOperatingProductRate(data);

    Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    return response.getBody();
  }

}
