package com.marineindustryproj.repository;

import com.marineindustryproj.domain.Person;
import com.marineindustryproj.service.dto.customs.PersonMinDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Person entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PersonRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {

    String ALL_PERSON_CACHE = "allPerson";

    @Query(value = "select distinct person from Person person left join fetch person.documents left join fetch person.scientificWorkGroups",
        countQuery = "select count(distinct person) from Person person")
    Page<Person> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct person from Person person left join fetch person.documents left join fetch person.scientificWorkGroups")
    List<Person> findAllWithEagerRelationships();



    @Query("select person from Person person left join fetch person.documents left join fetch person.scientificWorkGroups where person.id =:id")
    Optional<Person> findOneWithEagerRelationships(@Param("id") Long id);


    @Cacheable(cacheNames = ALL_PERSON_CACHE)
    @Query(value = "select new com.marineindustryproj.service.dto.customs.PersonMinDTO(person.id, person.name, person.family, person.nationalId, person.personelCode, person.job) from Person person where person.archived = false")
    List<PersonMinDTO> findAllFromCache();

    @Query(value = "select person from Person person where person.organizationChart = null and person.archived = false")
    List<Person> findAllByOrganizationChartNull();

    @Query(value = "select person from Person person where person.organizationChart <> null and person.archived = false")
    List<Person> findAllByOrganizationChartNotNull();

}
