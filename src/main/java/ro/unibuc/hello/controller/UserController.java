package ro.unibuc.hello.controller;

import java.util.List;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.hello.dto.UserDTO;
import ro.unibuc.hello.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    MeterRegistry metricsRegistry;

    @GetMapping("/user/getAll")
    @ResponseBody
    @Timed(value = "user.getUsers.time", description = "Time taken to return users")
    @Counted(value = "user.getUsers.count", description = "Times users were returned")
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/user/get")
    @ResponseBody
    @Timed(value = "user.getUser.time", description = "Time taken to return an user")
    @Counted(value = "user.getUser.count", description = "Times a user was returned")
    public UserDTO getUser(@RequestParam(name="id") String id) {
        return userService.getUser(id);
    }

    @PostMapping("/user/insert")
    @ResponseBody
    @Timed(value = "user.insertUser.time", description = "Time taken to insert an insert")
    @Counted(value = "user.insertUser.count", description = "Times a insert was inserted")
    public UserDTO insertUser(
            @RequestParam(name="name") String name, @RequestParam(name="email") String email,
            @RequestParam(name="reviewIds") List<String> reviewIds, @RequestParam(name="watchItemIds") List<String> watchItemIds
    ) {
        return userService.insertUser(name, email, reviewIds, watchItemIds);
    }

    @PutMapping("/user/update")
    @ResponseBody
    @Timed(value = "user.updateUser.time", description = "Time taken to update an user")
    @Counted(value = "user.updateUser.count", description = "Times a user was updated")
    public UserDTO updateUser(
            @RequestParam(name="id") String id, @RequestParam(name="name", required = false) String name, @RequestParam(name="email", required = false) String email,
            @RequestParam(name="reviewIds") List<String> reviewIds, @RequestParam(name="watchItemIds") List<String> watchItemIds
    ) {
        return userService.updateUser(id, name, email, reviewIds, watchItemIds);
    }

    @DeleteMapping("/user/delete")
    @ResponseBody
    @Timed(value = "user.deleteUser.time", description = "Time taken to delete an user")
    @Counted(value = "user.deleteUser.count", description = "Times a user was deleted")
    public String deleteUser(@RequestParam(name="id") String id) {
        return userService.deleteUser(id);
    }

    @PostMapping("/user/bugGenerator")
    @ResponseBody
    @Timed(value = "user.bugGenerator.time", description = "Time taken to bug an insert")
    @Counted(value = "user.bugGenerator.count", description = "Times a bug was inserted")
    public UserDTO buggedInsertUser(
            @RequestParam(name="name") String name, @RequestParam(name="email") String email,
            @RequestParam(name="reviewIds") List<String> reviewIds, @RequestParam(name="watchItemIds") List<String> watchItemIds
    ) {
        return userService.buggedInsertUser(name, email, reviewIds, watchItemIds);
    }

}
