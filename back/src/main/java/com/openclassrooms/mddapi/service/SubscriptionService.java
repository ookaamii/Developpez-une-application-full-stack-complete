package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.response.ResponseDTO;
import com.openclassrooms.mddapi.dto.response.TopicDTO;

import java.util.List;

public interface SubscriptionService {

    ResponseDTO subTopic(Long id);

    ResponseDTO unsubscribeTopic(Long id);

    List<TopicDTO> findAllByUser();

}
