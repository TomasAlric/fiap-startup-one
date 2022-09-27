package br.com.fiap.health.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ErroValidacao extends ErroPadrao {
    private static final long serialVersionUID = 1L;

    private List<CampoMensagem> erros = new ArrayList<>();

    public List<CampoMensagem> getErrors() {
        return erros;
    }

    public void addError(String fieldName, String message) {
        erros.add(new CampoMensagem(fieldName, message));
    }
}
