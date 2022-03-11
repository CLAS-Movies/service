package ro.unibuc.hello.service;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ro.unibuc.hello.data.ReviewEntity;
import ro.unibuc.hello.data.UserEntity;
import ro.unibuc.hello.data.UserRepository;
import ro.unibuc.hello.data.WatchItemEntity;
import ro.unibuc.hello.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService = new UserService();

    UserEntity user;
    UserEntity updatedUser;
    UserEntity userWithoutId;
    UserDTO userDTO;
    UserDTO updatedUserDTO;
    ArrayList<UserDTO> userDTOs;
    ArrayList<UserEntity> userEntities;
    List<String> reviewIds;
    List<String> watchItemIds;
    String deleteResponse;

    @BeforeEach
    void setUp() {
        user = new UserEntity("Miclaus", "miclaus@mymail.com", null, null);
        updatedUser = new UserEntity("Catalina", "catalina@mymail.com", user.getReviews(), user.getWatchItems());
        userWithoutId = new UserEntity(user.getName(), user.getEmail(), user.getReviews(), user.getWatchItems());
        user.setId("62287c931be61542fed8cca1");
        updatedUser.setId(user.getId());
        deleteResponse = "User with id " + user.getId() + " was deleted!";

        userDTO = new UserDTO(user);
        updatedUserDTO = new UserDTO(updatedUser);

        userDTOs = new ArrayList<>();
        userEntities = new ArrayList<>();

        userDTOs.add(userDTO);
        userEntities.add(user);

        reviewIds = user.getReviews()!= null ? user.getReviews()
                .stream()
                .map(ReviewEntity::getId)
                .collect(Collectors.toList()) : new ArrayList<>();
        watchItemIds = user.getWatchItems()!= null ? user.getWatchItems()
                .stream()
                .map(WatchItemEntity::getId)
                .collect(Collectors.toList()) : new ArrayList<>();
    }

    @Test
    void getUsers() {
        doReturn(userEntities).when(userRepository).findAll();

        List<UserDTO> result = userService.getUsers();

        Assertions.assertEquals(result, userDTOs);
    }

    @Test
    void getUser() {
        when(userRepository.findById(String.valueOf(new ObjectId(user.getId())))).thenReturn(Optional.ofNullable(user));

        UserDTO result = userService.getUser(userDTO.getId());

        Assertions.assertEquals(result, userDTO);
    }

    @Test
    void insertUser() {
        when(userRepository.save(userWithoutId)).thenReturn(user);

        UserDTO result = userService.insertUser(user.getName(), user.getEmail(), reviewIds, watchItemIds);

        Assertions.assertEquals(result, userDTO);
    }

    @Test
    void updateUser() {
        when(userRepository.findById(String.valueOf(new ObjectId(user.getId())))).thenReturn(Optional.ofNullable(user));
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);

        UserDTO result = userService.updateUser(user.getId(), "Catalina", "catalina@mymail.com", reviewIds, watchItemIds);

        Assertions.assertEquals(result, updatedUserDTO);
    }

    @Test
    void deleteUser() {
        String result = userService.deleteUser(user.getId());

        Assertions.assertEquals(result, deleteResponse);
    }
}