package br.com.searchalgorithm.engine;

import br.com.searchalgorithm.file.FileReader;
import org.junit.Test;

import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static org.junit.Assert.*;

public class SearchExecutorTest {

    private static final int BATCH_SIZE = 200;

    @Test(timeout = 1)
    public void run() {
        List<Path> filenames = FileReader.getFilenames("/Users/alexandre/zProjects/lulabs/data");
        List<String> result = SearchExecutor.with(filenames, new String[]{"from", "year"}, BATCH_SIZE).run();
    }
}