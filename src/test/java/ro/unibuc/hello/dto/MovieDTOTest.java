package ro.unibuc.hello.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ro.unibuc.hello.data.MovieEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieDTOTest {

    String id;
    String title;
    String director;
    String writer;
    Integer year;
    Integer duration;
    MovieDTO movieDTO;
    MovieDTO movieDTO2;
    int hash;
    String movieStringDTO;

    @BeforeEach
    void setUp(){
        id = "111111111111111111111111";
        title = "Dune";
        director = "Denis Villeneuve";
        writer = "Jon Spaihts";
        year = 2021;
        duration = 155;
        movieDTO = new MovieDTO();
        movieDTO2 = new MovieDTO(new MovieEntity(title, director, writer, year, duration));
        hash = Objects.hash(id, title, director, writer, year, duration);
        movieStringDTO = "MovieDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", writer='" + writer + '\'' +
                ", year=" + year +
                ", duration=" + duration +
                '}';

        movieDTO.setId(id);
        movieDTO.setTitle(title);
        movieDTO.setDirector(director);
        movieDTO.setWriter(writer);
        movieDTO.setYear(year);
        movieDTO.setDuration(duration);
        movieDTO2.setId(id);



    }

    @Test
    void getId() {
        Assertions.assertEquals(id, movieDTO.getId());
    }

    @Test
    void getTitle() {
        Assertions.assertEquals(title, movieDTO.getTitle());
    }

    @Test
    void getDirector() {
        Assertions.assertEquals(director, movieDTO.getDirector());
    }

    @Test
    void getWriter() {
        Assertions.assertEquals(writer, movieDTO.getWriter());
    }

    @Test
    void getYear() {
        Assertions.assertEquals(year, movieDTO.getYear());
    }

    @Test
    void getDuration() {
        Assertions.assertEquals(duration, movieDTO.getDuration());
    }

    @Test
    void testEquals() {
        Assertions.assertEquals(movieDTO, movieDTO2);
    }

    @Test
    void testHashCode() {
        Assertions.assertEquals(hash, movieDTO.hashCode());
    }

    @Test
    void testToString() {
        Assertions.assertEquals(movieStringDTO, movieDTO.toString());
    }
}