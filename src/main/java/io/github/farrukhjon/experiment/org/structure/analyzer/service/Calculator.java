package io.github.farrukhjon.experiment.org.structure.analyzer.service;

import io.github.farrukhjon.experiment.org.structure.analyzer.model.Employee;
import io.github.farrukhjon.experiment.org.structure.analyzer.model.ReportResult;
import java.util.List;
import java.util.Map;

/**
 * This interface is a service for the application.
 *
 * @author fsattorov
 */
public interface Calculator {

    /**
     * Implementation should provide a calculated result of reports {@link ReportResult}.
     *
     * @param employees a mapping of employee ID and employee domain entities.
     * @return list of calculated {@link ReportResult}.
     */
    List<ReportResult> calculate(Map<Integer, Employee> employees);
}
