package org.lebedeva.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class TeacherDto {
    private Integer id;

    @NonNull
    @NotBlank
    @Length(max = 30)
    private String name;

    @NonNull
    @NotBlank
    @Length(max = 30)
    private String surname;

    @NonNull
    @NotNull
    @PastOrPresent
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startWork;

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

    private List<Integer> groupsId = new ArrayList<>();
    private List<String> groupsName = new ArrayList<>();
}
