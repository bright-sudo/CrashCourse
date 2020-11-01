package com.student.demo.service.component;

import com.student.demo.exeptions.ApiEmailExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Component
public class EmailValidator implements Predicate<String> {

    private final Predicate<String> IS_EMAIL_VALID =
            Pattern.compile(
                    "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
                    Pattern.CASE_INSENSITIVE)
                    .asPredicate();
    @Override
    public boolean test(String email) {
        return IS_EMAIL_VALID.test(email);
    }

}
