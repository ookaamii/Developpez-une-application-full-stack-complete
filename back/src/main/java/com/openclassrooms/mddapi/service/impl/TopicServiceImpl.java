package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.dto.response.TopicDTO;
import com.openclassrooms.mddapi.mapper.TopicMapper;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.service.SubscriptionService;
import com.openclassrooms.mddapi.service.TopicService;
import com.openclassrooms.mddapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implémentation de {@link TopicService}.
 */
@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;
    private final UserService userService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TopicDTO> findAll() {
        // Récupérer tous les thèmes
        List<Topic> topics = topicRepository.findAll();

        return topicMapper.topicsToTopicDTOs(topics);
    }

}
