package org.dni9.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@Slf4j
public class JsonUtils {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  public static <T> T readJson(String fileName, Class<T> clazz) {
    try {
      log.info("Reading test data: {}.json", fileName);
      return objectMapper.readValue(new File("src/test/resources/data/" + fileName + ".json"), clazz);
    } catch (IOException e) {
      log.error("Unable to find test data with file name: {}", fileName);
      throw new RuntimeException(e);
    }
  }
}
