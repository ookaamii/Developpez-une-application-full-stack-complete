package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.response.TopicDTO;
import java.util.List;

public interface TopicService {

    List<TopicDTO> findAll();

}
