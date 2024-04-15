package io.github.farrukhjon.experiment.org.structure.analyzer;

import io.github.farrukhjon.experiment.org.structure.analyzer.model.ReportResult;
import io.github.farrukhjon.experiment.org.structure.analyzer.service.DefaultCalculator;
import io.github.farrukhjon.experiment.org.structure.analyzer.service.DefaultReporter;
import io.github.farrukhjon.experiment.org.structure.analyzer.service.Reporter;
import io.github.farrukhjon.experiment.org.structure.analyzer.util.CliOptionProvider;
import io.github.farrukhjon.experiment.org.structure.analyzer.util.CsvObjectMapper;
import java.io.File;
import java.util.List;

/**
 * The main class of the application.
 *
 * @author fsattorov
 */
public class Application {

    private final CliOptionProvider cliOptionProvider;

    private final Reporter reporter;

    public Application(final CliOptionProvider cliOptionProvider, final Reporter reporter) {
        this.cliOptionProvider = cliOptionProvider;
        this.reporter = reporter;
    }

    private void report() {
        final File inputFile = this.cliOptionProvider.getInputFile();
        final List<ReportResult> reportResults = this.reporter.generateReport(inputFile);
        System.out.println(reportResults);
    }

    public static void main(String[] args) {
        final Application application = new Application(
            new CliOptionProvider(args),
            new DefaultReporter(new CsvObjectMapper(), new DefaultCalculator())
        );
        application.report();
    }
}
