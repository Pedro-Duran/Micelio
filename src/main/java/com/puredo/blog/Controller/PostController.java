package com.puredo.blog.Controller;

import com.puredo.blog.User.UserService;
import com.puredo.blog.DTO.PostDTO;
import com.puredo.blog.model.Post;
import com.puredo.blog.Post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @Autowired
    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    // Endpoint para criar um novo post
    @PostMapping("/createPost")
    public ResponseEntity<PostDTO.Response.Public> createPost(@RequestBody PostDTO.Request.Create request) {
        var author = userService.findByUsername(request.getAuthorUsername());
        if (author == null) {
            return ResponseEntity.badRequest().body(null); // Retorna erro se o autor não existir
        }

        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setAuthor(author);

        Post createdPost = postService.createPost(post);

        PostDTO.Response.Public response = new PostDTO.Response.Public(
                createdPost.getId(),
                createdPost.getTitle(),
                createdPost.getContent(),
                createdPost.getAuthor().getUsername(),
                createdPost.getCreatedAt().toString()
        );

        return ResponseEntity.ok(response);
    }

    // Endpoint para listar todos os posts
    @GetMapping("/verPosts")
    public ResponseEntity<List<PostDTO.Response.Public>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();

        // Converte cada Post em um DTO de resposta pública
        List<PostDTO.Response.Public> responses = posts.stream()
                .map(post -> new PostDTO.Response.Public(
                        post.getId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getAuthor().getUsername(),
                        post.getCreatedAt().toString()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }
}
