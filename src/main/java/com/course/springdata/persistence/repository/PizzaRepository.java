package com.course.springdata.persistence.repository;

import com.course.springdata.persistence.entity.Pizza;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaRepository extends ListCrudRepository<Pizza, Integer> {
    List<Pizza> findAllByAvailableTrueOrderByPriceAsc();
    Pizza findAllByAvailableTrueAndNameIgnoreCase(String name);
    List<Pizza> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);
    List<Pizza> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);
}
