package com.rest.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "rest-events", path = "rest-events")
public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

    List<Event> findByTitle(String title);
    Page<Event> findAll(Pageable pageable);

    @Override
    @RestResource(exported=false)
    void deleteById(Long aLong);

}
