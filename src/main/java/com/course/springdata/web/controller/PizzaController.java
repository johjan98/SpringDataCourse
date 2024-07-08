package com.course.springdata.web.controller;

import com.course.springdata.persistence.entity.Pizza;
import com.course.springdata.service.PizzaService;
import com.course.springdata.service.dto.UpdatePizzaPriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<Page<Pizza>> getAll(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "8") int elements){
        return ResponseEntity.ok(pizzaService.getAll(page, elements));
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity<Pizza> get(@PathVariable(name = "idPizza") int idPizza){
        return ResponseEntity.ok(pizzaService.get(idPizza));
    }

    @GetMapping("/available")
    public ResponseEntity<Page<Pizza>> getAvailable(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "8") int elements,
                                                    @RequestParam(defaultValue = "price") String sortBy,
                                                    @RequestParam(defaultValue = "ASC") String sortDirection){
        return ResponseEntity.ok(pizzaService.getAvailable(page, elements, sortBy, sortDirection));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Pizza> getByName(@PathVariable("name") String name){
        return ResponseEntity.ok(pizzaService.getByName(name));
    }

    @GetMapping("/with/{ingredient}")
    public ResponseEntity<List<Pizza>> getWith(@PathVariable("ingredient") String ingredient){
        return ResponseEntity.ok(pizzaService.getWith(ingredient));
    }

    @GetMapping("/without/{ingredient}")
    public ResponseEntity<List<Pizza>> getWithout(@PathVariable("ingredient") String ingredient){
        return ResponseEntity.ok(pizzaService.getWithout(ingredient));
    }

    @GetMapping("/vegan_pizzas")
    public ResponseEntity<Integer> getWithout(){
        return ResponseEntity.ok(pizzaService.countVeganPizzas());
    }

    @GetMapping("/cheapest/{price}")
    public ResponseEntity<List<Pizza>> getCheapestPizzas(@PathVariable("price") double price){
        return ResponseEntity.ok(pizzaService.getCheapest(price));
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

    @PutMapping("/price")
    public ResponseEntity<Void> updatePrice(@RequestBody UpdatePizzaPriceDto dto){
        if(pizzaService.exists(dto.getPizzaId())){
            pizzaService.updatePizza(dto);
            return ResponseEntity.ok().build();
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
