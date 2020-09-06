package com.mynetflix.db.model;

public class MoviePreview {

    private String posterPath;

    private String title;
    private String releaseDate;
    private String overview;

    public MoviePreview() {
    }

    public MoviePreview(String posterPath, String title, String releaseDate, String overview) {
        this.posterPath = posterPath;
        this.title = title;
        this.releaseDate = releaseDate;
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    @Override
    public String toString() {
        return "posterPath : " + posterPath +
                "\ntitle : " + title +
                "\nreleaseDate : " + releaseDate +
                "\noverview : " + overview +
                "\n-----------------------------------";
    }
}
