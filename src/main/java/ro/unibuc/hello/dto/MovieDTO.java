package ro.unibuc.hello.dto;

import org.springframework.data.annotation.Id;
import ro.unibuc.hello.data.MovieEntity;

import java.util.ArrayList;
import java.util.Objects;

public class MovieDTO {
    @Id
    private String id;
    private String title;
    private String director;
    private String writer;
    private Integer year;
    private Integer duration;
    private ArrayList<ReviewDTO> reviews;
    private ArrayList<WatchItemDTO> watchItems;

    public MovieDTO() {
    }

    public MovieDTO(MovieEntity movie){
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.director = movie.getDirector();
        this.writer = movie.getWriter();
        this.year = movie.getYear();
        this.duration = movie.getDuration();
        ArrayList<ReviewDTO> reviewDTOs = new ArrayList<>();
        movie.getReviews().forEach(reviewEntity -> reviewDTOs.add(new ReviewDTO(reviewEntity)));
        this.reviews = reviewDTOs;
        ArrayList<WatchItemDTO> watchItemDTOs = new ArrayList<>();
        movie.getWatchItems().forEach(watchItemEntity -> watchItemDTOs.add(new WatchItemDTO(watchItemEntity)));
        this.watchItems = watchItemDTOs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public ArrayList<ReviewDTO> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<ReviewDTO> reviews) {
        this.reviews = reviews;
    }

    public ArrayList<WatchItemDTO> getWatchItems() {
        return watchItems;
    }

    public void setWatchItems(ArrayList<WatchItemDTO> watchItems) {
        this.watchItems = watchItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieDTO movieDTO = (MovieDTO) o;
        return id.equals(movieDTO.id) && title.equals(movieDTO.title) && director.equals(movieDTO.director) && writer.equals(movieDTO.writer) && year.equals(movieDTO.year) && duration.equals(movieDTO.duration) && Objects.equals(reviews, movieDTO.reviews) && Objects.equals(watchItems, movieDTO.watchItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, director, writer, year, duration, reviews, watchItems);
    }

    @Override
    public String toString() {
        return "MovieDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", writer='" + writer + '\'' +
                ", year=" + year +
                ", duration=" + duration +
                ", reviews=" + reviews +
                ", watchItems=" + watchItems +
                '}';
    }
}
