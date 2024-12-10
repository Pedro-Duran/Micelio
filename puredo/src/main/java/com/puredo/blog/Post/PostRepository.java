package com.puredo.blog.Post;


import com.puredo.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // Adicione métodos personalizados aqui, se necessário
}
