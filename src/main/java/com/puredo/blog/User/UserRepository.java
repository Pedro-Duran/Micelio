package com.puredo.blog.User;


import com.puredo.blog.DTO.UserDTO;
import com.puredo.blog.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query(value = "SELECT * FROM USERS", nativeQuery = true)
    List<User> findAllNative();

}
