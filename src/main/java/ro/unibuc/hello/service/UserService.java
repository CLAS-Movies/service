package ro.unibuc.hello.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.unibuc.hello.data.*;
import ro.unibuc.hello.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private WatchItemRepository watchItemRepository;

    public List<UserDTO> getUsers() {
        ArrayList<UserDTO> userDTOs = new ArrayList<>();
        userRepository.findAll().forEach(userEntity -> userDTOs.add(new UserDTO(userEntity)));
        return userDTOs;
    }

    public UserDTO getUser(String id) {
        UserEntity user = userRepository.findById(String.valueOf(new ObjectId(id))).orElse(null);
        if(user != null)
            return new UserDTO(user);
        else
            return null;
    }

    public UserDTO insertUser(String name, String email, List<String> reviewIds, List<String> watchItemIds) {
        UserEntity user = new UserEntity(name, email);
        ArrayList<ReviewEntity> reviewEntities = new ArrayList<>();
        ArrayList<WatchItemEntity> watchItemEntities = new ArrayList<>();

        if(!reviewIds.isEmpty() && reviewIds != null)
            reviewIds.forEach(id -> reviewEntities.add(reviewRepository.findById(String.valueOf(new ObjectId(id))).orElse(null)));
        else
            user.setReviews(null);

        if(!watchItemIds.isEmpty() && watchItemIds != null)
            watchItemIds.forEach(id -> watchItemEntities.add(watchItemRepository.findById(String.valueOf(new ObjectId(id))).orElse(null)));
        else
            user.setWatchItems(null);

        if(!reviewEntities.isEmpty() && reviewEntities != null)
            user.setReviews(reviewEntities);

        if(!watchItemEntities.isEmpty() && watchItemEntities != null)
            user.setWatchItems(watchItemEntities);

        return new UserDTO(userRepository.save(user));
    }

    public UserDTO updateUser(String id, String name, String email, List<String> reviewIds, List<String> watchItemIds) {
        UserEntity user = userRepository.findById(String.valueOf(new ObjectId(id))).orElse(null);
        if(user != null) {
            if(name != null)
                user.setName(name);

            if(email != null)
                user.setEmail(email);

            if(!reviewIds.isEmpty() && reviewIds != null) {
                ArrayList<ReviewEntity> reviewEntities = new ArrayList<>();
                reviewIds.forEach(reviewId -> reviewEntities.add(reviewRepository.findById(String.valueOf(new ObjectId(reviewId))).orElse(null)));
                if(!reviewEntities.isEmpty())
                    user.setReviews(reviewEntities);
            }
            else
                user.setReviews(null);

            if(!watchItemIds.isEmpty() && watchItemIds != null) {
                ArrayList<WatchItemEntity> watchItemEntities = new ArrayList<>();
                watchItemIds.forEach(watchItemId -> watchItemEntities.add(watchItemRepository.findById(String.valueOf(new ObjectId(watchItemId))).orElse(null)));
                if(!watchItemEntities.isEmpty())
                    user.setWatchItems(watchItemEntities);
            }
            else
                user.setWatchItems(null);

            return new UserDTO(userRepository.save(user));
        } else
            return  null;
    }

    public String deleteUser(String id) {
        userRepository.deleteById(String.valueOf(new ObjectId(id)));

        return "User with id " + id + " was deleted!";
    }

    public UserDTO buggedInsertUser(String name, String email, List<String> reviewIds, List<String> watchItemIds) {
        UserEntity user = new UserEntity(name, email);
        ArrayList<ReviewEntity> reviewEntities = new ArrayList<>();
        ArrayList<WatchItemEntity> watchItemEntities = new ArrayList<>();

        if(!reviewIds.isEmpty())
            reviewIds.forEach(id -> reviewEntities.add(reviewRepository.findById(String.valueOf(new ObjectId(id))).orElse(null)));
        else
            user.setReviews(null);

        if(!watchItemIds.isEmpty())
            watchItemIds.forEach(id -> watchItemEntities.add(watchItemRepository.findById(String.valueOf(new ObjectId(id))).orElse(null)));
        else
            user.setWatchItems(null);

        if(!reviewEntities.isEmpty())
            user.setReviews(reviewEntities);

        if(!watchItemEntities.isEmpty())
            user.setWatchItems(watchItemEntities);

        return new UserDTO(userRepository.save(user));
    }

}
