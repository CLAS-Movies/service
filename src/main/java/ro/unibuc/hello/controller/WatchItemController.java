package ro.unibuc.hello.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.hello.data.*;
import ro.unibuc.hello.dto.WatchItemDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WatchItemController {

    @Autowired
    private WatchItemRepository watchItemRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/watchItem/getAll")
    @ResponseBody
    public List<WatchItemDTO> getWatchItems() {
        ArrayList<WatchItemDTO> WatchItemDTOs = new ArrayList<>();
        watchItemRepository.findAll().forEach(watchItemEntity -> WatchItemDTOs.add(new WatchItemDTO(watchItemEntity)));
        return WatchItemDTOs;
    }

    @GetMapping("/watchItem/get")
    @ResponseBody
    public WatchItemDTO getWatchItem(@RequestParam(name="movieId") String movieId, @RequestParam(name="userId") String userId) {
        MovieEntity movie = movieRepository.findById(String.valueOf(new ObjectId(movieId))).orElse(null);
        UserEntity user = userRepository.findById(String.valueOf(new ObjectId(userId))).orElse(null);
        System.out.println("data " + movieId + " " + userId);
        if(movie != null && user != null) {
            System.out.println("in " + movie.getId() + " " +  user.getId());
            WatchItemEntity watchItem = watchItemRepository.findById(String.valueOf(new WatchItemEntity.CompositeKey(movie, user))).orElse(null);
            if(watchItem != null) {
                System.out.println("save");
                return new WatchItemDTO(watchItem);
            }
        }
        System.out.println("out");
        return null;
    }

    @PostMapping("/watchItem/insert")
    @ResponseBody
    public WatchItemDTO insertWatchItem(@RequestParam(name="movieId") String movieId, @RequestParam(name="userId") String userId) {
        MovieEntity movie = movieRepository.findById(String.valueOf(new ObjectId(movieId))).orElse(null);
        UserEntity user = userRepository.findById(String.valueOf(new ObjectId(userId))).orElse(null);
        if(movie != null && user != null)
            return new WatchItemDTO(watchItemRepository.save(new WatchItemEntity(new WatchItemEntity.CompositeKey(movie, user))));
        else 
            return null;
    }

    @PutMapping("/watchItem/update")
    @ResponseBody
    public WatchItemDTO updateWatchItem(
            @RequestParam(name="movieId") String movieId, @RequestParam(name="userId") String userId,
            @RequestParam(name="newMovieId", required = false) String newMovieId, @RequestParam(name="newUserId", required = false) String newUserId
                                        ) {
        WatchItemEntity watchItem = null;
        MovieEntity movie = movieRepository.findById(String.valueOf(new ObjectId(movieId))).orElse(null);
        UserEntity user = userRepository.findById(String.valueOf(new ObjectId(userId))).orElse(null);
        if(movie != null && user != null)
            watchItem = watchItemRepository.findById(String.valueOf(new WatchItemEntity.CompositeKey(movie, user))).orElse(null);
        if(watchItem != null) {
            if(newMovieId != null) {
                MovieEntity newMovie = movieRepository.findById(String.valueOf(new ObjectId(newMovieId))).orElse(null);
                if(newMovie != null)
                    watchItem.setId(new WatchItemEntity.CompositeKey(newMovie, watchItem.getId().getUser()));
            }
            if(newUserId != null) {
                UserEntity newUser = userRepository.findById(String.valueOf(new ObjectId(newUserId))).orElse(null);
                if(newUser != null)
                    watchItem.setId(new WatchItemEntity.CompositeKey(watchItem.getId().getMovie(), newUser));
            }
            return new WatchItemDTO(watchItemRepository.save(watchItem));
        } else
            return  null;
    }

    @DeleteMapping("/watchItem/delete")
    @ResponseBody
    public void deleteWatchItem(@RequestParam(name="movieId") String movieId, @RequestParam(name="userId") String userId) {
        MovieEntity movie = movieRepository.findById(String.valueOf(new ObjectId(movieId))).orElse(null);
        UserEntity user = userRepository.findById(String.valueOf(new ObjectId(userId))).orElse(null);
        if(movie != null && user != null)
            watchItemRepository.deleteById(String.valueOf(new WatchItemEntity.CompositeKey(movie, user)));
    }
}
