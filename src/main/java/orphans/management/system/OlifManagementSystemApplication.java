package orphans.management.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import orphans.management.system.model.User;
import orphans.management.system.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class OlifManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OlifManagementSystemApplication.class, args);
	}

	//@Bean
	public User insertAdmin(UserRepository userRepository) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
 		User user = new User();
		user.setFirstName("Hidaya");
		user.setLastName("Bakar");
		user.setUsername("Hiba");
		user.setPassword(passwordEncoder.encode("12345678"));
		user.setEmail("hidayabakaromar01@.com");
		user.setPhoneNumber("0710 931 618");
		user.setAddress("Jumbi");
		user.setGender("Female");
		user.setRole("ROLE_ADMIN");
		user.setActive(true);
		user.setNotLocked(true);
		userRepository.save(user);
		return user;
	}

//Responsible for front end connection

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList(
				"Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin", "Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"
		));
		corsConfiguration.setExposedHeaders(Arrays.asList(
				"Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization", "Access-Control-Allow-Origin",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"
		));
		corsConfiguration.setAllowedMethods(Arrays.asList(
				"GET", "POST", "PUT", "DELETE", "OPTIONS"
		));

		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

}
