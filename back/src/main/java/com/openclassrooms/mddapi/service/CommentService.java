package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.configuration.exception.NotFoundException;
import com.openclassrooms.mddapi.dto.request.CommentRequestDTO;
import com.openclassrooms.mddapi.dto.response.CommentResponseDTO;
import com.openclassrooms.mddapi.dto.response.ResponseDTO;

import java.util.List;

/**
 * Service pour gérer les commentaires liés aux articles.
 */
public interface CommentService {

    /**
     * Permet d'afficher tous les commentaires liés à un article.
     *
     * @param id L'identifiant de l'article.
     * @return La liste des commentaires et leur auteur directement.
     */
    List<CommentResponseDTO> findAllByPostId(Long id);

    /**
     * Permet à l'utilisateur de créer un commentaire lié à un article.
     *
     * @param id L'identifiant de l'article.
     * @param commentRequestDTO Les données DTO du formulaire que l'utilisateur a rempli.
     * @return Un message de succès.
     * @throws NotFoundException Si l'article n'existe pas.
     */
    ResponseDTO create(Long id, CommentRequestDTO commentRequestDTO);

}
