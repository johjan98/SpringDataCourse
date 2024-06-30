package com.course.springdata.persistence.repository;

import com.course.springdata.persistence.entity.Pizza;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends ListCrudRepository<Pizza, Integer> {
}
