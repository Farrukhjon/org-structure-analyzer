package io.github.farrukhjon.experiment.org.structure.analyzer.service;

import io.github.farrukhjon.experiment.org.structure.analyzer.model.Employee;
import io.github.farrukhjon.experiment.org.structure.analyzer.model.ReportResult;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author fsattorov
 */
final class CalculatorTest extends BaseTest {

    private final Calculator calculator = new DefaultCalculator();

    @Test
    @DisplayName("Test calculate salary percentage difference between a manager and its subordinates")
    void testCalculate() {
        //given:
        final Map<Integer, Employee> testEmployees = createTestEmployeesWithSingleManager();

        //when:
        final List<ReportResult> reportResultList = this.calculator.calculate(testEmployees);
        final Optional<ReportResult> joeDoeOpt = reportResultList.stream().filter(this.byFullName("Joe Doe")).findFirst();
        final Optional<ReportResult> martinChekovOpt = reportResultList.stream().filter(this.byFullName("Martin Chekov")).findFirst();

        //then:
        Assertions.assertTrue(joeDoeOpt.isPresent());
        Assertions.assertTrue(martinChekovOpt.isPresent());
        Assertions.assertEquals(2, reportResultList.size());
        Assertions.assertEquals(14000, joeDoeOpt.get().getSalaryDifference());
        Assertions.assertEquals(46000, joeDoeOpt.get().getAverageSalaryOfSubordinates());
        Assertions.assertEquals("30.43%", joeDoeOpt.get().getSalaryPercentageDifference());
        final Optional<Employee> aliceHasacat =
            martinChekovOpt.get().getSubordinates().stream().filter(employee -> employee.getId() == 300).findFirst();
        Assertions.assertTrue(aliceHasacat.isPresent());
        Assertions.assertEquals(2, aliceHasacat.get().getReportingLineIds().size());
    }

    private static Map<Integer, Employee> createTestEmployeesWithSingleManager() {
        final Employee manager = new Employee();
        manager.setId(123);
        manager.setFirstName("Joe");
        manager.setLastName("Doe");
        manager.setSalary(60000);
        final Employee martinChekov = new Employee();
        martinChekov.setId(124);
        martinChekov.setFirstName("Martin");
        martinChekov.setLastName("Chekov");
        martinChekov.setSalary(45000);
        martinChekov.setManagerId(manager.getId());
        final Employee bobRonstad = new Employee();
        bobRonstad.setId(125);
        bobRonstad.setFirstName("Bob");
        bobRonstad.setLastName("Ronstad");
        bobRonstad.setSalary(47000);
        bobRonstad.setManagerId(manager.getId());
        final Employee aliceHasacat = new Employee();
        aliceHasacat.setId(300);
        aliceHasacat.setFirstName("Alice");
        aliceHasacat.setLastName("Hasacat");
        aliceHasacat.setSalary(50000);
        aliceHasacat.setManagerId(martinChekov.getId());
        return Map.of(
            manager.getId(), manager,
            martinChekov.getId(), martinChekov,
            bobRonstad.getId(), bobRonstad,
            aliceHasacat.getId(), aliceHasacat
        );
    }
}