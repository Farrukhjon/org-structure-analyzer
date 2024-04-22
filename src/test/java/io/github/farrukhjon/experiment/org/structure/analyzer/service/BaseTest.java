package io.github.farrukhjon.experiment.org.structure.analyzer.service;

import io.github.farrukhjon.experiment.org.structure.analyzer.model.ReportResult;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.function.Predicate;

/**
 * Base test class provides common testing functionalities to its subclasses.
 *
 * @author fsattorov
 */
public class BaseTest {

    protected File loadFileFromClasspath(final String testFileName) throws Exception {
        final URL testFileUri = this.getClass().getClassLoader().getResource(testFileName);
        if (testFileUri != null) {
            return Paths.get(testFileUri.toURI()).toFile();
        } else {
            throw new IOException(String.format("Test file %s is not loaded!", testFileName));
        }
    }

    protected Predicate<ReportResult> byFullName(final String fullName) {
        return reportResult -> reportResult.getEmployeeFullName().equals(fullName);
    }
}
