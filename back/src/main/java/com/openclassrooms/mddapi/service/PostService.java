package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.configuration.exception.NotFoundException;
import com.openclassrooms.mddapi.dto.request.PostRequestDTO;
import com.openclassrooms.mddapi.dto.response.PostResponseDTO;
import com.openclassrooms.mddapi.dto.response.ResponseDTO;

import java.util.List;

/**
 * Service pour gérer les articles.
 */
public interface PostService {

    /**
     * Permet d'afficher un article.
     *
     * @param id L'identifiant de l'article.
     * @return L'article.
     * @throws NotFoundException Si l'article n'existe pas.
     */
    PostResponseDTO getPost(Long id);

    /**
     * Permet à l'utilisateur de créer un article.
     *
     * @param postDTO Les données DTO du formulaire que l'utilisateur a rempli.
     * @return Un message de succès.
     * @throws NotFoundException Si le thème n'existe pas.
     */
    ResponseDTO create(PostRequestDTO postDTO);

    /**
     * Permet d'afficher tous les articles par thème où l'utilisateur est abonné.
     *
     * @param sort L'ordre par l'id dans lequel les données doivent être triées.
     * @return Les articles.
     */
    List<PostResponseDTO> findAllByTopics(String sort);

}
