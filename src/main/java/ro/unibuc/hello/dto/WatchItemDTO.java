package ro.unibuc.hello.dto;

import org.springframework.data.mongodb.core.mapping.DBRef;
import ro.unibuc.hello.data.MovieEntity;
import ro.unibuc.hello.data.UserEntity;
import ro.unibuc.hello.data.WatchItemEntity;

import java.util.Objects;

public class WatchItemDTO {
    private MovieDTO movie;
    private UserDTO user;

    public WatchItemDTO(WatchItemEntity watchItem) {
        this.movie = new MovieDTO(watchItem.getId().getMovie());
        this.user = new UserDTO(watchItem.getId().getUser());
    }

    public WatchItemDTO() {
    }

    public MovieDTO getMovie() {
        return movie;
    }

    public void setMovie(MovieDTO movie) {
        this.movie = movie;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WatchItemDTO that = (WatchItemDTO) o;
        return movie.equals(that.movie) && user.equals(that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie, user);
    }

    @Override
    public String toString() {
        return "WatchItemDTO{" +
                "movie=" + movie +
                ", user=" + user +
                '}';
    }
}
