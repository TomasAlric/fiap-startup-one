package br.com.fiap.health.config;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TelefoneValidator implements ConstraintValidator<Telefone, String> {

    private final Pattern padrao = Pattern.compile("\\(?\\d{2,}\\)?[ -]?\\d{4,}[\\-\\s]?\\d{4}");

    @Override
    public void initialize(Telefone ann) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null || "".equals(value)) {
            return true;
        }

        Matcher matcher = padrao.matcher(value);
        return matcher.matches();
    }

}
