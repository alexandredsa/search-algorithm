package br.com.searchalgorithm.index.models;

import java.io.Serializable;
import java.util.TreeSet;

public class IndexModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String term;
    private TreeSet<String> filenames;

    public IndexModel(String term, TreeSet<String> filenames) {
        this.term = term;
        this.filenames = filenames;
    }

    public IndexModel() {
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public TreeSet<String> getFilenames() {
        return filenames;
    }

    public void setFilenames(TreeSet<String> filenames) {
        this.filenames = filenames;
    }

    @Override
    public String toString() {
        return String.format("term : %s\nfilenames : %s", this.term,
                this.filenames.stream().reduce(";", String::concat));
    }
}
