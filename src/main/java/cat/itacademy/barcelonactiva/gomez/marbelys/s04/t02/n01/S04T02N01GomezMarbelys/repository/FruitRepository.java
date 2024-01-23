package cat.itacademy.barcelonactiva.gomez.marbelys.s04.t02.n01.S04T02N01GomezMarbelys.repository;

import cat.itacademy.barcelonactiva.gomez.marbelys.s04.t02.n01.S04T02N01GomezMarbelys.domain.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FruitRepository extends JpaRepository<Fruit, Integer> {


    Optional<Fruit> findByNameIgnoreCase(String name);
}

