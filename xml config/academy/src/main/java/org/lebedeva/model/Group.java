package org.lebedeva.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "groups")
@RequiredArgsConstructor
@ToString(exclude = {"students", "teachersList"})
@EqualsAndHashCode(exclude = {"students", "teachersList"})
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @NotBlank
    @Length(max = 30)
    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "group",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER
    )
    private List<Student> students = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "teachers_groups",
            joinColumns = @JoinColumn(name = "groups_id"),
            inverseJoinColumns = @JoinColumn(name = "teachers_id"))
    private List<Teacher> teachersList = new ArrayList<>();
}
