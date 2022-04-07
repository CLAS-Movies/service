package ro.unibuc.hello.controller;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.hello.dto.WatchItemDTO;
import ro.unibuc.hello.service.WatchItemService;

import java.util.List;

@RestController
public class WatchItemController {

    @Autowired
    private WatchItemService watchItemService;

    @Autowired
    MeterRegistry metricsRegistry;

    @GetMapping("/watchItem/getAll")
    @ResponseBody
    @Timed(value = "watchItem.getWatchItems.time", description = "Time taken to return watchItems")
    @Counted(value = "watchItem.getWatchItems.count", description = "Times watchItems were returned")
    public List<WatchItemDTO> getWatchItems() {
        return watchItemService.getWatchItems();
    }

    @GetMapping("/watchItem/get")
    @ResponseBody
    @Timed(value = "watchItem.getWatchItem.time", description = "Time taken to return a watchItem")
    @Counted(value = "watchItem.getWatchItem.count", description = "Times a watchItem was returned")
    public WatchItemDTO getWatchItem(@RequestParam(name="id") String id) {
        return watchItemService.getWatchItem(id);
    }

    @PostMapping("/watchItem/insert")
    @ResponseBody
    @Timed(value = "watchItem.insertWatchItem.time", description = "Time taken to insert a watchItem")
    @Counted(value = "watchItem.insertWatchItem.count", description = "Times a watchItem was inserted")
    public WatchItemDTO insertWatchItem(@RequestParam(name="movieId") String movieId, @RequestParam(name="userId") String userId) {
        return  watchItemService.insertWatchItem(movieId, userId);
    }

    @PutMapping("/watchItem/update")
    @ResponseBody
    @Timed(value = "watchItem.updateWatchItem.time", description = "Time taken to update a watchItem")
    @Counted(value = "watchItem.updateWatchItem.count", description = "Times a watchItem was updated")
    public WatchItemDTO updateWatchItem(
            @RequestParam(name="id") String id,
            @RequestParam(name="newMovieId", required = false) String newMovieId, @RequestParam(name="newUserId", required = false) String newUserId
                                        ) {
        return watchItemService.updateWatchItem(id, newMovieId, newUserId);
    }

    @DeleteMapping("/watchItem/delete")
    @ResponseBody
    @Timed(value = "watchItem.deleteWatchItem.time", description = "Time taken to delete a watchItem")
    @Counted(value = "watchItem.deleteWatchItem.count", description = "Times a watchItem was deleted")
    public String deleteWatchItem(@RequestParam(name="id") String id) {
        return watchItemService.deleteWatchItem(id);
    }
}
