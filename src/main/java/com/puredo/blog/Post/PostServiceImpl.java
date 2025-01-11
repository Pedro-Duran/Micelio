package com.puredo.blog.Post;



import com.puredo.blog.DTO.PostDTO;
import com.puredo.blog.Entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }


    @Override
    public Post updatePost(Post existingPost) {
        return postRepository.save(existingPost); // Atualiza o post no banco
    }

    @Override
    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Optional<Post> findPostByTitle(String title) {
        return postRepository.findPostByTitle(title);
    }

    @Override
    public Optional<Post> getPostByID(Long id){
        return postRepository.findById(id);
    }

    public List<String> getDistinctSubjects() {
        return postRepository.findDistinctSubjects();
    }

    @Override
    public HashMap<Long, String> findPostsBySubject(String subject) {
        List<Object[]> results = postRepository.findPostIdsAndTitlesBySubject(subject);
        HashMap<Long, String> postMap = new HashMap<>();
        for (Object[] result : results) {
            Long id =  (Long) result[0];
            String title = (String) result[1];
            postMap.put(id, title);
        }
        return postMap;
    }
}
