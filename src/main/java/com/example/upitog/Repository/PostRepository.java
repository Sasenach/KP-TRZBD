package com.example.upitog.Repository;


import com.example.upitog.Model.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findByTitleContaining(String title);
}
