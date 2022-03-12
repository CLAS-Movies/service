package ro.unibuc.hello.service;

import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ro.unibuc.hello.data.*;
import ro.unibuc.hello.dto.MovieDTO;
import ro.unibuc.hello.dto.ReviewDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
class ReviewServiceTest {

    @Mock
    ReviewRepository reviewRepository;

    @Mock
    MovieRepository movieRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    ReviewService reviewService = new ReviewService();

    ReviewEntity review;
    ReviewEntity updatedReview;
    ReviewEntity reviewWithoutId;
    ReviewDTO reviewDTO;
    ReviewDTO updatedReviewDTO;
    ArrayList<ReviewDTO> reviewDTOs;
    ArrayList<ReviewEntity>reviewEntities;
    String deleteResponse;
    MovieEntity movie;
    UserEntity user;

    @BeforeEach
    void setUp(){
        movie = new MovieEntity("Dune", "Denis Villeneuve", "Jon Spaihts", 2021, 155, null, null);
        movie.setId("111111111111111111111111");
        user =  new UserEntity("Miclaus", "miclaus@mymail.com", null, null);
        user.setId("222222222222222222222222");
        review = new ReviewEntity("comment",7,movie, user);
        updatedReview = new ReviewEntity("UpdatedComment", 8, review.getMovie(), review.getUser());
        reviewWithoutId = new ReviewEntity(review.getComment(), review.getScore(), review.getMovie(), review.getUser());
        review.setId("62287c931be61542fed8cca1");
        updatedReview.setId(review.getId());
        deleteResponse = "Review with id " + review.getId() + " was deleted!";

        reviewDTO = new ReviewDTO(review);
        updatedReviewDTO = new ReviewDTO(updatedReview);

        reviewDTOs = new ArrayList<>();
        reviewEntities = new ArrayList<>();

        reviewDTOs.add(reviewDTO);
        reviewEntities.add(review);

    }


    @Test
    void getReviews() {
        doReturn(reviewEntities).when(reviewRepository).findAll();

        List<ReviewDTO> result = reviewService.getReviews();

        Assertions.assertEquals(result, reviewDTOs);
    }

    @Test
    void getReview() {
        when(reviewRepository.findById(String.valueOf(new ObjectId(review.getId())))).thenReturn(Optional.ofNullable(review));

        ReviewDTO result = reviewService.getReview(reviewDTO.getId());

        Assertions.assertEquals(result, reviewDTO);
    }

    @Test
    void insertReview() {
        when(movieRepository.findById(String.valueOf(new ObjectId(movie.getId())))).thenReturn(Optional.ofNullable(movie));
        when(userRepository.findById(String.valueOf(new ObjectId(user.getId())))).thenReturn(Optional.ofNullable(user));
        when(reviewRepository.save(reviewWithoutId)).thenReturn(review);

        ReviewDTO result = reviewService.insertReview(review.getComment(), review.getScore(), movie.getId(), user.getId());

        Assertions.assertEquals(result,reviewDTO);
    }

    @Test
    void updateReview() {
        when(reviewRepository.findById(String.valueOf(new ObjectId(review.getId())))).thenReturn(Optional.ofNullable(review));
        when(movieRepository.findById(String.valueOf(new ObjectId(movie.getId())))).thenReturn(Optional.ofNullable(movie));
        when(userRepository.findById(String.valueOf(new ObjectId(user.getId())))).thenReturn(Optional.ofNullable(user));
        when(reviewRepository.save(updatedReview)).thenReturn(updatedReview);

        ReviewDTO result = reviewService.updateReview(updatedReview.getId(), updatedReview.getComment(),updatedReview.getScore(), movie.getId(),updatedReview.getUser().getId());

        Assertions.assertEquals(result, updatedReviewDTO);




    }

    @Test
    void deleteReview() {
      String result = reviewService.deleteReview(review.getId());

      Assertions.assertEquals(result, deleteResponse);

    }
}