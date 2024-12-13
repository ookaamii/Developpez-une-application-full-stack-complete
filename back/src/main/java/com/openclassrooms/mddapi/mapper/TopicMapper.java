package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.response.TopicDTO;
import com.openclassrooms.mddapi.model.Topic;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TopicMapper {

    TopicDTO topicToTopicDTO(Topic topic);

    List<TopicDTO> topicsToTopicDTOs(List<Topic> topics);

    // Mappe les champs de UserDTO vers User
    Topic topicDTOToTopic(TopicDTO topicDTO);

}
