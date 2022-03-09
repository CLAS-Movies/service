package ro.unibuc.hello;

import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ro.unibuc.hello.controller.UserController;
import ro.unibuc.hello.data.UserEntity;
import ro.unibuc.hello.data.UserRepository;
import ro.unibuc.hello.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.data.mongodb.core.aggregation.ConditionalOperators.Cond.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserEntity.class)
class UserTests {
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@MockBean
//	UserRepository userRepository;
//
//	@MockBean
//	UserController userController;
//
//	UserEntity user;
//
//	@Test
//	void contextLoads() {
//		assertThat(userRepository).isNotNull();
//		assertThat(userController).isNotNull();
//
//
//	}
//
//	@BeforeEach
//	public void setup() {
//		user = new UserEntity("Habs", "g@g.com");
//		user.setId("1");
//
//		userController.insertUser(user.getName(), user.getEmail(), new ArrayList<String>(), new ArrayList<String>());
//	}
//
//	@Test
//	public void testUserGetAll() throws Exception {
//		List<UserDTO> userEntities = userController.getUsers();
//
//		assertThat(userEntities).isNotNull();
//	}
//
//	@Test
//	public void testUserGet() throws Exception {
//		UserDTO userEntity = userController.getUser(user.getId());
//
//		assertThat(userEntity).isNotNull();
//	}
//
//	@Test
//	public void testInsertGet() throws Exception {
//		UserDTO userEntity = userController.insertUser(user.getName(), user.getEmail(), new ArrayList<String>(), new ArrayList<String>());
//
//		assertThat(userEntity).isNotNull();
//	}
//
//	@Test
//	public void testUpdateGet() throws Exception {
//		UserEntity updatedUser = user;
//		updatedUser.setName("John");
//		UserDTO userEntity = userController.updateUser(updatedUser.getId(), updatedUser.getName(), updatedUser.getEmail(), new ArrayList<String>(), new ArrayList<String>());
//
//		assertThat(userEntity).isNotNull();
//	}
//
//	@Test
//	public void testDeleteGet() throws Exception {
//		userController.deleteUser(user.getId());
//
//		assertThat(user).isNotNull();
//	}

}
