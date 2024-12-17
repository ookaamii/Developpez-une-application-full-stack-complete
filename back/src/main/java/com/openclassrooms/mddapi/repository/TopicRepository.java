package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    @Query("SELECT t FROM Topic t WHERE t.id NOT IN (SELECT s.topic.id FROM Subscription s WHERE s.user.id = :userId)")
    List<Topic> findTopicsNotSubByUser(@Param("userId") Long userId);

}
