package ro.unibuc.hello.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.unibuc.hello.data.MovieRepository;
import ro.unibuc.hello.dto.MovieDTO;

import java.util.ArrayList;

@SpringBootTest
public class MovieServiceTestIT {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MovieService movieService;

    @Test
    void updateMovie(){
        MovieDTO result = movieService.updateMovie("222222222222222222222222", "AltTitluIT",
                null, null, null, null, new ArrayList<>(), new ArrayList<>());

        Assertions.assertEquals(result.getId(), "222222222222222222222222");
        Assertions.assertEquals(result.getTitle(), "AltTitluIT");
    }

}
