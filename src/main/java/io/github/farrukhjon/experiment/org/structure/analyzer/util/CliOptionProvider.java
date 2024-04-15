package io.github.farrukhjon.experiment.org.structure.analyzer.util;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * This class parses and provides passed application arguments into internal usage.
 *
 * @author fsattorov
 */
public class CliOptionProvider {

    private final Map<String, String> options;

    public CliOptionProvider(final String[] args) {
        this.options = this.parseOption(args);
    }

    /**
     * Returns {@link File} from associated passed arguments (--file/-f).
     *
     * @return {@link File}.
     */
    public File getInputFile() {
        final String longOpt = this.options.get("--file");
        final String fileName = longOpt != null ? longOpt : this.options.get("-f");
        if (fileName == null) {
            this.usage();
        }
        return Paths.get(fileName).toFile();
    }

    /**
     * Parses program's options into {@link Map<>} of string keys and string values.
     *
     * @param args {@link String[]} of program's arguments.
     * @return {@link Map<>} of string keys and string values.
     */
    private Map<String, String> parseOption(final String[] args) {
        this.validateArgs(args);
        final Map<String, String> options = new HashMap<>();
        for (int i = 0, j = 1; i < args.length && j < args.length; i++, j++) {
            final String optKey = args[i];
            final String optValue = args[j];
            options.put(optKey, optValue);
        }
        return options;
    }

    /**
     * Performs some simple validation of passed program arguments.
     *
     * @param args {@link String[]} of program's arguments.
     */
    private void validateArgs(final String[] args) {
        if (args.length == 0) {
            this.usage();
        }
        final boolean evenLength = args.length % 2 == 0;
        if (!evenLength) {
            throw new IllegalArgumentException("Program arguments should be a pair of options");
        }
        for (int i = 0; i < args.length; i++) {
            boolean evenIndex = i % 2 == 0;
            final String optKey = args[i];
            if (evenIndex && !optKey.startsWith("-")) {
                throw new IllegalArgumentException("Program argument's key option must be started either with '-' or '--'");
            }
        }
    }

    /**
     * Usage hint.
     */
    private void usage() {
        System.err.print("Usage: java -jar org-structure-analyzer-<version>.jar --file/-f <path to domain csv file>");
        System.exit(-1);
    }
}
