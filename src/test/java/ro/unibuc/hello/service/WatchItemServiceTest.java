package ro.unibuc.hello.service;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ro.unibuc.hello.data.*;
import ro.unibuc.hello.dto.MovieDTO;
import ro.unibuc.hello.dto.UserDTO;
import ro.unibuc.hello.dto.WatchItemDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WatchItemServiceTest {

    @Mock
    WatchItemRepository watchItemRepository;

    @Mock
    MovieRepository movieRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    WatchItemService watchItemService = new WatchItemService();

    MovieEntity movie;
    MovieEntity updateMovie;
    UserEntity user;
    UserEntity updatedUser;
    String newMovieId;
    String newUserId;
    WatchItemEntity.CompositeKey compositeKey;
    WatchItemEntity.CompositeKey updatedCompositeKey;
    WatchItemEntity watchItem;
    WatchItemEntity updatedWatchItem;
    WatchItemEntity watchItemWithoutId;
    MovieDTO movieDTO;
    MovieDTO updatedMovieDTO;
    UserDTO userDTO;
    UserDTO updatedUserDTO;
    WatchItemDTO watchItemDTO;
    WatchItemDTO updatedWwatchItemDTO;
    ArrayList<WatchItemEntity> watchItemEntities;
    ArrayList<WatchItemDTO> watchItemDTOs;
    String deleteResponse;

    @BeforeEach
    void setUp() {
        newMovieId = "6220986c669d743f12d457a3";
        newUserId = "62287c931be61542fed8cca4";
        movie = new MovieEntity("Dune", "Denis Villeneuve", "Jon Spaihts", 2021, 155, null, null);
        movie.setId("6220986c669d743f12d457a0");
        updateMovie = new MovieEntity(movie.getTitle(), movie.getDirector(), "Denis Villeneuve", movie.getYear(), movie.getDuration(), movie.getReviews(), movie.getWatchItems());
        updateMovie.setId(movie.getId());
        user = new UserEntity("Denis Villeneuve", "denis@myemail.com", null, null);
        user.setId("62287c931be61542fed8cca1");
        updatedUser = new UserEntity(user.getName(), "villeneuve@myemail.com", user.getReviews(), user.getWatchItems());
        updatedUser.setId(user.getId());

        compositeKey = new WatchItemEntity.CompositeKey(movie, user);
        updatedCompositeKey = new WatchItemEntity.CompositeKey(updateMovie, updatedUser);

        watchItem = new WatchItemEntity(compositeKey);
        watchItem.setId("621fc65c582d6b27ab6a4419");
        updatedWatchItem = new WatchItemEntity(updatedCompositeKey);
        updatedWatchItem.setId(watchItem.getId());
        watchItemWithoutId = new WatchItemEntity(compositeKey);
        deleteResponse = "WatchItem with id " + watchItem.getId() + " was deleted!";

        movieDTO = new MovieDTO(movie);
        updatedMovieDTO = new MovieDTO(updateMovie);
        userDTO = new UserDTO(user);
        updatedUserDTO = new UserDTO(updatedUser);
        watchItemDTO = new WatchItemDTO(watchItem);
        updatedWwatchItemDTO = new WatchItemDTO(updatedWatchItem);

        watchItemEntities = new ArrayList<>();
        watchItemDTOs = new ArrayList<>();

        watchItemEntities.add(watchItem);
        watchItemDTOs.add(watchItemDTO);
    }

    @Test
    void getWatchItems() {
        doReturn(watchItemEntities).when(watchItemRepository).findAll();

        List<WatchItemDTO> result = watchItemService.getWatchItems();

        assertEquals(result, watchItemDTOs);
    }

    @Test
    void getWatchItem() {
        when(watchItemRepository.findById(String.valueOf(new ObjectId(watchItem.getId())))).thenReturn(Optional.ofNullable(watchItem));

        WatchItemDTO result = watchItemService.getWatchItem(watchItem.getId());

        assertEquals(result, watchItemDTO);
    }

    @Test
    void insertWatchItem() {
        when(movieRepository.findById(String.valueOf(new ObjectId(movie.getId())))).thenReturn(Optional.ofNullable(movie));
        when(userRepository.findById(String.valueOf(new ObjectId(user.getId())))).thenReturn(Optional.ofNullable(user));
        when(watchItemRepository.save(watchItemWithoutId)).thenReturn(watchItem);

        WatchItemDTO result = watchItemService.insertWatchItem(movie.getId(), user.getId());

        assertEquals(result, watchItemDTO);
    }

    @Test
    void updateWatchItem() {
        when(watchItemRepository.findById(String.valueOf(new ObjectId(watchItem.getId())))).thenReturn(Optional.ofNullable(watchItem));
        when(watchItemRepository.save(updatedWatchItem)).thenReturn(updatedWatchItem);
        when(movieRepository.findById(String.valueOf(new ObjectId(newMovieId)))).thenReturn(Optional.ofNullable(updateMovie));
        when(userRepository.findById(String.valueOf(new ObjectId(newUserId)))).thenReturn(Optional.ofNullable(updatedUser));

        WatchItemDTO result = watchItemService.updateWatchItem(watchItem.getId(), newMovieId, newUserId);

        Assertions.assertEquals(result, updatedWwatchItemDTO);
    }

    @Test
    void deleteWatchItem() {
        String result = watchItemService.deleteWatchItem(watchItem.getId());

        assertEquals(result, deleteResponse);
    }
}