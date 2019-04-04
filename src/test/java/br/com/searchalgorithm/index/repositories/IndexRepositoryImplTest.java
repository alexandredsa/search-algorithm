package br.com.searchalgorithm.index.repositories;

import br.com.searchalgorithm.index.io.IndexFileManager;
import br.com.searchalgorithm.index.models.IndexModel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class IndexRepositoryImplTest {
    IndexRepository indexRepository;

    @Mock
    IndexFileManager fileManager;

    @Captor
    ArgumentCaptor<String> captorString;

    @Captor
    ArgumentCaptor<IndexModel> captorIndexModel;

    @Test
    public void save() throws IOException {
        final String baseContext = "/path/fake/data";
        this.indexRepository = new IndexRepositoryImpl(baseContext, fileManager);
        IndexModel indexModel = new IndexModel();
        indexModel.setTerm("test");
        this.indexRepository.save(indexModel);
        final String expected = String.format("%s/data/%s.%s", baseContext, indexModel.getTerm(), IndexRepositoryImpl.CUSTOM_EXTENSION_TYPE);
        Mockito.verify(fileManager).write(captorString.capture(), captorIndexModel.capture());
        Assert.assertEquals(expected, captorString.getValue());
    }

    @Test
    public void retrieve() {
    }
}