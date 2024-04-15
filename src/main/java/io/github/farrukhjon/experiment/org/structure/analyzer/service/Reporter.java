package io.github.farrukhjon.experiment.org.structure.analyzer.service;

import io.github.farrukhjon.experiment.org.structure.analyzer.model.ReportResult;
import java.io.File;
import java.util.List;

/**
 * The interface is a service for the application.
 *
 * @author fsattorov
 */
public interface Reporter {

    /**
     * Consumes a file as a data source and generates a report type {@link List} of {@link ReportResult}.
     *
     * @param employeesFile a file containing domain data.
     * @return list of {@link ReportResult}.
     */
    List<ReportResult> generateReport(final File employeesFile);
}
