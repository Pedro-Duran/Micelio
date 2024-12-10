package com.puredo.blog.User;


import com.puredo.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Adicione métodos personalizados aqui, se necessário
}
