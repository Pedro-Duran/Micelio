package com.puredo.blog.User;



import com.puredo.blog.Entity.User;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;

public interface UserService {
    User createUser(User user);


    Optional<User> findByUserName(String username);
}
