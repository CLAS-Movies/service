package ro.unibuc.hello.dto;


import org.springframework.data.annotation.Id;
import ro.unibuc.hello.data.MovieEntity;
import ro.unibuc.hello.data.ReviewEntity;
import ro.unibuc.hello.data.UserEntity;

import java.util.Objects;

public class ReviewDTO {
    @Id
    private String id;
    private String comment;
    private Integer score;
    private MovieDTO movie;
    private UserDTO user;

    public ReviewDTO() {
    }

    public ReviewDTO(ReviewEntity review) {
        this.id = review.getId();
        this.comment = review.getComment();
        this.score = review.getScore();
        MovieEntity movieEntity = review.getMovie();
        if(movieEntity != null)
            this.movie = new MovieDTO(movieEntity);
        UserEntity userEntitye = review.getUser();
        if(userEntitye != null)
            this.user = new UserDTO(review.getUser());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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
        ReviewDTO reviewDTO = (ReviewDTO) o;
        return id.equals(reviewDTO.id) && comment.equals(reviewDTO.comment) && score.equals(reviewDTO.score) && movie.equals(reviewDTO.movie) && user.equals(reviewDTO.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, comment, score, movie, user);
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "id='" + id + '\'' +
                ", comment='" + comment + '\'' +
                ", score=" + score +
                ", movie=" + movie +
                ", user=" + user +
                '}';
    }
}
