package ro.unibuc.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.hello.dto.WatchItemDTO;
import ro.unibuc.hello.service.WatchItemService;

import java.util.List;

@RestController
public class WatchItemController {

    @Autowired
    private WatchItemService watchItemService;

    @GetMapping("/watchItem/getAll")
    @ResponseBody
    public List<WatchItemDTO> getWatchItems() {
        return watchItemService.getWatchItems();
    }

    @GetMapping("/watchItem/get")
    @ResponseBody
    public WatchItemDTO getWatchItem(@RequestParam(name="id") String id) {
        return watchItemService.getWatchItem(id);
    }

    @PostMapping("/watchItem/insert")
    @ResponseBody
    public WatchItemDTO insertWatchItem(@RequestParam(name="movieId") String movieId, @RequestParam(name="userId") String userId) {
        return  watchItemService.insertWatchItem(movieId, userId);
    }

    @PutMapping("/watchItem/update")
    @ResponseBody
    public WatchItemDTO updateWatchItem(
            @RequestParam(name="id") String id,
            @RequestParam(name="newMovieId", required = false) String newMovieId, @RequestParam(name="newUserId", required = false) String newUserId
                                        ) {
        return watchItemService.updateWatchItem(id, newMovieId, newUserId);
    }

    @DeleteMapping("/watchItem/delete")
    @ResponseBody
    public String deleteWatchItem(@RequestParam(name="id") String id) {
        return watchItemService.deleteWatchItem(id);
    }
}
