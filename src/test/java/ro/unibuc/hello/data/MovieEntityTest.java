package ro.unibuc.hello.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieEntityTest {

    String id;
    String title;
    String director;
    String writer;
    Integer year;
    Integer duration;
    ArrayList<ReviewEntity> reviews;
    ArrayList<WatchItemEntity> watchItems;
    MovieEntity movie;
    MovieEntity movie2;
    int hash;
    String movieString;

    @BeforeEach
    void setUp(){
        id = "111111111111111111111111";
        title = "Dune";
        director = "Denis Villeneuve";
        writer = "Jon Spaihts";
        year = 2021;
        duration = 155;
        reviews = new ArrayList<>();
        watchItems = new ArrayList<>();
        movie = new MovieEntity();
        movie2 = new MovieEntity(title, director, writer, year, duration, reviews, watchItems);
        hash = Objects.hash(id, title, director, writer, year, duration, reviews, watchItems);
        movieString = "MovieEntity{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", writer='" + writer + '\'' +
                ", year=" + year +
                ", duration=" + duration + " minutes}";

        movie.setId(id);
        movie.setTitle(title);
        movie.setDirector(director);
        movie.setWriter(writer);
        movie.setYear(year);
        movie.setDuration(duration);
        movie.setReviews(reviews);
        movie.setWatchItems(watchItems);
        movie2.setId(id);
    }

    @Test
    void getId() {
        Assertions.assertEquals(id, movie.getId());
    }

    @Test
    void getTitle() {
        Assertions.assertEquals(title, movie.getTitle());
    }

    @Test
    void getDirector() {
        Assertions.assertEquals(director, movie.getDirector());

    }

    @Test
    void getWriter() {
        Assertions.assertEquals(writer, movie.getWriter());
    }

    @Test
    void getYear() {
        Assertions.assertEquals(year, movie.getYear());
    }

    @Test
    void getDuration() {
        Assertions.assertEquals(duration, movie.getDuration());
    }

    @Test
    void getReviews() {
        Assertions.assertEquals(reviews, movie.getReviews());

    }

    @Test
    void getWatchItems() {
        Assertions.assertEquals(watchItems, movie.getWatchItems());

    }

    @Test
    void testEquals() {
        Assertions.assertEquals(movie, movie2);
    }

    @Test
    void testHashCode() {
        Assertions.assertEquals(hash, movie.hashCode());
    }

    @Test
    void testToString() {
        Assertions.assertEquals(movieString, movie.toString());
    }
}