package org.lebedeva.validator;

import org.lebedeva.dto.StudentDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class StudentValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == StudentDto.class;
    }

    @Override
    public void validate(Object o, Errors errors) {
        StudentDto dto = (StudentDto) o;

        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "password");
        }
    }
}
