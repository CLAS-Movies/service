package ro.unibuc.hello;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.mapping.DBRef;
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

	public static void main(String[] args) {
		SpringApplication.run(HelloApplication.class, args);
	}

	@PostConstruct
	public void runAfterObjectCreated() {
		UserEntity user1 = new UserEntity("Jack", "jack@wmail.com");
		UserEntity user2 = new UserEntity("Russell Hobbs", "russelhobbs@yahoo.com");
		UserEntity user3 = new UserEntity("Robert Smith", "robertsmith@yahoo.com");
		UserEntity user4 = new UserEntity("Cassidy Beth", "cassy@gmail.com");
		UserEntity user5 = new UserEntity("Thomas Miller", "tmiller@yahoo.com");

		MovieEntity movie1 = new MovieEntity("Avatar", "James Cameron", "James Cameron", 2009, 162);
		MovieEntity movie2 = new MovieEntity("Amadeus","Milos Forman", "Peter Shaffer", 1984,160);
		MovieEntity movie3 = new MovieEntity("Birdemic:Shock and Terror","James Nguyen", "James Nguyen", 2010, 105);
		MovieEntity movie4 = new MovieEntity("Meet the Parents","Jay Roach","Greg Glienna" ,2000,108);
		MovieEntity movie5 = new MovieEntity("The Way Back","Gavin O'Connor","Brad Ingelsby",2020,108);

		ReviewEntity review1 = new ReviewEntity("Great movie", 8);
		ReviewEntity review2 = new ReviewEntity("Amadeus is a film of high accolade...this film is grand on every level and I'm glad to" +
				"have experienced it",9);
		ReviewEntity review3 = new ReviewEntity("Should have been better.",4);
		ReviewEntity review4 = new ReviewEntity("7",7);
		ReviewEntity review5 = new ReviewEntity("Can this even be considered a movie?!",1);
		ReviewEntity review6 = new ReviewEntity("Star-driven comedy at times offensive and funny",6);
		ReviewEntity review7 = new ReviewEntity("Owes about 55 percent of its charm to De Niro,who can be hilarious" +
				"just sitting there.", 8);
		ReviewEntity review8 = new ReviewEntity("Without a Doubt a Must-See Mvoie",2);
		ReviewEntity review9 = new ReviewEntity("The film's awesome achievemnts will remind audiences how rich and challenging " +
				"a cinematic experience can be",9);
		ReviewEntity review10 = new ReviewEntity("A great movie by actor re-inacting the life of Amadeus(Thanks Tom)!!!!!",10);

		ArrayList<ReviewEntity> reviews1 = new ArrayList<>(){{ add(review1);}};
		ArrayList<ReviewEntity> reviews2 = new ArrayList<>(){{add(review2);
			add(review3);add(review9);add(review10);}};
		ArrayList<ReviewEntity> reviews3 = new ArrayList<>(){{add(review5);
			add(review8);}};
		ArrayList<ReviewEntity> reviews4 = new ArrayList<>(){{add(review6);add(review7);
			add(review4);}};
		ArrayList<ReviewEntity> reviews5= new ArrayList<>(){{}};


		ArrayList<MovieEntity> movieWatchList1 = new ArrayList<>(){{ add(movie1);}};
		ArrayList<MovieEntity> movieWatchList2 = new ArrayList<>(){{ add(movie1);add(movie2);add(movie3);
			add(movie4);add(movie5);}};
		ArrayList<MovieEntity> movieWatchList3 = new ArrayList<>(){{ add(movie2);add(movie3)}};
		ArrayList<MovieEntity> movieWatchList4 = new ArrayList<>(){{ add(movie1);}};
		ArrayList<MovieEntity> movieWatchList5 = new ArrayList<>(){{}};

		ArrayList<UserEntity> userWatchList1 = new ArrayList<>(){{ add(user1)}};
		ArrayList<UserEntity> userWatchList2 = new ArrayList<>(){{ add(user1);add(user2);add(user3);add(user4);
			add(user5);}};
		ArrayList<UserEntity> userWatchList3 = new ArrayList<>(){{ add(user1);add(user2);add(user3)}};
		ArrayList<UserEntity> userWatchList4 = new ArrayList<>(){{ add(user3);add(user4);
			add(user5);}};
		ArrayList<UserEntity> userWatchList5 = new ArrayList<>(){{}};

		movieRepository.deleteAll();
		reviewRepository.deleteAll();
		userRepository.deleteAll();

//		userRepository.save(new UserEntity(user1.getName(), user1.getEmail(), reviews, movieWatchList));
//		movieRepository.save(new MovieEntity(movie1.getTitle(), movie1.getDirector(), movie1.getWriter(), movie1.getYear(), movie1.getDuration(), reviews, userWatchList));
//		reviewRepository.save(new ReviewEntity(review1.getComment(), review1.getScore(), movie1));
	}

}
