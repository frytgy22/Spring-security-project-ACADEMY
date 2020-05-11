package org.lebedeva.repository;

import org.lebedeva.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface GroupRepository extends JpaRepository<Group, Integer> {

    @Query(value = "select  groups from Group groups left join fetch groups.teachersList " +
            "where upper(groups.name) like upper(:name)",
            countQuery = "select count (distinct groups) from Group groups where upper(groups.name) like upper(:name)")
    Page<Group> findAllByName(@Param("name") String name, Pageable pageable);
    // Page<Group> findAllByNameContainsIgnoreCase(String name, Pageable pageable);

    @Query(value = "select distinct groups from Group groups left join fetch groups.teachersList",
            countQuery = "select count (distinct groups) from Group groups")
    Page<Group> findAllWithEagerRelationships(Pageable pageable);

    @Query("select groups from Group groups left join fetch groups.teachersList where groups.id =:id")
    Optional<Group> findOneWithEagerRelationships(@Param("id") Integer id);
}
