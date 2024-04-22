package io.github.farrukhjon.experiment.org.structure.analyzer.util;

import io.github.farrukhjon.experiment.org.structure.analyzer.service.BaseTest;
import java.io.File;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author fsattorov
 */
final class CsvObjectMapperTest extends BaseTest {

    private final ObjectMapper objectMapper = new CsvObjectMapper();

    @Test
    @DisplayName("Test throws exception when an empty records csv is passed")
    void toEmployeesWhenMissedRecords() throws Exception {
        final String testFileName = "employees-empty-records.csv";
        final File file = this.loadFileFromClasspath(testFileName);
        final RuntimeException exception =
            Assertions.assertThrows(RuntimeException.class, () -> this.objectMapper.toEmployees(file));
        final String errMsg = exception.getCause().getMessage();
        Assertions.assertTrue(errMsg.endsWith(testFileName + " is empty!\n"));
    }

    @Test
    @DisplayName("Test throws exception when an empty records csv is passed")
    void toEmployeesWhenMissedNames() throws Exception {
        final String testFileName = "employees-missed-names.csv";
        final File file = this.loadFileFromClasspath(testFileName);
        final RuntimeException exception =
            Assertions.assertThrows(RuntimeException.class, () -> this.objectMapper.toEmployees(file));
        final Throwable cause = exception.getCause();
        final String message = cause.getLocalizedMessage();
        Assertions.assertInstanceOf(IllegalArgumentException.class, cause);
        Assertions.assertEquals("First name value is required!", message);
    }
}