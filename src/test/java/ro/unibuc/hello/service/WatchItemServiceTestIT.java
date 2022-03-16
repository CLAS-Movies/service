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

        assertEquals(result.size(), 13);
        assertEquals(result.get(12).getId(), "222222222222222222222224");
        assertEquals(result.get(12).getMovie().getId(), "222222222222222222222222");
        assertEquals(result.get(12).getUser().getId(), "222222222222222222222221");
    }
}

