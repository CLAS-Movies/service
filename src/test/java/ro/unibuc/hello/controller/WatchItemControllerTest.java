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
import ro.unibuc.hello.data.UserEntity;
import ro.unibuc.hello.data.WatchItemEntity;
import ro.unibuc.hello.dto.WatchItemDTO;
import ro.unibuc.hello.service.WatchItemService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class WatchItemControllerTest {

    @Mock
    private WatchItemService watchItemService;

    @InjectMocks
    private WatchItemController watchItemController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    String id;
    String movieId;
    String userId;
    String newMovieId;
    String newUserId;
    MovieEntity movie;
    MovieEntity newMovie;
    UserEntity user;
    UserEntity newUser;
    WatchItemEntity watchItem;
    WatchItemEntity updatedWatchItem;
    WatchItemDTO watchItemDTO;
    WatchItemDTO updatedWatchItemDTO;
    ArrayList<WatchItemDTO> watchItemDTOs;
    String deleteResponse;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(watchItemController).build();
        objectMapper = new ObjectMapper();

        id = "621fc65c582d6b27ab6a4419";
        movieId = "6220986c669d743f12d457a0";
        userId = "62287c931be61542fed8cca1";
        newMovieId = "6220986c669d743f12d457a3";
        newUserId = "62287c931be61542fed8cca4";
        movie = new MovieEntity("Dune", "Denis Villeneuve", "Jon Spaihts", 2021, 155, null, null);
        movie.setId(movieId);
        newMovie = new MovieEntity(movie.getTitle(), movie.getDirector(), "Denis Villeneuve", movie.getYear(), movie.getDuration(), movie.getReviews(), movie.getWatchItems());
        newMovie.setId(newMovieId);
        user = new UserEntity("Denis Villeneuve", "denis@myemail.com", null, null);
        user.setId(userId);
        newUser = new UserEntity(user.getName(), "villeneuve@myemail.com", user.getReviews(), user.getWatchItems());
        newUser.setId(newUserId);
        deleteResponse = "User with id " + id + " was deleted!";

        watchItem = new WatchItemEntity(new WatchItemEntity.CompositeKey(movie, user));
        watchItemDTO = new WatchItemDTO(watchItem);

        updatedWatchItem = new WatchItemEntity(new WatchItemEntity.CompositeKey(newMovie, newUser));
        updatedWatchItemDTO = new WatchItemDTO(updatedWatchItem);

        watchItemDTOs = new ArrayList<>();
        watchItemDTOs.add(watchItemDTO);
    }

    @Test
    void getWatchItems() throws Exception {
        when(watchItemService.getWatchItems()).thenReturn(watchItemDTOs);

        MvcResult result = mockMvc.perform(get("/watchItem/getAll")
                        .content(objectMapper.writeValueAsString(watchItemDTOs))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), objectMapper.writeValueAsString(watchItemDTOs));
    }

    @Test
    void getWatchItem() throws Exception {
        when(watchItemService.getWatchItem(id)).thenReturn(watchItemDTO);

        MvcResult result = mockMvc.perform(get("/watchItem/get?id=621fc65c582d6b27ab6a4419")
                        .content(objectMapper.writeValueAsString(watchItemDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), objectMapper.writeValueAsString(watchItemDTO));
    }

    @Test
    void insertWatchItem() throws Exception {
        when(watchItemService.insertWatchItem(movieId, userId)).thenReturn(watchItemDTO);

        MvcResult result = mockMvc.perform(post("/watchItem/insert?movieId=6220986c669d743f12d457a0&userId=62287c931be61542fed8cca1")
                        .content(objectMapper.writeValueAsString(watchItemDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), objectMapper.writeValueAsString(watchItemDTO));
    }

    @Test
    void updateWatchItem() throws Exception {
        when(watchItemService.updateWatchItem(id, newMovieId, newUserId)).thenReturn(updatedWatchItemDTO);

        MvcResult result = mockMvc.perform(put("/watchItem/update?id=621fc65c582d6b27ab6a4419&newMovieId=6220986c669d743f12d457a3&newUserId=62287c931be61542fed8cca4")
                        .content(objectMapper.writeValueAsString(updatedWatchItemDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), objectMapper.writeValueAsString(updatedWatchItemDTO));
    }

    @Test
    void deleteWatchItem() throws Exception {
        when(watchItemService.deleteWatchItem(id)).thenReturn(deleteResponse);

        MvcResult result = mockMvc.perform(delete("/watchItem/delete?id=621fc65c582d6b27ab6a4419")
                        .content(objectMapper.writeValueAsString(deleteResponse))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), deleteResponse);
    }
}