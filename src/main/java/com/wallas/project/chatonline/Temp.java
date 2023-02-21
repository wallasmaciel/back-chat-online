package com.wallas.project.chatonline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.wallas.project.chatonline.models.User;
import com.wallas.project.chatonline.repositories.UserRepository;
import com.wallas.project.chatonline.utils.BCryptPassword;

@Configuration
public class Temp implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;
	@Override
	public void run(String... args) throws Exception {
		User wallasUser = new User(
			"Wallas Vitor", 
			"wallasmaciel919@gmail.com", 
			BCryptPassword.encode("123456"), 
			"https://images.pexels.com/photos/4323761/pexels-photo-4323761.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
		);
		// Verify if email not exists before insert 
		if (userRepository.findByEmail(wallasUser.getEmail()) == null)
			userRepository.insert(wallasUser);
		
		User manuUser = new User(
			"Manu Gabriela", 
			"manu@gmail.com", 
			BCryptPassword.encode("123456"), 
			"https://images.pexels.com/photos/5081397/pexels-photo-5081397.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
		);
		// Verify if email not exists before insert 
		if (userRepository.findByEmail(manuUser.getEmail()) == null)
			userRepository.insert(manuUser);
		// 
		System.out.println("-------------------------------------");
		for (User user : userRepository.findAll()) {
			System.out.println(user.getUser_id());
			System.out.println(user.getName());
			System.out.println(user.getUrl_picture());
		}
		System.out.println("-------------------------------------");
	}
}
