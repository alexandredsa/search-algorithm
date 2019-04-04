package br.com.searchalgorithm.index.models;

import java.io.Serializable;
import java.util.Objects;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndexModel that = (IndexModel) o;
        return Objects.equals(term, that.term) &&
                Objects.equals(filenames, that.filenames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(term, filenames);
    }

    @Override
    public String toString() {
        return String.format("term : %s\nfilenames : %s", this.term,
                this.filenames.stream().reduce(";", String::concat));
    }
}
