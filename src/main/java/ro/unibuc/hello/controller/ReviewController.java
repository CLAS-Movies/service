package ro.unibuc.hello.controller;

import java.util.List;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.hello.dto.ReviewDTO;
import ro.unibuc.hello.service.ReviewService;

@RestController
public class ReviewController {

@Autowired
 private ReviewService reviewService;

    @Autowired
    MeterRegistry metricsRegistry;

    @GetMapping("/review/getAll")
    @ResponseBody
    @Timed(value = "review.getReviews.time", description = "Time taken to return reviews")
    @Counted(value = "review.getReviews.count", description = "Times reviews were returned")
    public List<ReviewDTO> getReviews() {
        return reviewService.getReviews();
    }

    @GetMapping("/review/get")
    @ResponseBody
    @Timed(value = "review.getReview.time", description = "Time taken to return a review")
    @Counted(value = "review.getReview.count", description = "Times a review was returned")
    public ReviewDTO getReview(@RequestParam (name="id") String id) {
        return reviewService.getReview(id);
    }

    @PostMapping("/review/insert")
    @ResponseBody
    @Timed(value = "review.insertReview.time", description = "Time taken to insert a review")
    @Counted(value = "review.insertReview.count", description = "Times a review was inserted")
    public ReviewDTO insertReview(@RequestParam(name="comment") String comment,
                                     @RequestParam(name="score") Integer score,
                                     @RequestParam(name="movieId") String movieId,
                                     @RequestParam(name="userId") String userId) {
        return reviewService.insertReview(comment, score, movieId, userId);
    }

    @PutMapping("/review/update")
    @ResponseBody
    @Timed(value = "review.updateReview.time", description = "Time taken to update a review")
    @Counted(value = "review.updateReview.count", description = "Times a review was updated")
    public ReviewDTO updateReview(@RequestParam(name="id") String id,
                                  @RequestParam(name="comment",required = false) String comment,
                                  @RequestParam(name="score",required = false) Integer score,
                                  @RequestParam(name="movieId",required = false) String movieId,
                                  @RequestParam(name="userId", required = false) String userId)
    {
        return reviewService.updateReview(id, comment, score, movieId, userId);
    }

    @DeleteMapping("/review/delete")
    @ResponseBody
    @Timed(value = "review.deleteReview.time", description = "Time taken to delete a review")
    @Counted(value = "review.deleteReview.count", description = "Times a review was deleted")
    public String deleteReview(@RequestParam(name="id") String id){
        return reviewService.deleteReview(id);
    }
}
