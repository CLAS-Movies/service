package ro.unibuc.hello.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.hello.dto.ReviewDTO;
import ro.unibuc.hello.service.ReviewService;

@RestController
public class ReviewController {

@Autowired
 private ReviewService reviewService;

    @GetMapping("/review/getAll")
    @ResponseBody
    public List<ReviewDTO> getReviews() {
        return reviewService.getReviews();
    }

    @GetMapping("/review/get")
    @ResponseBody
    public ReviewDTO getReview(@RequestParam (name="id") String id) {
        return reviewService.getReview(id);
    }

    @PostMapping("/review/insert")
    @ResponseBody
    public ReviewDTO insertReview(@RequestParam(name="comment") String comment,
                                     @RequestParam(name="score") Integer score,
                                     @RequestParam(name="movieId") String movieId,
                                     @RequestParam(name="userId") String userId) {
        return reviewService.insertReview(comment, score, movieId, userId);
    }

    @PutMapping("/review/update")
    @ResponseBody
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
    public String deleteReview(@RequestParam(name="id") String id){
        return reviewService.deleteReview(id);
    }
}
