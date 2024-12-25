package com.openclassrooms.mddapi.configuration;

import com.openclassrooms.mddapi.configuration.exception.BadRequestException;
import com.openclassrooms.mddapi.configuration.exception.NotFoundException;
import com.openclassrooms.mddapi.configuration.exception.UnauthorizedException;
import com.openclassrooms.mddapi.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * Classe globale pour gérer les exceptions.
 * Permet de capturer les exceptions et de renvoyer des réponses formatées avec un code et un message adaptés.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Gère les exceptions de type {@link NotFoundException}.
     *
     * @param e L'exception capturée.
     * @param request La requête HTTP en cours.
     * @return Une réponse HTTP avec un message d'erreur, le code 404, le path et la date.
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e, HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage(e.getMessage());
        error.setCode(HttpStatus.NOT_FOUND.value());
        error.setPath(request.getRequestURI());
        error.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Gère les exceptions de type {@link BadRequestException}.
     *
     * @param e L'exception capturée.
     * @param request La requête HTTP en cours.
     * @return Une réponse HTTP avec un message d'erreur, le code 400, le path et la date.
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException e, HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage(e.getMessage());
        error.setCode(HttpStatus.BAD_REQUEST.value());
        error.setPath(request.getRequestURI());
        error.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Gère les exceptions de type {@link UnauthorizedException}.
     *
     * @param e L'exception capturée.
     * @param request La requête HTTP en cours.
     * @return Une réponse HTTP avec un message d'erreur, le code 401, le path et la date.
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException e, HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage("Erreur d'authentification");
        error.setCode(HttpStatus.UNAUTHORIZED.value());
        error.setPath(request.getRequestURI());
        error.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Gère toutes les autres exceptions non spécifiées.
     *
     * @param e L'exception capturée.
     * @param request La requête HTTP en cours.
     * @return Une réponse HTTP avec un message générique, le code 500, le path et la date.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage("Une erreur inattendue est survenue.");
        error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setPath(request.getRequestURI());
        error.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
