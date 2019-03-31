import br.com.searchalgorithm.engine.SearchExecutor;
import br.com.searchalgorithm.file.FileReader;

import java.nio.file.Path;
import java.util.List;

public class Main {
    private static final int BATCH_SIZE = 200;

    public static void main(String[] args) {
        List<Path> filenames = FileReader.getFilenames("/Users/alexandre/zProjects/lulabs/data");
        SearchExecutor.with(filenames, new String[]{"test"}, BATCH_SIZE).run();
        System.out.println(args[0]);
    }
}
