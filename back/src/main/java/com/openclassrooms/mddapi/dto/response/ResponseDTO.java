package com.openclassrooms.mddapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Représente un message de réponse.
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ResponseDTO {

    private String message;

}