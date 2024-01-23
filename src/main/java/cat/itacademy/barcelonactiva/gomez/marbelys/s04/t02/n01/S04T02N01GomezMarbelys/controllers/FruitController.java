package cat.itacademy.barcelonactiva.gomez.marbelys.s04.t02.n01.S04T02N01GomezMarbelys.controllers;

import cat.itacademy.barcelonactiva.gomez.marbelys.s04.t02.n01.S04T02N01GomezMarbelys.domain.Fruit;
import cat.itacademy.barcelonactiva.gomez.marbelys.s04.t02.n01.S04T02N01GomezMarbelys.model.services.FruitService;
import cat.itacademy.barcelonactiva.gomez.marbelys.s04.t02.n01.S04T02N01GomezMarbelys.repository.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/v1/fruits")
public class FruitController {

    @Autowired
    private FruitService fruitService;
    @Autowired
    private FruitRepository fruitRepository;

    @PostMapping("/add")
    public ResponseEntity<Fruit> addFruit(@RequestBody Fruit fruit) {
        return fruitService.checkAddFruit(fruit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fruit> updateOneFruit(@PathVariable("id") int id, @RequestBody Fruit fruit) {
        ResponseEntity<Fruit> fruitResponse = fruitService.checkIdExist(id);
        if (fruitResponse.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            return fruitResponse;
        } else {
            ResponseEntity<Fruit> updatedFruit = fruitService.updateFruit(id, fruit);
            return updatedFruit;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Fruit> deleteOneFruit(@PathVariable("id") int id) {
        ResponseEntity<Fruit> fruitResponse = fruitService.checkIdExist(id);

        if (fruitResponse.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            return fruitResponse;
        } else {
            fruitRepository.delete(fruitResponse.getBody());
            return fruitResponse;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fruit> getOneFruit(@PathVariable("id") int id) {
        return fruitService.checkIdExist(id);
    }

    @GetMapping()
    public ResponseEntity<List<Fruit>> getAllFruits() {
        return fruitService.checkGetFruits();
    }
}
