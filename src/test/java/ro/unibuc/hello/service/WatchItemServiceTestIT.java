package ro.unibuc.hello.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.unibuc.hello.data.MovieRepository;
import ro.unibuc.hello.data.UserRepository;
import ro.unibuc.hello.data.WatchItemRepository;
import ro.unibuc.hello.dto.WatchItemDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class WatchItemServiceTestIT {

    @Autowired
    WatchItemRepository watchItemRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WatchItemService watchItemService;

    @Test
    void getWatchItems() {
        List<WatchItemDTO> result = watchItemService.getWatchItems();
        WatchItemDTO watchItemDTO = result.get(12);

        assertEquals(result.size(), 13);
        assertEquals(watchItemDTO.getId(), "222222222222222222222224");
        assertEquals(watchItemDTO.getMovie().getId(), "222222222222222222222222");
        assertEquals(watchItemDTO.getUser().getId(), "222222222222222222222221");
    }
}

