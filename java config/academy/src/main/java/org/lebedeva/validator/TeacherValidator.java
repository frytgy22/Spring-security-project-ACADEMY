package org.lebedeva.validator;

import org.lebedeva.dto.TeacherDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TeacherValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == TeacherDto.class;
    }

    @Override
    public void validate(Object o, Errors errors) {
        TeacherDto dto = (TeacherDto) o;

        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "password");
        }
    }
}
