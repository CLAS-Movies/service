package ro.unibuc.hello.dto;

import org.springframework.data.annotation.Id;
import ro.unibuc.hello.data.UserEntity;

import java.util.ArrayList;
import java.util.Objects;

public class UserDTO {
    @Id
    private String id;
    private String name;
    private String email;
    private ArrayList<ReviewDTO> reviews;
    private ArrayList<WatchItemDTO> watchItems;

    public UserDTO() {
    }

    public UserDTO(UserEntity user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        ArrayList<ReviewDTO> reviewDTOs = new ArrayList<>();
        user.getReviews().forEach(reviewEntity -> reviewDTOs.add(new ReviewDTO(reviewEntity)));
        this.reviews = reviewDTOs;
        ArrayList<WatchItemDTO> watchItemDTOs = new ArrayList<>();
        user.getWatchItems().forEach(watchItemEntity -> watchItemDTOs.add(new WatchItemDTO(watchItemEntity)));
        this.watchItems = watchItemDTOs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<ReviewDTO> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<ReviewDTO> reviews) {
        this.reviews = reviews;
    }

    public ArrayList<WatchItemDTO> getWatchItems() {
        return watchItems;
    }

    public void setWatchItems(ArrayList<WatchItemDTO> watchItems) {
        this.watchItems = watchItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return id.equals(userDTO.id) && name.equals(userDTO.name) && email.equals(userDTO.email) && Objects.equals(reviews, userDTO.reviews) && Objects.equals(watchItems, userDTO.watchItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, reviews, watchItems);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", reviews=" + reviews +
                ", watchItems=" + watchItems +
                '}';
    }
}
