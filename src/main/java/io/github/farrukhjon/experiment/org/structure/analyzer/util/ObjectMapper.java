package io.github.farrukhjon.experiment.org.structure.analyzer.util;

import io.github.farrukhjon.experiment.org.structure.analyzer.model.Employee;
import java.io.File;
import java.util.Map;

/**
 * @author fsattorov
 */
public interface ObjectMapper {

    Map<Integer, Employee> toEmployees(File file);

}
