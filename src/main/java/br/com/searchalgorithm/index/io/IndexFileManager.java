package br.com.searchalgorithm.index.io;

import br.com.searchalgorithm.index.models.IndexModel;

import java.io.*;

public class IndexFileManager implements FileManager{
    @Override
    public void write(String path, IndexModel model) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
        objectOutputStream.writeObject(model);
        objectOutputStream.close();
    }

    @Override
    public Object read(String path) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(path);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
        Object object = objectInputStream.readObject();
        objectInputStream.close();
        return object;
    }
}
