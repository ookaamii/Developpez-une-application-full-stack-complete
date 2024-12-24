package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.Subscription;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubscriptionRepository  extends JpaRepository<Subscription, Long> {

    @Transactional
    @Modifying
    void deleteSubscriptionByTopicAndUser(Topic topic, User user);

    List<Subscription> findAllByUser(User user);

}
