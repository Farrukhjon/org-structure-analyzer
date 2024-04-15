package io.github.farrukhjon.experiment.org.structure.analyzer.service;

import io.github.farrukhjon.experiment.org.structure.analyzer.model.Employee;
import io.github.farrukhjon.experiment.org.structure.analyzer.model.ReportResult;
import io.github.farrukhjon.experiment.org.structure.analyzer.util.ObjectMapper;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Default implementation of {@link Reporter} interface.
 *
 * @author fsattorov
 */
public class DefaultReporter implements Reporter {

    private final Calculator calculator;

    private final ObjectMapper objectMapper;

    public DefaultReporter(ObjectMapper objectMapper, Calculator calculator) {
        this.objectMapper = objectMapper;
        this.calculator = calculator;
    }

    /**
     * Parses employeesFile to the map of objects of type {@link Employee} and generates report by calling {@link Calculator#calculate(Map)}.
     *
     * @param employeesFile a file containing domain data.
     * @return list of {@link ReportResult}.
     */
    @Override
    public List<ReportResult> generateReport(final File employeesFile) {
        Objects.requireNonNull(employeesFile, "Passed employees file is null!");
        final Map<Integer, Employee> employees = this.objectMapper.toEmployees(employeesFile);
        return this.calculator.calculate(employees);
    }

}
