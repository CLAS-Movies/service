package ro.unibuc.hello.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.unibuc.hello.data.UserEntity;
import ro.unibuc.hello.data.UserRepository;
import ro.unibuc.hello.dto.UserDTO;

import java.util.ArrayList;

@SpringBootTest
public class UserServiceTestIT {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    UserEntity user;
    String deleteResponse;
    String id;


    @BeforeEach
    void setUp() {
        id = "222222222222222222222227";
        user = new UserEntity("Miclaus", "miclaus@mymail.com", null, null);
        user.setId(id);
        deleteResponse = "User with id " + id + " was deleted!";
    }

    @Test
    void insertUser() {
        UserDTO result = userService.insertUser(user.getName(), user.getEmail(), new ArrayList<>(), new ArrayList<>());

        Assertions.assertEquals(result.getName(), "Miclaus");
        Assertions.assertEquals(result.getEmail(), "miclaus@mymail.com");
    }

    @Test
    void deleteUser() {
        userRepository.save(user);
        String result = userService.deleteUser(id);

        Assertions.assertEquals(result, deleteResponse);
    }
}
