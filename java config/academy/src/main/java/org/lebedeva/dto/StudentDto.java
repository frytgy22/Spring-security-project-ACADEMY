package org.lebedeva.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class StudentDto {
    private Integer id;

    @NonNull
    @NotBlank
    @Length(max = 30)
    private String name;

    @NonNull
    @NotBlank
    @Length(max = 30)
    private String surname;

    @Past
    @NonNull
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

    private String photo;

    @Email
    @NonNull
    private String email;

    @NonNull
    @NotBlank
    private String password;

    @NonNull
    @NotBlank
    private String confirmPassword;

    @NonNull
    @NotBlank
    private String role;

    @NotNull
    @NonNull
    private Integer groupId;

    private String groupName;
}
