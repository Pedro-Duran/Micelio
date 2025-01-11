package com.puredo.blog.Controller;

import com.puredo.blog.DTO.UserDTO;
import com.puredo.blog.DTO.PostDTO;
import com.puredo.blog.Entity.User;
import com.puredo.blog.User.UserService;
import com.puredo.blog.Entity.Post;
import com.puredo.blog.Post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "http://localhost:3000")
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
    public ResponseEntity<PostDTO.Response.Post> createPost(@RequestBody PostDTO.Request.Create request) {
        System.out.println(request.getAuthor().getUsername());

        Optional<User> author = userService.findByUserName(request.getAuthor().getUsername());
        System.out.println(author);
        if (author.isEmpty()) {
            return ResponseEntity.badRequest().body(null); // Retorna erro se o autor não existir
        }

        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setAuthor(author.get());
        post.setLinks(request.getLinks());
        post.setSubject(request.getSubject()); // Use a entidade diretamente

        Post createdPost = postService.createPost(post);

        // Converter o autor para o DTO UsuarioPublico
        UserDTO.Response.UsuarioPublico authorDTO = convertToUsuarioPublico(createdPost.getAuthor());

        PostDTO.Response.Post response = new PostDTO.Response.Post(
                createdPost.getId(),
                createdPost.getTitle(),
                createdPost.getContent(),
                authorDTO,
                createdPost.getCreatedAt().toString(),
                createdPost.getLinks(),
                createdPost.getSubject()
        );

        return ResponseEntity.ok(response);
    }


    private UserDTO.Response.UsuarioPublico convertToUsuarioPublico(com.puredo.blog.Entity.User user) {
        return new UserDTO.Response.UsuarioPublico(
                user.getId(),
                user.getUsername(),
                null // Se necessário, converta a lista de posts para PostSummary
        );
    }

    // Endpoint para listar todos os posts
    @GetMapping("/verPosts")
    public ResponseEntity<List<PostDTO.Response.Post>> getAllPosts() {


        List<Post> posts = postService.getAllPosts();

        // Converte cada Post em um DTO de resposta pública
        List<PostDTO.Response.Post> responses = posts.stream()
                .map(post -> new PostDTO.Response.Post(
                        post.getId(),
                        post.getTitle(),
                        post.getContent(),
                        convertToUsuarioPublico(post.getAuthor()),
                        post.getCreatedAt().toString(),
                        post.getLinks(),
                        post.getSubject()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    // Endpoint para atualizar um post
    @PutMapping("/updatePost")
    public ResponseEntity<PostDTO.Response.Post> updatePost(@RequestParam String title, @RequestBody PostDTO.Request.Update request) {
        Optional<Post> existingPost = postService.findPostByTitle(title);
        if (existingPost.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retorna 404 se o post não for encontrado
        }
        System.out.println("IMPRIMINDO O EXISTING POST ======= " + existingPost.get().getTitle());
        existingPost.get().setTitle(request.getTitle());
        existingPost.get().setContent(request.getContent());



        Post updatedPost = existingPost.get();
        updatedPost.setLinks(request.getLinks());
        updatedPost = postService.updatePost(updatedPost);
        UserDTO.Response.UsuarioPublico usuarioPublico = convertToUsuarioPublico(updatedPost.getAuthor());

        PostDTO.Response.Post response = new PostDTO.Response.Post(
                updatedPost.getId(),
                updatedPost.getTitle(),
                updatedPost.getContent(),
                usuarioPublico,
                updatedPost.getCreatedAt().toString(),
                request.getLinks(),
                request.getSubject()
        );

        return ResponseEntity.ok(response);
    }

    // Remover um post pelo ID
    @DeleteMapping("/deletePost")
    public ResponseEntity<Void> deletePost(@RequestParam Long id) {
        Optional<Post> existingPost = postService.getPostByID(id);
        if (existingPost.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retorna 404 se o post não for encontrado
        }

        postService.deletePostById(existingPost.get().getId());
        return ResponseEntity.noContent().build(); // Retorna 204 (No Content) após exclusão
    }

    @GetMapping("/subjects")
    public List<String> getSubjects() {
        return postService.getDistinctSubjects();
    }

    @GetMapping("/postsIdForThisSubject")
    public HashMap< Long, String> getPostsIdsForThisSubject(@RequestParam String subject) {
       return postService.findPostsBySubject(subject);
    }
}
