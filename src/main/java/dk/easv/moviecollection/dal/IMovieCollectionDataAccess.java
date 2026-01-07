package dk.easv.moviecollection.dal;

public interface iMovieCollectionDataAccess {

    void createMovie() throws Exception;

    void deleteMovie() throws Exception;

    void createCategory() throws Exception;

    void deleteCategory() throws Exception;
}
