package com.javier.libreria_springbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibreriaSpringBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibreriaSpringBackendApplication.class, args);
	}

//	@Bean
//	CommandLineRunner run(IUserDao userService) {
//		return args -> {
//			userService.saveRole(new Role(null, "ROLE_USER"));
//			userService.saveRole(new Role(null, "ROLE_ADMIN"));
//			userService.saveRole(new Role(null, "ROLE_MANAGER"));
//			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

//
//			userService.save(new User(null, "Chanchito10", "Chanchito10", "1234", Role.ADMIN));
//			userService.save(new User(null, "Chanchito11", "Chanchito11", "1234", Role.USER));
//			userService.saveUser(new User(null, "Chanchito2", "Chanchito2", "1234", new ArrayList<>()));
//			userService.saveUser(new User(null, "Chanchito3", "Chanchito3", "1234", new ArrayList<>()));
//			userService.saveUser(new User(null, "Chanchito4", "Chanchito4", "1234", new ArrayList<>()));
//
//			userService.addRoleToUser("Chanchito1", "ROLE_USER");
//			userService.addRoleToUser("Chanchito4", "ROLE_USER");
//			userService.addRoleToUser("Chanchito3", "ROLE_MANAGER");
//			userService.addRoleToUser("Chanchito2", "ROLE_USER");
//			userService.addRoleToUser("Chanchito2", "ROLE_ADMIN");
//			userService.addRoleToUser("Chanchito2", "ROLE_SUPER_ADMIN");
//		};
//	}

}
