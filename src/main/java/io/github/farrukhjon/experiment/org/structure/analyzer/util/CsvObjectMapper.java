package io.github.farrukhjon.experiment.org.structure.analyzer.util;

import io.github.farrukhjon.experiment.org.structure.analyzer.model.Employee;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
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
            final List<String> lines = reader.lines().collect(Collectors.toList());
            if (lines.size() <= 1) {
                final String errMsg = String.format("Csv file %s is empty!%n", file.getAbsoluteFile());
                System.err.printf(errMsg);
                throw new IllegalArgumentException(errMsg);
            }
            return lines
                .stream()
                .skip(1)
                .filter(line -> !line.isEmpty())
                .map(this::toEmployee)
                .collect(Collectors.toMap(Employee::getId, Function.identity()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Maps a passed comma separated string line to {@link Employee} object.
     *
     * @param line string line.
     * @return object of {@link Employee} type.
     */
    private Employee toEmployee(final String line) {
        Objects.requireNonNull(line);
        final LinkedList<String> csvValues = Arrays.stream(line.split(",")).collect(Collectors.toCollection(LinkedList::new));
        final Employee employee = new Employee();
        employee.setId(Integer.parseInt(pollFirstAndRemove(csvValues, "ID value is required!")));
        employee.setFirstName(pollFirstAndRemove(csvValues, "First name value is required!"));
        employee.setLastName(pollFirstAndRemove(csvValues, "Last name value is required!"));
        final String salaryStr = csvValues.pollFirst();
        final double salary = salaryStr == null || salaryStr.isEmpty() ? Double.NaN : Double.parseDouble(salaryStr);
        if (Double.isNaN(salary)) {
            System.err.printf("Employee with ID %s has NaN salary!%n", employee.getId());
        }
        employee.setSalary(salary);
        final String managerId = csvValues.pollFirst();
        if (managerId != null && !managerId.isEmpty()) {
            employee.setManagerId(Integer.parseInt(managerId));
        }
        return employee;
    }

    private static String pollFirstAndRemove(final LinkedList<String> strings, final String errorMessage) {
        final String value = strings.pollFirst();
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(errorMessage);
        }
        return value;
    }

}
