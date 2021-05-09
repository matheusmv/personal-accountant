package br.edu.ifce.matheus.pacc.domain.services.utils;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class EmailValidation implements Predicate<String> {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE
    );

    @Override
    public boolean test(String email) {
        return VALID_EMAIL_ADDRESS_REGEX.matcher(email).find();
    }
}
