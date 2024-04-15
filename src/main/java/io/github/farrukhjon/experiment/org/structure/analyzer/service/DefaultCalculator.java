package io.github.farrukhjon.experiment.org.structure.analyzer.service;

import io.github.farrukhjon.experiment.org.structure.analyzer.model.Employee;
import io.github.farrukhjon.experiment.org.structure.analyzer.model.ReportResult;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Default implementation of the {@link Calculator}.
 *
 * @author fsattorov
 */
public class DefaultCalculator implements Calculator {

    @Override
    public List<ReportResult> calculate(final Map<Integer, Employee> employees) {
        return employees.values()
            .stream()
            .filter(employee -> Objects.nonNull(employee.getManagerId()))
            .collect(
                Collectors.groupingBy(Employee::getManagerId, Collectors.mapping(defineReportingLineIds(employees), Collectors.toList()))
            )
            .entrySet()
            .stream()
            .map(managerIdToSubordinates -> {
                final Integer managerId = managerIdToSubordinates.getKey();
                final List<Employee> subordinates = managerIdToSubordinates.getValue();
                final Employee manager = employees.get(managerId);
                return this.calculate(manager, subordinates);
            })
            .collect(Collectors.toList());
    }

    private ReportResult calculate(final Employee manager, final List<Employee> subordinates) {
        Objects.requireNonNull(manager, "Passed manager object is null!");
        final ReportResult reportResult = new ReportResult();
        final double managerSalary = manager.getSalary();
        reportResult.setId(manager.getId());
        reportResult.setEmployeeFullName(manager.getFirstName() + " " + manager.getLastName());
        reportResult.setSalary(managerSalary);
        final OptionalDouble averageOpt = subordinates.stream().mapToDouble(Employee::getSalary).average();
        if (averageOpt.isPresent()) {
            final double averageSalaryOfSubordinates = averageOpt.getAsDouble();
            reportResult.setAverageSalaryOfSubordinates(averageSalaryOfSubordinates);
            final double salaryDifference = managerSalary - averageSalaryOfSubordinates;
            reportResult.setSalaryDifference(salaryDifference);
            reportResult.setSalaryPercentageDifference(
                String.format("%s%%", calculateSalaryPercentageDiff(averageSalaryOfSubordinates, salaryDifference)));
            reportResult.setSubordinates(subordinates);
            reportResult.setReportingLineIds(manager.getReportingLineIds());
        } else {
            System.err.printf("There is no average salary of subordinates for manager ID %s%n", manager.getId());
        }

        return reportResult;
    }

    private static double calculateSalaryPercentageDiff(final double averageSalaryOfSubordinates, final double salaryDifference) {
        final double salaryPercentDifference = (salaryDifference / averageSalaryOfSubordinates) * 100;
        return Math.floor(salaryPercentDifference * 100) / 100;
    }

    private static Function<Employee, Employee> defineReportingLineIds(final Map<Integer, Employee> employees) {
        return employee -> {
            Integer managerId = employee.getManagerId();
            while (managerId != null) {
                final Employee manager = employees.get(managerId);
                employee.getReportingLineIds().add(managerId);
                managerId = manager.getManagerId();
            }
            return employee;
        };
    }


}
