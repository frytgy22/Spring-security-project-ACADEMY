package org.lebedeva.init;

import org.lebedeva.model.Group;
import org.lebedeva.model.Student;
import org.lebedeva.model.Teacher;
import org.lebedeva.repository.GroupRepository;
import org.lebedeva.repository.StudentRepository;
import org.lebedeva.repository.TeacherRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;

@Component
@Profile("dev")
public class InitDatabase {

    private static boolean init;

    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public InitDatabase(GroupRepository groupRepository,
                        StudentRepository studentRepository,
                        TeacherRepository teacherRepository) {
        this.groupRepository = groupRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }


    @PostConstruct
    public void init() {
        if (init) return;

        List<Group> groups = List.of(
                new Group("Java summer 2019"),
                new Group("Java summer 2018"),
                new Group("Java summer 2017"),
                new Group("Internet Marketing 2018"),
                new Group("Java summer 2011"),
                new Group("Java summer 2016"),
                new Group("Java summer 2020"),
                new Group("Web autumn 2011"),
                new Group("Web autumn 2012"),
                new Group("Web autumn 2013"),
                new Group("Web autumn 2014"),
                new Group("Web autumn 2015"),
                new Group("Web autumn 2016"),
                new Group("Web autumn 2017"),
                new Group("Web autumn 2018")
        );

        groups.forEach(groupRepository::save);
//password = password
        studentRepository.save(new Student("Вася", "Пупкин",
                LocalDate.of(2001, 1, 1), "123@gmail.ru",
                "$2a$10$GVdyIMr61bhkul6eW1ZzPu2zRhFnpYqpp.oq5OFB4JH.sW.49X/Zi",
                "ROLE_STUDENT", groups.get(0)));

        studentRepository.save(new Student("Маша", "Ефросинина",
                LocalDate.of(1986, 2, 12), "qwe@rambler.ru",
                "$2a$10$GVdyIMr61bhkul6eW1ZzPu2zRhFnpYqpp.oq5OFB4JH.sW.49X/Zi",
                "ROLE_STUDENT", groups.get(4)));

        teacherRepository.save(new Teacher(null, "Василий", "Петров",
                LocalDate.of(1956, 2, 12), null, "zxc@rambler.ru",
                "$2a$10$GVdyIMr61bhkul6eW1ZzPu2zRhFnpYqpp.oq5OFB4JH.sW.49X/Zi",
                "ROLE_ADMIN",
                List.of(groups.get(0), groups.get(1))));

        init = true;
    }
}
