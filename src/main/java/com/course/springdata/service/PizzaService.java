package com.course.springdata.service;

import com.course.springdata.persistence.entity.Pizza;
import com.course.springdata.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository){
        this.pizzaRepository = pizzaRepository;
    }

    public List<Pizza> getAll(){
        return pizzaRepository.findAll();
    }

    public Pizza get(int idPizza){
        return pizzaRepository.findById(idPizza).orElse(null);
    }

    public List<Pizza> getAvailable(){
        return pizzaRepository.findAllByAvailableTrueOrderByPriceAsc();
    }

    public Pizza getByName(String name){
        return pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name);
    }

    public List<Pizza> getWith(String ingredient){
        return pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public List<Pizza> getWithout(String ingredient){
        return pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
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
