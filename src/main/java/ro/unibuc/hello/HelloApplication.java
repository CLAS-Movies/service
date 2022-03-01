package ro.unibuc.hello;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ro.unibuc.hello.data.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = InformationRepository.class)
public class HelloApplication {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private WatchItemRepository watchItemRepository;

	public static void main(String[] args) {
		SpringApplication.run(HelloApplication.class, args);
	}

	@PostConstruct
	public void runAfterObjectCreated() {
		UserEntity user1 = new UserEntity("Wane", "wane@wmail.com");
		MovieEntity movie1 = new MovieEntity("Avatar", "James Cameron", "James Cameron", 2009, 162);
		WatchItemEntity watchItem1 = null;

		movieRepository.deleteAll();
		reviewRepository.deleteAll();
		userRepository.deleteAll();
		watchItemRepository.deleteAll();

		UserEntity finalUser = user1 = userRepository.save(new UserEntity(user1.getName(), user1.getEmail()));
		MovieEntity finalMovie = movie1 = movieRepository.save(new MovieEntity(movie1.getTitle(), movie1.getDirector(), movie1.getWriter(), movie1.getYear(), movie1.getDuration()));

		UserEntity userEntity = userRepository.findById(String.valueOf(new ObjectId(finalUser.getId()))).orElse(null);
		MovieEntity movieEntity = movieRepository.findById(String.valueOf(new ObjectId(finalMovie.getId()))).orElse(null);

		if(movieEntity != null && userEntity != null)
			watchItem1 = watchItemRepository.save(new WatchItemEntity(new WatchItemEntity.CompositeKey(movieEntity, userEntity)));

		WatchItemEntity finalWatchItem = watchItem1;
		ArrayList<WatchItemEntity> watchItemEntityArrayList = new ArrayList<>(){{ add(finalWatchItem);}};

		user1.setWatchItems(watchItemEntityArrayList);

		userRepository.save(user1);
	}

}
