package br.edu.ifce.matheus.pacc.domain.valueobjects;

import br.edu.ifce.matheus.pacc.domain.exceptions.InvalidEmailException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Email {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE
    );

    private String value;

    public Email(String value) {
        this.value = value != null ? value : "";

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(value);

        if (!matcher.find()) {
            throw new InvalidEmailException();
        }
    }
}
