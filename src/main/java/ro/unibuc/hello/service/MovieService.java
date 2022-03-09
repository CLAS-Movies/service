package ro.unibuc.hello.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import ro.unibuc.hello.data.*;
import ro.unibuc.hello.dto.MovieDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private WatchItemRepository watchItemRepository;

    public List<MovieDTO> getMovies() {
        ArrayList<MovieDTO> movieDTOs = new ArrayList<>();

        movieRepository.findAll().forEach(movieEntity -> movieDTOs.add(new MovieDTO(movieEntity)));
        return movieDTOs;
    }

    public MovieDTO getMovie(String id) {
        MovieEntity movie = movieRepository.findById(String.valueOf(new ObjectId(id))).orElse(null);

        if (movie!=null)
            return new MovieDTO(movie);
        else
            return null;
    }

    public MovieDTO insertMovie(String title,
                                String director,
                                String writer,
                                Integer year,
                                Integer duration,
                                List<String>reviewIds,
                                List<String>watchItemIds){
        MovieEntity movie=new MovieEntity(title,director,writer,year,duration);
        ArrayList<ReviewEntity> reviewEntities = new ArrayList<>();
        ArrayList<WatchItemEntity> watchItemEntities = new ArrayList<>();

        if(!reviewIds.isEmpty())
            reviewIds.forEach(id -> reviewEntities.add(reviewRepository.findById(String.valueOf(new ObjectId(id))).orElse(null)));
        else
            movie.setReviews(null);

        if(!watchItemIds.isEmpty())
            watchItemIds.forEach(id -> watchItemEntities.add(watchItemRepository.findById(String.valueOf(new ObjectId(id))).orElse(null)));
        else
            movie.setWatchItems(null);

        if(!reviewEntities.isEmpty())
            movie.setReviews(reviewEntities);

        if(!watchItemEntities.isEmpty())
            movie.setWatchItems(watchItemEntities);
        return new MovieDTO(movieRepository.save(movie));
    }

    public MovieDTO updateMovie(String id,
                                String title,
                                String director,
                                String writer,
                                Integer year,
                                Integer duration,
                                List<String>reviewIds,
                                List<String>watchItemIds){
        MovieEntity movie=movieRepository.findById(String.valueOf(new ObjectId(id))).orElse(null);

        if(movie!=null)
        {
            if(title!=null)
                movie.setTitle(title);
            if(director!=null)
                movie.setDirector(director);
            if(writer!=null)
                movie.setWriter(writer);
            if(year!=null)
                movie.setYear(year);
            if(duration!=null)
                movie.setDuration(duration);

            if(!reviewIds.isEmpty()) {
                ArrayList<ReviewEntity> reviewEntities = new ArrayList<>();
                reviewIds.forEach(reviewId -> reviewEntities.add(reviewRepository.findById(String.valueOf(new ObjectId(reviewId))).orElse(null)));
                if(!reviewEntities.isEmpty())
                    movie.setReviews(reviewEntities);
            }
            else
                movie.setReviews(null);

            if(!watchItemIds.isEmpty()) {
                ArrayList<WatchItemEntity> watchItemEntities = new ArrayList<>();
                watchItemIds.forEach(watchItemId -> watchItemEntities.add(watchItemRepository.findById(String.valueOf(new ObjectId(watchItemId))).orElse(null)));
                if(!watchItemEntities.isEmpty())
                    movie.setWatchItems(watchItemEntities);
            }
            else
                movie.setWatchItems(null);
            return new MovieDTO(movieRepository.save(movie));
        }
        else return null;
    }
    public String deleteReview(String id){
        movieRepository.deleteById(String.valueOf(new ObjectId(id)));

        return "Movie with id " + id + " was deleted!";
    }


}
