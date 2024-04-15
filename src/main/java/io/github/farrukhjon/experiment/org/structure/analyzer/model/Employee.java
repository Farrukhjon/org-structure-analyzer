package io.github.farrukhjon.experiment.org.structure.analyzer.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * The main domain class.
 *
 * @author fsattorov
 */
public final class Employee {

    private int id;

    private String firstName;

    private String lastName;

    private double salary;

    private Integer managerId;

    private List<Integer> reportingLineIds = new ArrayList<>();

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(final double salary) {
        this.salary = salary;
    }

    public Integer getManagerId() {
        return this.managerId;
    }

    public void setManagerId(final Integer managerId) {
        this.managerId = managerId;
    }

    public List<Integer> getReportingLineIds() {
        return this.reportingLineIds;
    }

    public void setReportingLineIds(final List<Integer> reportingLineIds) {
        this.reportingLineIds = reportingLineIds;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final Employee employee = (Employee) o;
        return this.id == employee.id && Objects.equals(this.firstName, employee.firstName) &&
            Objects.equals(this.lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstName, this.lastName);
    }

    @Override
    public String toString() {
        return new StringJoiner("\n", this.firstName + " " + this.lastName + ": {\n", "}")
            .add("\t\tID: " + this.id)
            .add("\t\tSalary: " + this.salary)
            .add("\t\tReporting line IDs: " + this.reportingLineIds)
            .add("\t\tReporting lines size: " + this.reportingLineIds.size())
            .toString();
    }
}
