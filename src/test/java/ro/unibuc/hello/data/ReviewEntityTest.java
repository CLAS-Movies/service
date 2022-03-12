package ro.unibuc.hello.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewEntityTest {

    String id;
    String comment;
    Integer score;
    MovieEntity movie;
    ArrayList<ReviewEntity> reviews;
    ArrayList<WatchItemEntity> watchItems;
    UserEntity user;
    ReviewEntity review;
    ReviewEntity review2;
    int hash;
    String reviewString;

    @BeforeEach
    void setUp() {
                id="333333333333333333333333";
                comment = "comment";
                score = 7;
                movie = new MovieEntity("Dune","Denis Villeneuve","Jon Spaihts",2021,155, null,null);
                movie.setId("111111111111111111111111");
                reviews = new ArrayList<>();
                watchItems = new ArrayList<>();
                user = new UserEntity("Miclaus", "miclaus@yahoo.com", reviews, watchItems);
                user.setId("222222222222222222222222");
                review = new ReviewEntity();
                review.setId(id);
                review.setComment(comment);
                review.setScore(score);
                review.setUser(user);
                review.setMovie(movie);
                review2 = new ReviewEntity(comment, score, movie, user);
                review2.setId(id);
                hash = Objects.hash(id, comment, score, movie, user);
                reviewString = "ReviewEntity{" +
                        "id='" + id + '\'' +
                        ", comment='" + comment + '\'' +
                        ", score=" + score +
                        '}';
    }

    @Test
    void getId() {
        Assertions.assertEquals(id, review.getId());
    }

    @Test
    void getComment() {
        Assertions.assertEquals(comment, review.getComment());
    }

    @Test
    void getScore() {
        Assertions.assertEquals(score, review.getScore());
    }

    @Test
    void getMovie() {
        Assertions.assertEquals(movie, review.getMovie());

    }

    @Test
    void getUser() {
        Assertions.assertEquals(user, review.getUser());
    }

    @Test
    void testEquals() {
        Assertions.assertEquals(review, review2);
    }

    @Test
    void testHashCode() {
        Assertions.assertEquals(hash, review.hashCode());
    }

    @Test
    void testToString() {
        Assertions.assertEquals(reviewString, review.toString());
    }
}