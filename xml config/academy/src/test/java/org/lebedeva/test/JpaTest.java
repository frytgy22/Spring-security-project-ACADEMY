//package org.lebedeva.test;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.lebedeva.model.Group;
//import org.lebedeva.model.Student;
//import org.lebedeva.model.Teacher;
//import org.lebedeva.service.GroupService;
//import org.lebedeva.service.StudentService;
//import org.lebedeva.service.TeacherService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration("classpath:spring-jdbc.xml")
//public class JpaTest {
//
//    @Autowired
//    GroupService groupService;
//    @Autowired
//    StudentService studentService;
//    @Autowired
//    TeacherService teacherService;
//
//    @Test
//    @Transactional
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
//    void insert() {
//        assertNotNull(groupService);
//        assertNotNull(studentService);
//        assertNotNull(teacherService);
//
//        Group group = new Group("Java");
//        Student student = new Student("Ivan", "Livov",
//                LocalDate.of(1999, 9, 9), group);
//        group.setStudents(List.of(student));
//        Teacher teacher = new Teacher("Petr", "Petrov", LocalDate.of(2020, 2, 2));
//        teacher.setGroups(List.of(group));
//        group.setTeachersList(List.of(teacher));
//
//        groupService.saveGroup(group);
//        studentService.saveStudent(student);
//        teacherService.saveTeacher(teacher);
//
//        group = groupService.getGroup(group.getId());
//        assertNotNull(group);
//        assertNotNull(group.getStudents());
//        assertNotNull(group.getTeachersList());
//        assertEquals("Java", group.getName());
//        assertEquals(1, group.getStudents().size());
//        assertEquals(1, group.getTeachersList().size());
//        assertEquals("Ivan", group.getStudents().get(0).getName());
//        assertEquals("Livov", group.getStudents().get(0).getSurname());
//        assertEquals("Petr", group.getTeachersList().get(0).getName());
//        assertEquals("Petrov", group.getTeachersList().get(0).getSurname());
//        assertEquals(LocalDate.of(1999, 9, 9), group.getStudents().get(0).getBirthDate());
//        assertEquals(LocalDate.of(2020, 2, 2), group.getTeachersList().get(0).getStartWork());
//
//        student = studentService.getStudent(student.getId());
//        assertEquals("Ivan", student.getName());
//        assertEquals("Livov", student.getSurname());
//        assertEquals(LocalDate.of(1999, 9, 9), student.getBirthDate());
//        assertNotNull(student.getGroup());
//        assertEquals("Java", student.getGroup().getName());
//
//        teacher = teacherService.getTeacher(teacher.getId());
//        assertEquals("Petr", teacher.getName());
//        assertEquals("Petrov", teacher.getSurname());
//        assertEquals(LocalDate.of(2020, 2, 2), teacher.getStartWork());
//        assertNotNull(teacher.getGroups());
//        assertEquals("Java", teacher.getGroups().get(0).getName());
//    }
//}
