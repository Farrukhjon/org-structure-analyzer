package io.github.farrukhjon.experiment.org.structure.analyzer.model;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Client facing domain class (entity).
 *
 * @author fsattorov
 */
public final class ReportResult {

    private int id;

    private String employeeFullName;

    private double salary;

    private double averageSalaryOfSubordinates;

    private String salaryPercentageDifference;

    private double salaryDifference;

    private List<Employee> subordinates;

    private List<Integer> reportingLineIds;

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getEmployeeFullName() {
        return this.employeeFullName;
    }

    public void setEmployeeFullName(final String employeeFullName) {
        this.employeeFullName = employeeFullName;
    }

    public void setSalary(final double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setAverageSalaryOfSubordinates(final double averageSalaryOfSubordinates) {
        this.averageSalaryOfSubordinates = averageSalaryOfSubordinates;
    }

    public double getAverageSalaryOfSubordinates() {
        return this.averageSalaryOfSubordinates;
    }

    public void setSalaryPercentageDifference(final String salaryPercentDifference) {
        this.salaryPercentageDifference = salaryPercentDifference;
    }

    public double getSalaryDifference() {
        return this.salaryDifference;
    }

    public void setSalaryDifference(final double salaryDifference) {
        this.salaryDifference = salaryDifference;
    }

    public String getSalaryPercentageDifference() {
        return this.salaryPercentageDifference;
    }

    public List<Employee> getSubordinates() {
        return this.subordinates;
    }

    public void setSubordinates(final List<Employee> subordinates) {
        this.subordinates = subordinates;
    }

    public void setReportingLineIds(final List<Integer> reportingLineIds) {
        this.reportingLineIds = reportingLineIds;
    }

    public List<Integer> getReportingLineIds() {
        return this.reportingLineIds;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final ReportResult that = (ReportResult) o;
        return this.id == that.id && Objects.equals(this.employeeFullName, that.employeeFullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.employeeFullName);
    }

    @Override
    public String toString() {
        return new StringJoiner("\n", "\n" + this.employeeFullName + ": {\n", "\n}")
            .add("\tID: " + this.id)
            .add("\tSalary: " + this.salary)
            .add("\tDirect subordinates average salary: " + this.averageSalaryOfSubordinates)
            .add("\tSalary difference: " + this.salaryDifference)
            .add("\tSalary difference in percentage: " + this.salaryPercentageDifference)
            .add("\tDirect subordinates: " + this.subordinates)
            .add("\tReporting line IDs: " + this.reportingLineIds)
            .toString();
    }

}
