package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Topic;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.topic.id IN (SELECT s.topic.id FROM Subscription s WHERE s.user.id = :userId)")
    List<Post> findAllByTopicSubscriptions(@Param("userId") Long userId, @Param("sort") Sort sort);

}
