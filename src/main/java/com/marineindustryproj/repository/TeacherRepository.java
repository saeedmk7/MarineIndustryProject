package com.marineindustryproj.repository;

import com.marineindustryproj.domain.Teacher;
import com.marineindustryproj.service.dto.customs.TeacherMinDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Teacher entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>, JpaSpecificationExecutor<Teacher> {

    String ALL_TEACHER_CACHE = "allTeacher";

    @Query(value = "select distinct teacher from Teacher teacher left join fetch teacher.documents",
        countQuery = "select count(distinct teacher) from Teacher teacher")
    Page<Teacher> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct teacher from Teacher teacher left join fetch teacher.documents")
    List<Teacher> findAllWithEagerRelationships();

    @Query("select teacher from Teacher teacher left join fetch teacher.documents where teacher.id =:id")
    Optional<Teacher> findOneWithEagerRelationships(@Param("id") Long id);

    @Cacheable(cacheNames = ALL_TEACHER_CACHE)
    @Query("select new com.marineindustryproj.service.dto.customs.TeacherMinDTO(teacher.id, teacher.name, teacher.family, teacher.phoneNumber)  from Teacher teacher")
    List<TeacherMinDTO> findAllFromCache();

}
