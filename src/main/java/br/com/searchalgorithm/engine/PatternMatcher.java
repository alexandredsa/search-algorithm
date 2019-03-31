package br.com.searchalgorithm.engine;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PatternMatcher {
    private Path path;
    private String[] terms;

    public PatternMatcher(Path path, String[] terms) {
        this.path = path;
        this.terms = terms;
    }


    public boolean exists() throws FileNotFoundException {
        List<String> remainingTerms = Arrays.asList(this.terms);
        final Scanner scanner = new Scanner(this.path.toFile());
        while (scanner.hasNextLine()) {
            final String line = scanner.nextLine();

            remainingTerms = remainingTerms
                    .stream()
                    .filter(w -> !line.contains(w))
                    .collect(Collectors.toList());

            if(remainingTerms.size() == 0) {
                return true;
            }
        }

        return false;
    }
}