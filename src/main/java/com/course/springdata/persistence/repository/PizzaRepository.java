package com.course.springdata.persistence.repository;

import com.course.springdata.persistence.entity.Pizza;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PizzaRepository extends ListCrudRepository<Pizza, Integer> {
    List<Pizza> findAllByAvailableTrueOrderByPriceAsc();
    Optional<Pizza> findFirstByAvailableTrueAndNameIgnoreCase(String name);
    List<Pizza> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);
    List<Pizza> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);
    List<Pizza> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(double price);
    int countByVeganTrue();
}
