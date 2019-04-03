package br.com.searchalgorithm.index.repositories;

import br.com.searchalgorithm.index.models.IndexModel;

import java.io.*;

public class IndexRepository {
    private static final String CUSTOM_EXTENSION_TYPE = "llabs";

    private String context;

    public IndexRepository(String context) {
        this.context = context;
    }

    public void save(IndexModel model) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(this.generatePath(model.getTerm()));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
        objectOutputStream.writeObject(model);
        objectOutputStream.close();
    }

    public IndexModel retrieve(String term) throws IOException, ClassNotFoundException {
        term = term.replace("/", "--");
        FileInputStream fileInputStream = new FileInputStream(this.generatePath(term));
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
        Object object = objectInputStream.readObject();
        objectInputStream.close();
        return (IndexModel) object;
    }

    private String generatePath(String term) {
        String basePath = String.format("%s/data", this.context);
        File base = new File(basePath);

        if(!base.exists()) {
            base.mkdirs();
        }

        return String.format("%s/%s.%s", basePath, term, CUSTOM_EXTENSION_TYPE);
    }
}
