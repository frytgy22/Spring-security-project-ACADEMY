package org.lebedeva.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class GroupDto {
    private Integer id;

    @NonNull
    @NotBlank
    @Length(max = 30)
    private String name;

    private List<StudentDto> students = new ArrayList<>();
    private List<TeacherDto> teachers = new ArrayList<>();
}

