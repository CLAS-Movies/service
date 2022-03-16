package ro.unibuc.hello.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.unibuc.hello.dto.ReviewDTO;

@SpringBootTest
public class ReviewServiceTestIT {

    @Autowired
    private ReviewService reviewService;

    @Test
    void getReview() {
        ReviewDTO result = reviewService.getReview("222222222222222222222223");

        Assertions.assertEquals(result.getId(),"222222222222222222222223" );
        Assertions.assertEquals(result.getComment(),"TestComment");
        Assertions.assertEquals(result.getScore(),Integer.valueOf(10));
        Assertions.assertEquals(result.getMovie().getId(),"222222222222222222222222");
        Assertions.assertEquals(result.getUser().getId(),"222222222222222222222221");
    }
}
