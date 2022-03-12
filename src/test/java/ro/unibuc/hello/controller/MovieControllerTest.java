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
import ro.unibuc.hello.data.WatchItemEntity;
import ro.unibuc.hello.dto.MovieDTO;
import ro.unibuc.hello.service.MovieService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class MovieControllerTest {

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    String id;
    String title;
    String updatedTitle;
    String director;
    String updatedDirector;
    String writer;
    String updatedWriter;
    Integer year;
    Integer updatedYear;
    Integer duration;
    Integer updatedDuration;
    List<String> reviewIds;
    List<String> watchItemIds;
    MovieEntity movieEntity;
    MovieEntity updatedMovieEntity;
    MovieDTO movieDTO;
    MovieDTO updatedMovieDTO;
    ArrayList<MovieDTO> movieDTOs;
    String deleteResponse;

@BeforeEach
public void setUp(){
    mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
    objectMapper = new ObjectMapper();

    id = "111111111111111111111111";
    title = "Dune";
    director = "Denis Villeneuve";
    writer = "Jon Spaihts";
    year = 2021;
    duration = 155;
    updatedTitle = "Dunes";
    updatedDirector = "Deni$ Villeneuve";
    updatedWriter = "Jon $paihts";
    updatedYear = 2022;
    updatedDuration = 156;
    movieEntity = new MovieEntity(title, director, writer, year, duration);
    movieEntity.setId(id);
    updatedMovieEntity = new MovieEntity(updatedTitle ,updatedDirector, updatedWriter,
             updatedYear, updatedDuration);
    updatedMovieEntity.setId(id);
    deleteResponse = "Movie with id " + id + " was deleted!";

    movieDTO = new MovieDTO(movieEntity);
    updatedMovieDTO = new MovieDTO(updatedMovieEntity);
    movieDTOs = new ArrayList<>();
    movieDTOs.add(movieDTO);
    reviewIds = movieEntity.getReviews()!= null ? movieEntity.getReviews()
            .stream()
            .map(ReviewEntity::getId)
            .collect(Collectors.toList()) : new ArrayList<>();
    watchItemIds = movieEntity.getWatchItems()!= null ? movieEntity.getWatchItems()
            .stream()
            .map(WatchItemEntity::getId)
            .collect(Collectors.toList()) : new ArrayList<>();


}

    @Test
    void getMovies() throws Exception {
        when(movieService.getMovies()).thenReturn(movieDTOs);

        MvcResult result = mockMvc.perform(get("/movie/getAll")
                .content(objectMapper.writeValueAsString(movieDTOs))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(),
                objectMapper.writeValueAsString(movieDTOs));



    }

    @Test
    void getMovie() throws Exception {
        when(movieService.getMovie(id)).thenReturn(movieDTO);

        MvcResult result = mockMvc.perform(get("/movie/get?id=111111111111111111111111")
                        .content(objectMapper.writeValueAsString(movieDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), objectMapper.writeValueAsString(movieDTO));
    }


    @Test
    void insertMovie() throws Exception {
    when(movieService.insertMovie(title, director, writer, year, duration, reviewIds, watchItemIds))
            .thenReturn(movieDTO);

        MvcResult result = mockMvc.perform(post("/movie/insert?title=Dune&director=Denis Villeneuve" +
                        "&writer=Jon Spaihts&year=2021&duration=155&reviewIds=&watchItemIds=")
                        .content(objectMapper.writeValueAsString(movieDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), objectMapper.writeValueAsString(movieDTO));
    }

    @Test
    void updateMovie() throws Exception{
        when(movieService.updateMovie(id, updatedTitle, updatedDirector, updatedWriter, updatedYear, updatedDuration, reviewIds, watchItemIds))
                .thenReturn(updatedMovieDTO);
        MvcResult result = mockMvc.perform(put("/movie/update?id=111111111111111111111111&title=Dunes&director=Deni$ Villeneuve" +
                        "&writer=Jon $paihts&year=2022&duration=156&reviewIds=&watchItemIds=")
                        .content(objectMapper.writeValueAsString(updatedMovieDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), objectMapper.writeValueAsString(updatedMovieDTO));

    }

    @Test
    void deleteReview() throws Exception {
    when(movieService.deleteMovie(id)).thenReturn(deleteResponse);

    MvcResult result = mockMvc.perform(delete("/movie/delete?id=111111111111111111111111")
            .content(objectMapper.writeValueAsString(deleteResponse))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

    assertEquals(result.getResponse().getContentAsString(), deleteResponse);
    }
}