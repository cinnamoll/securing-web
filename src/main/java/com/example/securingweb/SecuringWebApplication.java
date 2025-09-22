package com.example.securingweb;

import com.example.securingweb.Entity.User;
import com.example.securingweb.Entity.UserRole;
import com.example.securingweb.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SecuringWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecuringWebApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Kiểm tra xem user đã tồn tại chưa
            if (userRepository.findByUsername("user").isEmpty()) {
                User user = new User("user", passwordEncoder.encode("password"), UserRole.USER);
                userRepository.save(user);
                System.out.println("Created USER: user/password");
            }

            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User("admin", passwordEncoder.encode("adminpass"), UserRole.ADMIN);
                userRepository.save(admin);
                System.out.println("Created ADMIN: admin/adminpass");
            }
        };
    }
}