package ro.unibuc.hello.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ro.unibuc.hello.data.UserEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDTOTest {

    String id;
    String name;
    String email;
    UserDTO userDTO;
    UserDTO userDTO2;
    int hash;
    String userDTOString;

    @BeforeEach
    void setUp() {
        id = "62287c931be61542fed8cca1";
        name = "Miclaus";
        email = "miclaus@mymail.com";
        userDTO = new UserDTO();
        userDTO2 = new UserDTO(new UserEntity(name, email));
        hash = Objects.hash(id, name, email);
        userDTOString = "UserDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';

        userDTO.setId(id);
        userDTO.setName(name);
        userDTO.setEmail(email);
        userDTO2.setId(id);
    }

    @Test
    void getId() {
        assertEquals(id, userDTO.getId());
    }

    @Test
    void getName() {
        assertEquals(name, userDTO.getName());
    }

    @Test
    void getEmail() {
        assertEquals(email, userDTO.getEmail());
    }

    @Test
    void testEquals() {
        assertEquals(userDTO, userDTO2);
    }

    @Test
    void testHashCode() {
        assertEquals(hash, userDTO.hashCode());
    }

    @Test
    void testToString() {
        assertEquals(userDTOString, userDTO.toString());
    }
}