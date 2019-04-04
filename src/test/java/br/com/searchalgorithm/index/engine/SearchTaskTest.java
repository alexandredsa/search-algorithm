package br.com.searchalgorithm.index.engine;

import br.com.searchalgorithm.index.Search;
import br.com.searchalgorithm.index.models.IndexModel;
import br.com.searchalgorithm.index.repositories.IndexRepositoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SearchTaskTest {
    @Mock
    IndexRepositoryImpl indexRepository;

    @Test
    public void call() throws IOException, ClassNotFoundException {
        IndexModel indexModel = new IndexModel();
        indexModel.setTerm("test");

        TreeSet<String> filenames = new TreeSet<>(Arrays.asList("foo.txt", "foo_2.txt"));
        indexModel.setFilenames(filenames);

        Mockito.when(indexRepository.retrieve(Mockito.anyString())).thenReturn(indexModel);
        SearchTask searchTask = SearchTask.with(indexRepository, Mockito.anyString());
        Set<String> result = searchTask.call();
        assertEquals(result.size(), 2);
    }
}