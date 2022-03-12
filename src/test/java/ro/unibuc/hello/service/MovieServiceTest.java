package ro.unibuc.hello.service;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ro.unibuc.hello.data.MovieEntity;
import ro.unibuc.hello.data.MovieRepository;
import ro.unibuc.hello.data.ReviewEntity;
import ro.unibuc.hello.data.WatchItemEntity;
import ro.unibuc.hello.dto.MovieDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
class MovieServiceTest {

    @Mock
    MovieRepository movieRepository;

    @InjectMocks
    MovieService movieService = new MovieService();

    MovieEntity movie;
    MovieEntity updatedMovie;
    MovieEntity movieWithoutId;
    MovieDTO movieDTO;
    MovieDTO updatedMovieDTO;
    ArrayList<MovieDTO> movieDTOs;
    ArrayList<MovieEntity>movieEntities;
    String deleteResponse;

    List<String> reviewIds;
    List<String> watchItemIds;

    @BeforeEach
    void setUp(){
        movie = new MovieEntity("Dune","Denis Villeneuve","Jon Spaihts",2021,155, null,null);
        updatedMovie = new MovieEntity("Dunes","Deniss Villeneuve","John Spaihts",
                movie.getYear(),movie.getDuration(),movie.getReviews(),movie.getWatchItems());
        movieWithoutId = new MovieEntity(movie.getTitle(),movie.getDirector(),movie.getWriter(),
                movie.getYear(),movie.getDuration(),movie.getReviews(),movie.getWatchItems());
        movie.setId("333333333333333333333333");
        updatedMovie.setId(movie.getId());
        deleteResponse = "Movie with id " + movie.getId() + " was deleted!";

        movieDTO = new MovieDTO(movie);
        updatedMovieDTO = new MovieDTO(updatedMovie);

        movieDTOs = new ArrayList<>();
        movieEntities = new ArrayList<>();

        movieDTOs.add(movieDTO);
        movieEntities.add(movie);


        reviewIds = movie.getReviews()!= null ? movie.getReviews()
                .stream()
                .map(ReviewEntity::getId)
                .collect(Collectors.toList()) : new ArrayList<>();
        watchItemIds = movie.getWatchItems()!= null ? movie.getWatchItems()
                .stream()
                .map(WatchItemEntity::getId)
                .collect(Collectors.toList()) : new ArrayList<>();
    }


    @Test
    void getMovies() {
        doReturn(movieEntities).when(movieRepository).findAll();

        List<MovieDTO> result = movieService.getMovies();

        Assertions.assertEquals(result, movieDTOs);
    }

    @Test
    void getMovie() {
        when(movieRepository.findById(String.valueOf(new ObjectId(movie.getId())))).thenReturn(Optional.ofNullable(movie));

        MovieDTO result = movieService.getMovie(movieDTO.getId());

        Assertions.assertEquals(result, movieDTO);
    }

    @Test
    void insertMovie() {
        when(movieRepository.save(movieWithoutId)).thenReturn(movie);

        MovieDTO result = movieService.insertMovie(movie.getTitle(),movie.getDirector(),movie.getWriter(),movie.getYear(),movie.getDuration(),
        reviewIds,watchItemIds);

        Assertions.assertEquals(result,movieDTO);
    }

    @Test
    void updateMovie() {
        when(movieRepository.findById(String.valueOf(new ObjectId(movie.getId())))).thenReturn(Optional.ofNullable(movie));
        when(movieRepository.save(updatedMovie)).thenReturn(updatedMovie);

        MovieDTO result = movieService.updateMovie(movie.getId(),"Dunes", "Deniss Villeneuve","John Spaihts",2021,155,
                reviewIds, watchItemIds);

        Assertions.assertEquals(result, updatedMovieDTO);
    }

    @Test
    void deleteReview() {
        String result = movieService.deleteMovie(movie.getId());

        Assertions.assertEquals(result, deleteResponse);
    }
}