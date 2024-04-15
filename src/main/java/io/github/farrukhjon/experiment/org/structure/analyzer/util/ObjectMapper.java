package io.github.farrukhjon.experiment.org.structure.analyzer.util;

import io.github.farrukhjon.experiment.org.structure.analyzer.model.Employee;
import java.io.File;
import java.util.Map;

/**
 * Simple object mapper interface.
 *
 * @author fsattorov
 */
public interface ObjectMapper {

    /**
     * Maps content of the passed file to Map of {@link Employee} by mapping employee IDs in keys.
     *
     * @param file a file.
     * @return {@link Map} of employee IDs in keys to {@link Employee} in values.
     */
    Map<Integer, Employee> toEmployees(File file);

}
