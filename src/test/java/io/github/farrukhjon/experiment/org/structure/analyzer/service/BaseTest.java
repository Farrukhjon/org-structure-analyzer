package io.github.farrukhjon.experiment.org.structure.analyzer.service;

import io.github.farrukhjon.experiment.org.structure.analyzer.model.ReportResult;
import java.util.function.Predicate;

/**
 * @author fsattorov
 */
public class BaseTest {


    protected Predicate<ReportResult> byFullName(final String fullName) {
        return reportResult -> reportResult.getEmployeeFullName().equals(fullName);
    }
}
