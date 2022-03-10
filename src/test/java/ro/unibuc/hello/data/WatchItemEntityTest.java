package ro.unibuc.hello.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WatchItemEntityTest {

    String id;
    MovieEntity movie;
    UserEntity user;
    WatchItemEntity.CompositeKey compositeKey;
    WatchItemEntity.CompositeKey compositeKey2;
    WatchItemEntity watchItem;
    WatchItemEntity watchItem2;
    int hash;
    String watchItemString;
    int compositeKeyHash;
    String compositeKeyString;

    @BeforeEach
    void setUp() {
        id = "621fc65c582d6b27ab6a4419";
        movie = new MovieEntity("Dune", "Denis Villeneuve", "Jon Spaihts", 2021, 155, null, null);
        movie.setId("6220986c669d743f12d457a0");
        user = new UserEntity("Denis Villeneuve", "denis@myemail.com", null, null);
        user.setId("62287c931be61542fed8cca1");

        compositeKey = new WatchItemEntity.CompositeKey();
        compositeKey.setMovie(movie);
        compositeKey.setUser(user);
        compositeKey2 = new WatchItemEntity.CompositeKey(movie, user);


        watchItem = new WatchItemEntity();
        watchItem2 = new WatchItemEntity(compositeKey);
        
        watchItem.setId(id);
        watchItem2.setId(id);
        watchItem.setCompositeKey(compositeKey);

        hash = Objects.hash(id, compositeKey);
        compositeKeyHash = Objects.hash(movie, user);

        watchItemString = "WatchItemEntity{" +
                "id='" + id + '\'' +
                ", compositeKey=" + compositeKey +
                '}';
        compositeKeyString = "CompositeKey{" +
                "movie=" + movie +
                ", user=" + user +
                '}';
    }

    @Test
    void getId() {
        assertEquals(id, watchItem.getId());
    }

    @Test
    void getCompositeKey() {
        assertEquals(id, watchItem.getId());
    }

    @Test
    void getMovieCompositeKey() {
        assertEquals(movie, watchItem.getCompositeKey().getMovie());
    }

    @Test
    void getUserCompositeKey() {
        assertEquals(user, watchItem.getCompositeKey().getUser());
    }

    @Test
    void testEquals() {
        assertEquals(watchItem, watchItem2);
    }

    @Test
    void testHashCode() {
        assertEquals(hash, watchItem.hashCode());
    }

    @Test
    void testToString() {
        assertEquals(watchItemString, watchItem.toString());
    }

    @Test
    void testEqualsCompositeKey() {
        assertEquals(compositeKey, compositeKey2);
    }

    @Test
    void testHashCodeCompositeKey() {
        assertEquals(compositeKeyHash, compositeKey.hashCode());
    }

    @Test
    void testToStringCompositeKey() {
        assertEquals(compositeKeyString, compositeKey.toString());
    }
}