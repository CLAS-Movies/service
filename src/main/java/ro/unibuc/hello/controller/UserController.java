package ro.unibuc.hello.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.hello.data.*;
import ro.unibuc.hello.dto.UserDTO;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private WatchItemRepository watchItemRepository;

    @GetMapping("/user/getAll")
    @ResponseBody
    public List<UserDTO> getUsers() {
        ArrayList<UserDTO> userDTOs = new ArrayList<>();
        userRepository.findAll().forEach(userEntity -> userDTOs.add(new UserDTO(userEntity)));
        return userDTOs;
    }

    @GetMapping("/user/get")
    @ResponseBody
    public UserDTO getUser(@RequestParam(name="id") String id) {
        UserEntity user = userRepository.findById(String.valueOf(new ObjectId(id))).orElse(null);
        if(user != null)
            return new UserDTO(user);
        else
            return null;
    }

    @PostMapping("/user/insert")
    @ResponseBody
    public UserEntity insertUser(
            @RequestParam(name="name") String name, @RequestParam(name="email") String email,
            @RequestParam(name="reviewIds", required = false) String reviewIds, @RequestParam(name="watchItemId", required = false) String watchItemId
    ) {
        UserEntity user = new UserEntity(name, email);
        List<String> reviewIdList = Arrays.asList(reviewIds.split(","));
        List<String> watchItemIdList = Arrays.asList(watchItemId.split(","));
        ArrayList<ReviewEntity> reviewEntities = new ArrayList<>();
        ArrayList<WatchItemEntity> watchItemEntities = new ArrayList<>();
        reviewIdList.forEach(id -> reviewEntities.add(reviewRepository.findById(String.valueOf(new ObjectId(id))).orElse(null)));
        watchItemIdList.forEach(id -> watchItemEntities.add(watchItemRepository.findById(String.valueOf(new ObjectId(id))).orElse(null)));
        if(!reviewEntities.isEmpty())
            user.setReviews(reviewEntities);
        if(!watchItemEntities.isEmpty())
            user.setWatchItems(watchItemEntities);
        return userRepository.save(user);
    }

    @PutMapping("/user/update")
    @ResponseBody
    public UserDTO updateUser(@RequestParam(name="id") String id, @RequestParam(name="name", required = false) String name, @RequestParam(name="email", required = false) String email) {
        UserEntity user = userRepository.findById(String.valueOf(new ObjectId(id))).orElse(null);
        if(user != null) {
            if(name != null)
                user.setName(name);
            if(email != null)
                user.setEmail(email);
            return new UserDTO(userRepository.save(user));
        } else
            return  null;
    }

    @DeleteMapping("/user/delete")
    @ResponseBody
    public void deleteUser(@RequestParam(name="id") String id) {
        userRepository.deleteById(String.valueOf(new ObjectId(id)));
    }

}
