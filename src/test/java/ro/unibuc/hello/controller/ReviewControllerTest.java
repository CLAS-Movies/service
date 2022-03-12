package ro.unibuc.hello.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ro.unibuc.hello.data.MovieEntity;
import ro.unibuc.hello.data.ReviewEntity;
import ro.unibuc.hello.data.UserEntity;
import ro.unibuc.hello.dto.MovieDTO;
import ro.unibuc.hello.dto.ReviewDTO;
import ro.unibuc.hello.dto.UserDTO;
import ro.unibuc.hello.service.ReviewService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ReviewControllerTest {

    @Mock
    private ReviewService reviewService;

   @InjectMocks
   private ReviewController reviewController;

   private MockMvc mockMvc;

   private ObjectMapper objectMapper;

   String id;
   String comment;
   String updatedComment;
   Integer score;
   Integer updatedScore;
   MovieEntity movieEntity;
   MovieEntity updatedMovieEntity;
   MovieDTO movieDTO;
   MovieDTO updatedMovieDTO;
   UserEntity userEntity;
   UserEntity updatedUserEntity;
   UserDTO userDTO;
   UserDTO updatedUserDTO;
   ReviewEntity reviewEntity;
   ReviewEntity updatedReviewEntity;
   ReviewDTO reviewDTO;
   ReviewDTO updatedReviewDTO;
   ArrayList<ReviewDTO>  reviewDTOs;
   String deleteResponse;

   @BeforeEach
   public void setUp(){
       mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();
       objectMapper = new ObjectMapper();

       id = "333333333333333333333333";
       comment = "comment";
       score = 7;
       movieEntity = new MovieEntity("Dune", "Denis Villeneuve", "Jon Spaihts", 2021, 155, null, null);
       movieEntity.setId("111111111111111111111111");
       movieDTO = new MovieDTO(movieEntity);
       movieDTO.setId("111111111111111111111111");
       userEntity = new UserEntity("Miclaus", "miclaus@yahoo.com", null, null);
       userEntity.setId("222222222222222222222222");
       userDTO = new UserDTO(userEntity);
       userDTO.setId("222222222222222222222222");
       updatedComment = "UpdatedComment";
       updatedScore = 8;
       updatedMovieEntity = new MovieEntity("Dunes", "Deni$ Villeneuve", "Jon $paihts", 2022, 156, null, null);
       updatedMovieEntity.setId("111111111111111111111116");
       updatedUserEntity = new UserEntity("Miclau$", "miclau$@yahoo.com", null, null);
       updatedUserEntity.setId("222222222222222222222228");
       updatedMovieDTO = new MovieDTO(updatedMovieEntity);
       updatedUserDTO = new UserDTO(updatedUserEntity);
       reviewEntity = new ReviewEntity(comment, score, movieEntity, userEntity);
       reviewEntity.setId(id);
       reviewDTO = new ReviewDTO(reviewEntity);
       reviewDTO.setId(id);
       updatedReviewEntity = new ReviewEntity(updatedComment, updatedScore, updatedMovieEntity, updatedUserEntity);
       updatedReviewEntity.setId(id);
       updatedReviewDTO = new ReviewDTO(updatedReviewEntity);
       updatedReviewDTO.setId(id);
       reviewDTOs = new ArrayList<>();
       deleteResponse = "Review with id " + id + " was deleted!";
   }


    @Test
    void getReviews() throws Exception{
       when(reviewService.getReviews()).thenReturn(reviewDTOs);

        MvcResult result = mockMvc.perform(get("/review/getAll")
                        .content(objectMapper.writeValueAsString(reviewDTOs))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), objectMapper.writeValueAsString(reviewDTOs));

    }

    @Test
    void getReview() throws Exception {
        when(reviewService.getReview(id)).thenReturn(reviewDTO);

        MvcResult result = mockMvc.perform(get("/review/get?id=333333333333333333333333")
                        .content(objectMapper.writeValueAsString(reviewDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), objectMapper.writeValueAsString(reviewDTO));

    }

    @Test
    void insertReview() throws Exception{
        when(reviewService.insertReview(comment, score, movieEntity.getId(), userEntity.getId())).thenReturn(reviewDTO);

        MvcResult result = mockMvc.perform(post("/review/insert?comment=comment&score=7&movieId=111111111111111111111111&userId=222222222222222222222222")
                        .content(objectMapper.writeValueAsString(reviewDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), objectMapper.writeValueAsString(reviewDTO));

    }

    @Test
    void updateReview() throws Exception{
        when(reviewService.updateReview(id, updatedComment,updatedScore,updatedMovieEntity.getId(), updatedUserEntity.getId())).thenReturn(updatedReviewDTO);

        MvcResult result = mockMvc.perform(put("/review/update?id=333333333333333333333333&comment=UpdatedComment&score=8&movieId=111111111111111111111116&userId=222222222222222222222228")
                        .content(objectMapper.writeValueAsString(updatedReviewDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), objectMapper.writeValueAsString(updatedReviewDTO));


    }

    @Test
    void deleteReview() throws Exception{
        when(reviewService.deleteReview(id)).thenReturn(deleteResponse);

        MvcResult result = mockMvc.perform(delete("/review/delete?id=333333333333333333333333")
                        .content(objectMapper.writeValueAsString(deleteResponse))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), deleteResponse);
    }
}