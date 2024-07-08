package com.course.springdata.persistence.repository;

import com.course.springdata.persistence.entity.Pizza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface PizzaPagSortRepository extends ListPagingAndSortingRepository<Pizza, Integer> {
    Page<Pizza> findByAvailableTrue(Pageable pageable);
}
