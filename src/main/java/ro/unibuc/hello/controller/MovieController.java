package ro.unibuc.hello.controller;

import java.util.List;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.hello.dto.MovieDTO;
import ro.unibuc.hello.service.MovieService;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    MeterRegistry metricsRegistry;

    @GetMapping("/movie/getAll")
    @ResponseBody
    @Timed(value = "movie.getMovies.time", description = "Time taken to return movies")
    @Counted(value = "movie.getMovies.count", description = "Times movies were returned")
    public List<MovieDTO> getMovies(){
        return movieService.getMovies();
    }

    @GetMapping("/movie/get")
    @ResponseBody
    @Timed(value = "movie.getMovie.time", description = "Time taken to return a movie")
    @Counted(value = "movie.getMovie.count", description = "Times a movie was returned")
    public MovieDTO getMovie(@RequestParam(name="id") String id) {
       return movieService.getMovie(id);
    }

    @PostMapping("/movie/insert")
    @ResponseBody
    @Timed(value = "movie.insertMovie.time", description = "Time taken to insert a movie")
    @Counted(value = "movie.insertMovie.count", description = "Times a movie was inserted")
    public MovieDTO insertMovie(@RequestParam(name="title") String title,
                                @RequestParam(name="director") String director,
                                @RequestParam(name="writer") String writer,
                                @RequestParam(name="year") Integer year,
                                @RequestParam(name="duration") Integer duration,
                                @RequestParam(name="reviewIds") List<String>reviewIds,
                                @RequestParam(name="watchItemIds") List<String>watchItemIds) {
        return movieService.insertMovie(title, director, writer, year, duration, reviewIds, watchItemIds);
    }

    @PutMapping("/movie/update")
    @ResponseBody
    @Timed(value = "movie.updateMovie.time", description = "Time taken to update a movie")
    @Counted(value = "movie.updateMovie.count", description = "Times a movie was updated")
    public MovieDTO updateMovie(@RequestParam(name="id") String id,
                                @RequestParam(name="title") String title,
                                @RequestParam(name="director") String director,
                                @RequestParam(name="writer") String writer,
                                @RequestParam(name="year") Integer year,
                                @RequestParam(name="duration") Integer duration,
                                @RequestParam(name="reviewIds") List<String> reviewIds,
                                @RequestParam(name="watchItemIds") List<String> watchItemIds) {
        return movieService.updateMovie(id, title, director, writer, year, duration, reviewIds, watchItemIds);
    }

    @DeleteMapping("/movie/delete")
    @ResponseBody
    @Timed(value = "movie.deleteMovie.time", description = "Time taken to delete a movie")
    @Counted(value = "movie.deleteMovie.count", description = "Times a movie was deleted")
    public String deleteMovie(@RequestParam(name="id") String id) {
        return movieService.deleteMovie(id);

    }
}
