package com.course.springdata.persistence.repository;

import com.course.springdata.persistence.entity.Pizza;
import com.course.springdata.service.dto.UpdatePizzaPriceDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * The @Modifying annotation means that the method can execute queries different to SELECT's.
 * Without this annotation the application throws an exception.
 */
@Repository
public interface PizzaRepository extends ListCrudRepository<Pizza, Integer> {
    List<Pizza> findAllByAvailableTrueOrderByPriceAsc();
    Optional<Pizza> findFirstByAvailableTrueAndNameIgnoreCase(String name);
    List<Pizza> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);
    List<Pizza> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);
    List<Pizza> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(double price);
    int countByVeganTrue();

    @Query(value =
            "UPDATE pizza " +
            "SET price = :#{#newPizzaPrice.newPrice} " +
            "WHERE id_pizza = :#{#newPizzaPrice.pizzaId} ", nativeQuery = true)
    @Modifying
    void updatePizza(@Param("newPizzaPrice")UpdatePizzaPriceDto newPizzaPrice);
}
