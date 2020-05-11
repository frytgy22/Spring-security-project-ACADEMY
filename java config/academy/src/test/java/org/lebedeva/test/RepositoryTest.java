package org.lebedeva.test;

import org.junit.jupiter.api.Test;
import org.lebedeva.model.Group;
import org.lebedeva.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
@ActiveProfiles("dev")
@SpringJUnitConfig(locations = "classpath:spring-jdbc.xml")
public class RepositoryTest {

    @Autowired
    GroupRepository groupRepository;

    @Test
    void getAllTest() {
        assertNotNull(groupRepository);

        List<Group> groups = groupRepository.findAll();

        assertNotNull(groups);
        assertEquals(15, groups.size());
        Group group = groups.get(0);
        assertEquals(1, group.getId());
        assertEquals("Java summer 2019", group.getName());
    }

    @Test
    void getAllSorted() {
        assertNotNull(groupRepository);

        List<Group> groups = groupRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));

        assertNotNull(groups);
        assertEquals(15, groups.size());
        Group group = groups.get(0);
        assertEquals(4, group.getId());
        assertEquals("Internet Marketing 2018", group.getName());
    }

    @Test
    void getByExample() {
        assertNotNull(groupRepository);

        Group group = groupRepository
                .findOne(Example.of(new Group("Internet Marketing 2018")))
                .orElse(null);

        assertNotNull(group);
        assertEquals(4, group.getId());
        assertEquals("Internet Marketing 2018", group.getName());
    }

    @Test
    void getPaging() {
        assertNotNull(groupRepository);

        Page<Group> groupPage = groupRepository.findAll(PageRequest.of(0, 5));

        assertEquals(3, groupPage.getTotalPages());// pages counter
        assertEquals(15, groupPage.getTotalElements());//elements counter

        List<Group> groups = groupPage.getContent();

        assertNotNull(groups);
        assertEquals(5, groups.size());

        Group group = groups.get(0);
        assertEquals(1, group.getId());
        assertEquals("Java summer 2019", group.getName());
        assertEquals("Java summer 2011", groups.get(4).getName());
    }
}
