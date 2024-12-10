package com.puredo.blog.Post;


import com.puredo.blog.model.Post;

import java.util.List;

public interface PostService {
    Post createPost(Post post);

    List<Post> getAllPosts();
}
