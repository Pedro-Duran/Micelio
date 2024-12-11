package com.puredo.blog.Controller;



import com.puredo.blog.DTO.UserDTO;

import com.puredo.blog.Entity.User;
import com.puredo.blog.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/criaUser")
    public ResponseEntity<UserDTO.Response.UsuarioPublico> createUser( @RequestBody UserDTO.Request.Create request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());

        User savedUser = userService.createUser(user);

        UserDTO.Response.UsuarioPublico response = new UserDTO.Response.UsuarioPublico(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getPosts());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/verUsuario")
    public ResponseEntity<User> getUserByUsername(@RequestParam String username) {
        Optional<User> user = userService.findByUserName(username);

        UserDTO.Response.UsuarioPublico response = new UserDTO.Response.UsuarioPublico(
                user.get().getId(),
                user.get().getUsername(),
                user.get().getPosts()
        );

        return ResponseEntity.ok(user.get());
    }
}
