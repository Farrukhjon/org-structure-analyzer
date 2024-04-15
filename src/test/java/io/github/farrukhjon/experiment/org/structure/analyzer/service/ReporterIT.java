package io.github.farrukhjon.experiment.org.structure.analyzer.service;

import io.github.farrukhjon.experiment.org.structure.analyzer.model.ReportResult;
import io.github.farrukhjon.experiment.org.structure.analyzer.util.CsvObjectMapper;
import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author fsattorov
 */
final class ReporterIT extends BaseTest {

    private final Reporter reporter = new DefaultReporter(new CsvObjectMapper(), new DefaultCalculator());

    @Test
    @DisplayName("Test generate report by loading and parsing a csv file to objects and validating calculated result")
    void testGenerateReport() throws Exception {
        //given:
        final String testFileName = "employees.csv";
        final URL testFileUri = this.getClass().getClassLoader().getResource(testFileName);
        Assertions.assertNotNull(testFileUri);
        final File file = Paths.get(testFileUri.toURI()).toFile();

        //when:
        final List<ReportResult> reportResults = this.reporter.generateReport(file);

        final Optional<ReportResult> joeDoeOpt = reportResults.stream().filter(this.byFullName("Joe Doe")).findFirst();
        final Optional<ReportResult> martinChekovOpt = reportResults.stream().filter(this.byFullName("Martin Chekov")).findFirst();
        final Optional<ReportResult> aliceHasacatOpt = reportResults.stream().filter(this.byFullName("Alice Hasacat")).findFirst();

        //then:
        Assertions.assertTrue(joeDoeOpt.isPresent());
        Assertions.assertTrue(martinChekovOpt.isPresent());
        Assertions.assertTrue(aliceHasacatOpt.isPresent());
        Assertions.assertEquals("30.43%", joeDoeOpt.get().getSalaryPercentageDifference());
        Assertions.assertEquals("-10.0%", martinChekovOpt.get().getSalaryPercentageDifference());
        Assertions.assertEquals("47.05%", aliceHasacatOpt.get().getSalaryPercentageDifference());
        final int expectedNumberOfManagers = 3;
        Assertions.assertEquals(expectedNumberOfManagers, reportResults.size());
    }

}