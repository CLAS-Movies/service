package ro.unibuc.hello.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.hello.dto.UserDTO;
import ro.unibuc.hello.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/getAll")
    @ResponseBody
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/user/get")
    @ResponseBody
    public UserDTO getUser(@RequestParam(name="id") String id) {
        return userService.getUser(id);
    }

    @PostMapping("/user/insert")
    @ResponseBody
    public UserDTO insertUser(
            @RequestParam(name="name") String name, @RequestParam(name="email") String email,
            @RequestParam(name="reviewIds") List<String> reviewIds, @RequestParam(name="watchItemIds") List<String> watchItemIds
    ) {
        return userService.insertUser(name, email, reviewIds, watchItemIds);
    }

    @PutMapping("/user/update")
    @ResponseBody
    public UserDTO updateUser(
            @RequestParam(name="id") String id, @RequestParam(name="name", required = false) String name, @RequestParam(name="email", required = false) String email,
            @RequestParam(name="reviewIds") List<String> reviewIds, @RequestParam(name="watchItemIds") List<String> watchItemIds
    ) {
        return userService.updateUser(id, name, email, reviewIds, watchItemIds);
    }

    @DeleteMapping("/user/delete")
    @ResponseBody
    public String deleteUser(@RequestParam(name="id") String id) {
        return userService.deleteUser(id);
    }

}
