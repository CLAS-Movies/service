package ro.unibuc.hello.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.unibuc.hello.data.*;
import ro.unibuc.hello.dto.ReviewDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ReviewDTO> getReviews() {
        ArrayList<ReviewDTO> reviewDTOs = new ArrayList<>();

        reviewRepository.findAll().forEach(reviewEntity -> reviewDTOs.add(new ReviewDTO(reviewEntity)));
        return reviewDTOs;
    }

    public ReviewDTO getReview(String id) {
        ReviewEntity review = reviewRepository.findById(String.valueOf(new ObjectId(id))).orElse(null);

        if(review != null)
            return new ReviewDTO(review);
        else
            return null;
    }

    public ReviewDTO insertReview(String comment,
                                  Integer score,
                                  String movieId,
                                  String userId) {
        ReviewEntity review =new ReviewEntity(comment,score);

        if(!movieId.isEmpty()) {
            MovieEntity movie = movieRepository.findById(String.valueOf(new ObjectId(movieId))).orElse(null);

            review.setMovie(movie);
        }
        else
            return null;

        if(!userId.isEmpty()) {
            UserEntity user = userRepository.findById(String.valueOf(new ObjectId(userId))).orElse(null);

            review.setUser(user);
        }
        else
            return null;
        return new ReviewDTO(reviewRepository.save(review));
    }

    public ReviewDTO updateReview(String id,
                                  String comment,
                                  Integer score,
                                  String movieId,
                                  String userId)
    {
        ReviewEntity review=reviewRepository.findById(String.valueOf(new ObjectId(id))).orElse(null);

        if(review!=null) {
            if (comment != null)
                review.setComment(comment);
            if (score != null)
                review.setScore(score);

            if(!movieId.isEmpty()) {
                MovieEntity movie = movieRepository.findById(String.valueOf(new ObjectId(movieId))).orElse(null);

                if(movie != null)
                    review.setMovie(movie);
            }

            if(!userId.isEmpty()) {
                UserEntity user= userRepository.findById(String.valueOf(new ObjectId(userId))).orElse(null);

                if(user != null)
                    review.setUser(user);
            }
            return new ReviewDTO(reviewRepository.save(review));
        }
        else
            return null;
    }


    public String deleteReview(String id){
        reviewRepository.deleteById(String.valueOf(new ObjectId(id)));
        return "Review with id " + id + " was deleted!";
    }


}
