package cat.itacademy.barcelonactiva.gomez.marbelys.s04.t02.n01.S04T02N01GomezMarbelys.model.services;


import cat.itacademy.barcelonactiva.gomez.marbelys.s04.t02.n01.S04T02N01GomezMarbelys.domain.Fruit;
import cat.itacademy.barcelonactiva.gomez.marbelys.s04.t02.n01.S04T02N01GomezMarbelys.repository.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FruitService {

    @Autowired
    private FruitRepository fruitRepository;

    public ResponseEntity<List<Fruit>> checkGetFruits() {
        try {
            List<Fruit> fruits = fruitRepository.findAll();
            if (fruits == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            if (fruits.isEmpty()) return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            return ResponseEntity.ok(fruits);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Fruit> checkAddFruit(Fruit fruit) {
        try {
            Optional<Fruit> fruitOptional = fruitRepository.findByNameIgnoreCase(fruit.getName());
            if (fruitOptional.isPresent()) return new ResponseEntity<>(fruit, HttpStatus.FOUND);
            if (fruit.getName().isEmpty() || fruit.getQuantityKilos() < 0) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            Fruit newFruit = new Fruit(fruit.getName(), fruit.getQuantityKilos());
            fruitRepository.save(newFruit);
            return new ResponseEntity<>(newFruit, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Fruit> checkIdExist(int id) {
        if (fruitRepository.existsById(id)) {
            return new ResponseEntity<>(fruitRepository.findById(id).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<Fruit> updateFruit(int id, Fruit fruit) {
        Fruit fruitToUpdate = fruitRepository.findById(id).get();
        fruitToUpdate.setName(fruit.getName());
        fruitToUpdate.setQuantityKilos(fruit.getQuantityKilos());
        fruitRepository.save(fruitToUpdate);
        return new ResponseEntity<>(fruitToUpdate,HttpStatus.CREATED);
    }
}

