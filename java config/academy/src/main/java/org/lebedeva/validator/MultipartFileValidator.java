package org.lebedeva.validator;

import org.lebedeva.object.FileUploadModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.List;

@Component
public class MultipartFileValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == FileUploadModel.class;
    }

    @Override
    public void validate(Object o, Errors errors) {
        FileUploadModel fileUploadModel = (FileUploadModel) o;

        if (!fileUploadModel.getFile().isEmpty()) {

            if (fileUploadModel.getFile().getSize() > 500000) {
                errors.rejectValue("photo", "file.size");
            }

            List<String> validContentTypes = Arrays.asList("image/png", "image/jpg", "image/jpeg");

            if (validContentTypes.stream()
                    .noneMatch(s -> s.equals(fileUploadModel.getFile().getContentType()))) {
                errors.rejectValue("photo", "file.type");
            }
        }
    }
}
