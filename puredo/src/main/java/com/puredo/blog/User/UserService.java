package com.puredo.blog.User;



import com.puredo.blog.model.User;

public interface UserService {
    User createUser(User user);
    User findByUsername(String username);
}
