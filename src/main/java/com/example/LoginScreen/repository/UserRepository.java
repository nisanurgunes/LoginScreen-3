package com.example.LoginScreen.repository;

import com.example.LoginScreen.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
@EnableMongoRepositories
public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByUsernameandPassword(String username, String password);
}
