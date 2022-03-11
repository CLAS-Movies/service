package ro.unibuc.hello.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.hello.dto.MovieDTO;
import ro.unibuc.hello.service.MovieService;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;



    @GetMapping("/movie/getAll")
    @ResponseBody
    public List<MovieDTO> getMovies(){
        return movieService.getMovies();
    }

    @GetMapping("/movie/get")
    @ResponseBody
    public MovieDTO getMovie(@RequestParam(name="id") String id) {
       return movieService.getMovie(id);
    }

    @PostMapping("/movie/insert")
    @ResponseBody
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
    public MovieDTO updateMovie(@RequestParam(name="id") String id,
                                @RequestParam(name="title",required = false) String title,
                                @RequestParam(name="director",required = false) String director,
                                @RequestParam(name="writer",required = false) String writer,
                                @RequestParam(name="year",required = false) Integer year,
                                @RequestParam(name="duration",required = false) Integer duration,
                                @RequestParam(name="reviewIds") List<String> reviewIds,
                                @RequestParam(name="watchItemIds") List<String> watchItemIds) {
        return movieService.updateMovie(id, title, director, writer, year, duration, reviewIds, watchItemIds);
    }

    @DeleteMapping("/movie/delete")
    @ResponseBody
    public String deleteMovie(@RequestParam(name="id") String id) {
        return movieService.deleteMovie(id);

    }
}
