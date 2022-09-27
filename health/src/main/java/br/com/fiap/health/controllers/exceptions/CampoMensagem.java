package br.com.fiap.health.controllers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CampoMensagem implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fieldName;

    private String message;

}