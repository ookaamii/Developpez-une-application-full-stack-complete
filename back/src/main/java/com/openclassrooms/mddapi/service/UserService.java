package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.configuration.exception.BadRequestException;
import com.openclassrooms.mddapi.configuration.exception.NotFoundException;
import com.openclassrooms.mddapi.configuration.exception.UnauthorizedException;
import com.openclassrooms.mddapi.dto.request.PasswordUpdateDTO;
import com.openclassrooms.mddapi.dto.response.UserUpdateDTO;
import com.openclassrooms.mddapi.dto.request.LoginDTO;
import com.openclassrooms.mddapi.dto.request.RegisterDTO;
import com.openclassrooms.mddapi.dto.response.AuthDTO;
import com.openclassrooms.mddapi.dto.response.ResponseDTO;
import com.openclassrooms.mddapi.model.User;

/**
 * Service pour gérer les utilisateurs.
 */
public interface UserService {

    /**
     * Permet à un utilisateur de s'enregistrer.
     *
     * @param registerDTO Les données DTO du formulaire rempli par l'utilisateur.
     * @return Un token.
     * @throws BadRequestException Si l'email est déjà utilisé.
     */
    AuthDTO register(RegisterDTO registerDTO);

    /**
     * Permet à un utilisateur de se connecter.
     *
     * @param loginDTO Les données DTO du formulaire rempli par l'utilisateur.
     * @return Un token.
     * @throws UnauthorizedException Si les identifiants sont incorrects.
     */
    AuthDTO login(LoginDTO loginDTO);

    /**
     * Permet de récupérer les informations de l'utilisateur connecté.
     *
     * @return L'utilisateur connecté.
     * @throws NotFoundException Si l'utilisateur n'existe pas.
     */
    User getAuthenticatedUser();

    /**
     * Permet d'afficher les informations de l'utilisateur connecté.
     *
     * @return Les informations DTO de l'utilisateur.
     */
    UserUpdateDTO getProfile();

    /**
     * Permet à l'utilisateur de modifier son profil.
     *
     * @param userDTO Les données DTO du formulaire rempli par l'utilisateur.
     * @return Un message de succès.
     */
    ResponseDTO update(UserUpdateDTO userDTO);

    /**
     * Permet à un utilisateur de modifier son mot de passe.
     *
     * @param passwordUpdateDTO Les données DTO du formulaire rempli par l'utilisateur.
     * @return Un message de succès.
     * @throws BadRequestException Si l'ancien mot de passe est incorrect.
     */
    ResponseDTO updatePassword(PasswordUpdateDTO passwordUpdateDTO);

}
