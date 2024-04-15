package io.github.farrukhjon.experiment.org.structure.analyzer.util;

import io.github.farrukhjon.experiment.org.structure.analyzer.model.Employee;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Simple object mapper implementation of {@link ObjectMapper} from CSV file to Employee entity.
 *
 * @author fsattorov
 */
public class CsvObjectMapper implements ObjectMapper {

    /**
     * Maps content of the passed CSV file to Map of {@link Employee} by mapping employee IDs in keys.
     *
     * @param file CSV file.
     * @return {@link Map} of employee IDs in keys to {@link Employee} in values.
     */
    @Override
    public Map<Integer, Employee> toEmployees(final File file) {
        Objects.requireNonNull(file, "Passed file for mapping must not be null!");
        try (final BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader
                .lines()
                .skip(1)
                .map(line -> {
                    final String[] fields = line.split(",");
                    final Employee employee = new Employee();
                    employee.setId(Integer.parseInt(fields[0]));
                    employee.setFirstName(fields[1]);
                    employee.setLastName(fields[2]);
                    employee.setSalary(Double.parseDouble(fields[3]));
                    if (fields.length == 5) {
                        employee.setManagerId(Integer.parseInt(fields[4]));
                    }
                    return employee;
                })
                .collect(Collectors.toMap(Employee::getId, Function.identity()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
