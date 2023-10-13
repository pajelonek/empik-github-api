package org.github.pajelonek.empik.empikgithubapi.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import java.nio.file.Files;

public class TestUtils {

    public static <T> T json2ClassType(String fileName, Class<T> classType) {

        T response = null;
        ClassPathResource classPathResource = new ClassPathResource("test_data/" + fileName);

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            response = mapper.readValue(classPathResource.getFile(), classType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}
