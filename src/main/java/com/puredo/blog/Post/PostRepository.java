package com.puredo.blog.Post;


import com.puredo.blog.DTO.PostDTO;
import com.puredo.blog.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    public Optional<Post> findPostByTitle(String title);

    @Query("SELECT DISTINCT p.subject FROM Post p")
    List<String> findDistinctSubjects();


}
