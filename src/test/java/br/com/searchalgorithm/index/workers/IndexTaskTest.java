package br.com.searchalgorithm.index.workers;

import br.com.searchalgorithm.index.io.utils.IndexFileReader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class IndexTaskTest {
    @Mock
    IndexFileReader indexFileReader;

    @Test
    public void call() throws FileNotFoundException {
        List<Path> pathList = new ArrayList<>();
        pathList.add(Paths.get("/fake/path/foo.txt"));
        List<String> words = Arrays.asList("walt", "disney", "movie", "1988");
        TreeSet<String> result = new TreeSet<>(words);
        Mockito.when(indexFileReader.wordsFromFile(Mockito.any())).thenReturn(result);

        IndexTask indexTask = IndexTask.with(pathList, indexFileReader);
        Map<String, List<String>> taskResult = indexTask.call();
        Assert.assertEquals(taskResult.size(), 4);
        Assert.assertEquals(taskResult.get("walt").get(0), "foo.txt");
    }
}