package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.response.ResponseDTO;

public interface SubscriptionService {

    ResponseDTO subTopic(Long id);

    ResponseDTO unsubscribeTopic(Long id);

}
