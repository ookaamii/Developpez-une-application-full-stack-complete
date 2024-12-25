package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.configuration.exception.NotFoundException;
import com.openclassrooms.mddapi.dto.response.TopicDTO;
import java.util.List;

/**
 * Service pour gérer les thèmes.
 */
public interface TopicService {

    /**
     * Permet d'afficher tous les thèmes.
     *
     * @return La liste des thèmes.
     */
    List<TopicDTO> findAll();

}
