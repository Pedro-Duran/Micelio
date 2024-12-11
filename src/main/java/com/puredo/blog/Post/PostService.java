package com.puredo.blog.Post;


import com.puredo.blog.DTO.PostDTO;
import com.puredo.blog.Entity.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Post createPost(Post post);

    List<Post> getAllPosts();


    Post updatePost(Post existingPost);

    void deletePostById(Long id);

    Optional<Post> findPostByTitle(String title);

    Optional<Post> getPostByID(Long id);


}
