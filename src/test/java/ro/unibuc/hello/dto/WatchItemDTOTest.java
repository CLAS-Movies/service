package ro.unibuc.hello.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ro.unibuc.hello.data.MovieEntity;
import ro.unibuc.hello.data.UserEntity;
import ro.unibuc.hello.data.WatchItemEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WatchItemDTOTest {

    String id;
    MovieEntity movieEntity;
    UserEntity userEntity;
    MovieDTO movie;
    UserDTO user;
    WatchItemEntity watchItem;
    WatchItemDTO watchItemDTO;
    WatchItemDTO watchItemDTO2;
    int hash;
    String watchItemToString;


    @BeforeEach
    void setUp() {
        id = "621fc65c582d6b27ab6a4419";
        movieEntity = new MovieEntity("Dune", "Denis Villeneuve", "Jon Spaihts", 2021, 155, null, null);
        movieEntity.setId("6220986c669d743f12d457a0");
        userEntity = new UserEntity("Denis Villeneuve", "denis@myemail.com", null, null);
        userEntity.setId("62287c931be61542fed8cca1");
        movie = new MovieDTO(movieEntity);
        movie.setId("6220986c669d743f12d457a0");
        user = new UserDTO(userEntity);
        user.setId("62287c931be61542fed8cca1");
        watchItem = new WatchItemEntity(new WatchItemEntity.CompositeKey(movieEntity, userEntity));
        watchItem.setId("62287c931be61542fed8cca3");

        watchItemDTO = new WatchItemDTO();
        watchItemDTO.setId(id);
        watchItemDTO.setMovie(movie);
        watchItemDTO.setUser(user);
        watchItemDTO2 = new WatchItemDTO(watchItem);
        watchItemDTO2.setId(id);

        hash = Objects.hash(id, movie, user);

        watchItemToString = "WatchItemDTO{" +
                "id='" + id + '\'' +
                ", movie=" + movie +
                ", user=" + user +
                '}';
    }

    @Test
    void getMovie() {
        assertEquals(movie, watchItemDTO.getMovie());
    }

    @Test
    void getUser() {
        assertEquals(user, watchItemDTO.getUser());
    }

    @Test
    void getId() {
        assertEquals(id, watchItemDTO.getId());
    }

    @Test
    void testEquals() {
        assertEquals(watchItemDTO, watchItemDTO2);
    }

    @Test
    void testHashCode() {
        assertEquals(hash, watchItemDTO.hashCode());
    }

    @Test
    void testToString() {
        assertEquals(watchItemToString, watchItemDTO.toString());
    }
}