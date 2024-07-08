package com.course.springdata.service;

import com.course.springdata.persistence.entity.Pizza;
import com.course.springdata.persistence.repository.PizzaPagSortRepository;
import com.course.springdata.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    private final PizzaPagSortRepository pizzaPagSortRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository){
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

    public Page<Pizza> getAll(int page, int elements){
        Pageable pageRequest = PageRequest.of(page, elements);
        return pizzaPagSortRepository.findAll(pageRequest);
    }

    public Pizza get(int idPizza){
        return pizzaRepository.findById(idPizza).orElse(null);
    }

    public List<Pizza> getAvailable(){
        return pizzaRepository.findAllByAvailableTrueOrderByPriceAsc();
    }

    public Pizza getByName(String name){
        return pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name)
                .orElseThrow(() -> new RuntimeException("The pizza does not exist"));
    }

    public List<Pizza> getWith(String ingredient){
        return pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public List<Pizza> getWithout(String ingredient){
        return pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    public List<Pizza> getCheapest(double price){
        return pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }

    public int countVeganPizzas(){
        return pizzaRepository.countByVeganTrue();
    }

    public Pizza save(Pizza pizza){
        return pizzaRepository.save(pizza);
    }

    public boolean exists(int idPizza){
        return pizzaRepository.existsById(idPizza);
    }

    public void delete(int idPizza){
        pizzaRepository.deleteById(idPizza);
    }
}
