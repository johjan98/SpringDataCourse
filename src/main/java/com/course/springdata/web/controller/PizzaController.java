package com.course.springdata.web.controller;

import com.course.springdata.persistence.entity.Pizza;
import com.course.springdata.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService){
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<List<Pizza>> getAll(){
        return ResponseEntity.ok(pizzaService.getAll());
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity<Pizza> get(@PathVariable(name = "idPizza") int idPizza){
        return ResponseEntity.ok(pizzaService.get(idPizza));
    }

    @PostMapping
    public ResponseEntity<Pizza> add(@RequestBody Pizza pizza){
        if(pizza.getIdPizza() == null || !pizzaService.exists(pizza.getIdPizza())){
            return ResponseEntity.ok(pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<Pizza> update(@RequestBody Pizza pizza){
        if(pizza.getIdPizza() != null || pizzaService.exists(pizza.getIdPizza())){
            return ResponseEntity.ok(pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idPizza}")
    public ResponseEntity<Void> delete(@PathVariable("idPizza") int idPizza){
        if(pizzaService.exists(idPizza)){
            pizzaService.delete(idPizza);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
