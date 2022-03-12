package ro.unibuc.hello.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ro.unibuc.hello.data.MovieEntity;
import ro.unibuc.hello.data.ReviewEntity;
import ro.unibuc.hello.data.UserEntity;

import java.util.Objects;


@SpringBootTest
class ReviewDTOTest {

    String id;
    String comment;
    Integer score;
    MovieEntity movieEntity;
    MovieDTO movie;
    UserEntity userEntity;
    UserDTO user;
    ReviewEntity review;
    ReviewDTO reviewDTO;
    ReviewDTO reviewDTO2;
    int hash;
    String stringReviewDTO;

    @BeforeEach
    void Setup(){
        id = "333333333333333333333333";
        comment = "comment";
        score = 7;
        movieEntity = new MovieEntity("Dune", "Denis Villeneuve", "Jon Spaihts", 2021, 155, null, null);
        movieEntity.setId("111111111111111111111111");
        movie = new MovieDTO(movieEntity);
        movie.setId("111111111111111111111111");
        userEntity = new UserEntity("Miclaus", "miclaus@yahoo.com", null , null);
        userEntity.setId("222222222222222222222222");
        user = new UserDTO(userEntity);
        user.setId("222222222222222222222222");
        review = new ReviewEntity(comment, score, movieEntity, userEntity);
        reviewDTO = new ReviewDTO();
        reviewDTO2 = new ReviewDTO(review);
        reviewDTO.setId("333333333333333333333333");
        reviewDTO.setComment("comment");
        reviewDTO.setScore(score);
        reviewDTO.setMovie(movie);
        reviewDTO.setUser(user);
        reviewDTO2.setId(id);
        hash = Objects.hash(id, comment, score, movie, user);
        stringReviewDTO = "ReviewDTO{" +
                "id='" + id + '\'' +
                ", comment='" + comment + '\'' +
                ", score=" + score +
                ", movie=" + movie +
                ", user=" + user +
                '}';

    }

    @Test
    void getMovie() {
        Assertions.assertEquals(movie, reviewDTO.getMovie());
    }

    @Test
    void getUser() {
        Assertions.assertEquals(user, reviewDTO.getUser());
    }


    @Test
    void getId() {
        Assertions.assertEquals(id, reviewDTO.getId());
    }


    @Test
    void getComment() {
        Assertions.assertEquals(comment, reviewDTO.getComment());
    }


    @Test
    void getScore(){
        Assertions.assertEquals(score, reviewDTO.getScore());
    }


    @Test
    void testEquals() {
        Assertions.assertEquals(reviewDTO, reviewDTO2);
    }

    @Test
    void testHashCode() {
        Assertions.assertEquals(hash, reviewDTO.hashCode());
    }

    @Test
    void testToString() {
        Assertions.assertEquals(stringReviewDTO, reviewDTO.toString());
    }
}