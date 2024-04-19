package io.github.farrukhjon.experiment.org.structure.analyzer.util;

import io.github.farrukhjon.experiment.org.structure.analyzer.model.Employee;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
                .filter(Objects::nonNull)
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
        final String[] fields = line.split(",");
        final Employee employee = new Employee();
        final int idIdx = 0;
        if (this.hasIndex(fields, idIdx)) {
            employee.setId(Integer.parseInt(fields[idIdx]));
        } else {
            System.err.printf("Failed to map %s string line to object type of %s%n", line, Employee.class.getName());
            return null;
        }
        final int fNameIdx = 1;
        if (this.hasIndex(fields, fNameIdx)) {
            employee.setFirstName(fields[fNameIdx]);
        }
        final int lNameIdx = 2;
        if (this.hasIndex(fields, lNameIdx)) {
            employee.setLastName(fields[lNameIdx]);
        }
        final int salaryIdx = 3;
        if (this.hasIndex(fields, salaryIdx)) {
            final String salaryStr = fields[salaryIdx];
            final double salary = salaryStr == null || salaryStr.isEmpty() ? Double.NaN : Double.parseDouble(salaryStr);
            if (Double.isNaN(salary)) {
                System.err.printf("Employee with ID %s has NaN salary!%n", employee.getId());
            }
            employee.setSalary(salary);
        }
        final int managerIdIdx = 4;
        if (this.hasIndex(fields, managerIdIdx)) {
            employee.setManagerId(Integer.parseInt(fields[managerIdIdx]));
        }
        return employee;
    }

    private boolean hasIndex(final String[] array, final int index) {
        try {
            Objects.checkIndex(index, array.length);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }
}
