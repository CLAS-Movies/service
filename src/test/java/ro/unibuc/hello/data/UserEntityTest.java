package ro.unibuc.hello.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserEntityTest {

    String id;
    String name;
    String email;
    ArrayList<ReviewEntity> reviews;
    ArrayList<WatchItemEntity> watchItems;
    UserEntity user;
    UserEntity user2;
    int hash;
    String userString;

    @BeforeEach
    void setUp() {
        id = "62287c931be61542fed8cca1";
        name = "Miclaus";
        email = "miclaus@mymail.com";
        reviews = new ArrayList<>();
        watchItems = new ArrayList<>();
        user = new UserEntity();
        user2 = new UserEntity(name, email, reviews, watchItems);
        hash = Objects.hash(id, name, email);
        userString = "UserEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';

        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setReviews(reviews);
        user.setWatchItems(watchItems);
        user2.setId(id);
    }

    @Test
    void getId() {
        assertEquals(id, user.getId());
    }

    @Test
    void getName() {
        assertEquals(name, user.getName());
    }

    @Test
    void getEmail() {
        assertEquals(email, user.getEmail());
    }

    @Test
    void getReviews() {
        assertEquals(reviews, user.getReviews());
    }

    @Test
    void getWatchItems() {
        assertEquals(watchItems, user.getWatchItems());
    }

    @Test
    void testEquals() {
        assertEquals(user, user2);
    }

    @Test
    void testHashCode() {
        assertEquals(hash, user.hashCode());
    }

    @Test
    void testToString() {
        assertEquals(userString, user.toString());
    }
}