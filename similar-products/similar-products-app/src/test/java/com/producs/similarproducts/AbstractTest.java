package com.producs.similarproducts;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.springframework.util.CollectionUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractTest {

  /**
   * Method used to get the absolute {@link Path} representation of the {@link String} passed by
   * parameter
   *
   * @param relativePath representation in String of the relative path of a file
   * @return Absolute path {@link Path} representation of the {@link String} passed by parameter
   */
  protected Path getAbsolutePath(final String relativePath) {
    return Paths.get(
        new File(Thread.currentThread().getContextClassLoader().getResource(relativePath).getFile())
            .getAbsolutePath());
  }

  /**
   * Method used to load a file with a string of the file path
   *
   * @param <T> the type of the value being boxed
   * @param clazz class that indicates the return type of the method
   * @param path string or initial part of the path string
   * @param more additional strings to be joined to form the path string
   * @return string or byte[] of the file loaded
   * @throws IOException when an I/O exception of some sort has occurred
   * @throws TestException when there is a problem while loading the file
   */
  protected <T> T loadFile(final Class<T> clazz, final String path, final String... more)
      throws IOException, TestException {
    return this.loadFile(clazz, Paths.get(path, more));
  }

  /**
   * Method used to load a file with a {@link Class} and {@link Path} passed
   *
   * @param <T> the type of the value being boxed
   * @param clazz class that indicates the return type of the method
   * @param fileToLoad object Path of the file to load
   * @return a string or byte[] of the file loaded
   * @throws IOException when an I/O exception of some sort has occurred
   */
  @SuppressWarnings("unchecked")
  protected <T> T loadFile(final Class<T> clazz, final Path fileToLoad) throws IOException {

    T fileLoaded = null;

    if (clazz == byte[].class) {
      fileLoaded = (T) FileUtils.readFileToByteArray(fileToLoad.toFile());
    } else {
      final String fileString =
          FileUtils.readFileToString(fileToLoad.toFile(), StandardCharsets.UTF_8);
      if (clazz == String.class) {
        fileLoaded = (T) fileString;
      } else {
        fileLoaded = this.readValueFromString(clazz, fileString);
      }
    }

    return fileLoaded;
  }

  /**
   * Method used to compare an {@link Object} to a file passed by a {@link String} path
   *
   * @param result Object to compare
   * @param expectedResultPath the path string or initial part of the path string of the file
   * @param more additional strings to be joined to form the path string
   * @return boolean of the comparison result
   * @throws IOException when an I/O exception of some sort has occurred
   * @throws TestException if there is an error while comparing the result to the file
   */
  protected boolean compareResultToFile(final Object result, final String expectedResultPath,
      final String... more) throws IOException, TestException {
    return this.compareResultToFile(result, Paths.get(expectedResultPath, more));
  }

  /**
   * Method used to compare an Object to a file passed as {@link Path}
   *
   * @param result Object to be compared
   * @param expectedResultPath Path of the file to be compared with
   * @return boolean as result of the comparison
   * @throws TestException when there is an error while loading the file
   * @throws IOException when an I/O exception of some sort has occurred
   */
  protected boolean compareResultToFile(final Object result, final Path expectedResultPath)
      throws TestException, IOException {
    final Class<?> clazz = result instanceof byte[] ? byte[].class : String.class;
    final Object expectedResult = this.loadFile(clazz, expectedResultPath);
    return this.compareResult(result, expectedResult);
  }

  /**
   * Method to compare two objects
   *
   * @param result object to compare
   * @param expectedResult object expected to compare
   * @return boolean of the result from the comparison
   * @throws JsonProcessingException Intermediate base class for all problems encountered when
   *         processing (parsing, generating) JSON content that are not pure I/O problems
   */
  protected boolean compareResult(final Object result, final Object expectedResult)
      throws JsonProcessingException {

    final Object resultComparable = this.convertToComparableType(result);
    final Object expectedComparable = this.convertToComparableType(expectedResult);

    Assertions.assertEquals(expectedComparable, resultComparable);
    return resultComparable.equals(expectedComparable);
  }

  /**
   * Method used to convert into a comparable type the {@link Object} passed
   *
   * @param object to be converted
   * @return the object converted into a String or byte[]
   * @throws JsonProcessingException Intermediate base class for all problems encountered when
   *         processing (parsing, generating) JSON content that are not pure I/O problems
   */
  protected Object convertToComparableType(final Object object) throws JsonProcessingException {

    if (object instanceof String) {
      return object;
    } else if (object instanceof byte[]) {
      return CollectionUtils.arrayToList(object);
    } else {
      return this.writeValueAsString(object);
    }
  }

  protected static ObjectMapper mapper = new ObjectMapper();

  static {
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  /**
   * Method used to deserialize an object from String;
   *
   * @param <T> the type of the value being boxed
   * @param clazz class that indicates the return type of the method
   * @param value object Path of the file to load
   * @return a string or byte[] of the file loaded
   * @throws JsonProcessingException Intermediate base class for all problems encountered when
   *         processing (parsing, generating) JSON content that are not pure I/O problems
   */
  protected <T> T readValueFromString(final Class<T> clazz, final String value)
      throws JsonProcessingException {
    return mapper.readValue(value, clazz);
  }

  /**
   * Method used to serialize and object as {@link String} with default pretty printer
   *
   * @param obj to serialize
   * @throws JsonProcessingException Intermediate base class for all problems encountered when
   *         processing (parsing, generating) JSON content that are not pure I/O problems
   * @return object serialized
   */
  protected String writeValueAsString(final Object obj) throws JsonProcessingException {
    return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
  }

  /**
   * Method used to serialize and object as {@link String} with flat printer
   *
   * @param obj to serialize
   * @throws JsonProcessingException Intermediate base class for all problems encountered when
   *         processing (parsing, generating) JSON content that are not pure I/O problems
   * @return object serialized
   */
  protected String writeValueAsFlatString(final Object obj) throws JsonProcessingException {
    return mapper.writeValueAsString(obj);
  }

  /**
   * Method used to serialize and object as {@link byte} array with default pretty printer
   *
   * @param obj to serialize
   * @throws JsonProcessingException Intermediate base class for all problems encountered when
   *         processing (parsing, generating) JSON content that are not pure I/O problems
   * @return object serialized
   */
  protected byte[] writeValueAsBytes(final Object obj) throws JsonProcessingException {
    return mapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(obj);
  }

  /**
   * Method used to serialize and object as {@link byte} array with flat printer
   *
   * @param obj to serialize
   * @throws JsonProcessingException Intermediate base class for all problems encountered when
   *         processing (parsing, generating) JSON content that are not pure I/O problems
   * @return object serialized
   */
  protected byte[] writeValueAsFlatBytes(final Object obj) throws JsonProcessingException {
    return mapper.writeValueAsBytes(obj);
  }

  /**
   * Convert to array
   *
   * @param <T> the type of the value being boxed
   * @param t objecs to intert on array
   * @return array with all parameters
   */
  @SuppressWarnings("unchecked")
  protected <T> T[] toArray(final T... t) {
    return t;
  }

  protected static class TestException extends Exception {

    public TestException(final String mesagge) {
      super(mesagge);
    }

    public TestException(final String mesagge, final Throwable cause) {
      super(mesagge, cause);
    }
  }

}
