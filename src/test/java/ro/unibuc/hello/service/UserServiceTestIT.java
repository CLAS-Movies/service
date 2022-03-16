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


    @BeforeEach
    void setUp() {
        user = new UserEntity("Miclaus", "miclaus@mymail.com", null, null);
        deleteResponse = "User with id 222222222222222222222221 was deleted!";
    }

    @Test
    void insertUser() {
        UserDTO result = userService.insertUser(user.getName(), user.getEmail(), new ArrayList<>(), new ArrayList<>());

        Assertions.assertEquals(result.getName(), "Miclaus");
        Assertions.assertEquals(result.getEmail(), "miclaus@mymail.com");
    }

    @Test
    void deleteUser() {
        String result = userService.deleteUser("222222222222222222222221");

        Assertions.assertEquals(result, deleteResponse);
    }
}
