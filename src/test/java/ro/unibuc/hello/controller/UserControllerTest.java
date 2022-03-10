package ro.unibuc.hello.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ro.unibuc.hello.data.ReviewEntity;
import ro.unibuc.hello.data.UserEntity;
import ro.unibuc.hello.data.WatchItemEntity;
import ro.unibuc.hello.dto.UserDTO;
import ro.unibuc.hello.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;


    String id;
    String name;
    String updatedName;
    String email;
    String updatedEmail;
    UserEntity userEntity;
    UserEntity updatedUserEntity;
    UserDTO userDTO;
    UserDTO updatedUserDTO;
    ArrayList<UserDTO> userDTOs;
    List<String> reviewIds;
    List<String> watchItemIds;
    String deleteResponse;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();

        id = "62287c931be61542fed8cca1";
        name = "Catalina";
        email = "catalina@mymail.com";
        updatedName = "Olivia";
        updatedEmail = "olivia@mymail.com";
        userEntity = new UserEntity(name, email);
        userEntity.setId(id);
        updatedUserEntity = new UserEntity(updatedName, updatedEmail);
        updatedUserEntity.setId(id);
        deleteResponse = "User with id " + id + " was deleted!";

        userDTO = new UserDTO(userEntity);
        updatedUserDTO = new UserDTO(updatedUserEntity);
        userDTOs = new ArrayList<>();

        userDTOs.add(userDTO);

        reviewIds = userEntity.getReviews()!= null ? userEntity.getReviews()
                .stream()
                .map(ReviewEntity::getId)
                .collect(Collectors.toList()) : new ArrayList<>();
        watchItemIds = userEntity.getWatchItems()!= null ? userEntity.getWatchItems()
                .stream()
                .map(WatchItemEntity::getId)
                .collect(Collectors.toList()) : new ArrayList<>();
    }

    @Test
    void getUsers() throws Exception {
        when(userService.getUsers()).thenReturn(userDTOs);

        MvcResult result = mockMvc.perform(get("/user/getAll")
                    .content(objectMapper.writeValueAsString(userDTOs))
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), objectMapper.writeValueAsString(userDTOs));
    }

    @Test
    void getUser() throws Exception {
        when(userService.getUser(id)).thenReturn(userDTO);

        MvcResult result = mockMvc.perform(get("/user/get?id=62287c931be61542fed8cca1")
                        .content(objectMapper.writeValueAsString(userDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), objectMapper.writeValueAsString(userDTO));
    }

    @Test
    void insertUser() throws Exception {
        when(userService.insertUser(name, email, reviewIds, watchItemIds)).thenReturn(userDTO);

        MvcResult result = mockMvc.perform(post("/user/insert?name=Catalina&email=catalina@mymail.com&reviewIds=&watchItemIds=")
                        .content(objectMapper.writeValueAsString(userDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), objectMapper.writeValueAsString(userDTO));
    }

    @Test
    void updateUser() throws Exception {
        when(userService.updateUser(id, updatedName, updatedEmail, reviewIds, watchItemIds)).thenReturn(updatedUserDTO);

        MvcResult result = mockMvc.perform(put("/user/update?id=62287c931be61542fed8cca1&name=Olivia&email=olivia@mymail.com&reviewIds=&watchItemIds=")
                        .content(objectMapper.writeValueAsString(updatedUserDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), objectMapper.writeValueAsString(updatedUserDTO));
    }

    @Test
    void deleteUser() throws Exception {
        when(userService.deleteUser(id)).thenReturn(deleteResponse);

        MvcResult result = mockMvc.perform(delete("/user/delete?id=62287c931be61542fed8cca1")
                        .content(objectMapper.writeValueAsString(deleteResponse))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), deleteResponse);
    }
}