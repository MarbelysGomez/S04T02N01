package cat.itacademy.barcelonactiva.gomez.marbelys.s04.t02.n01.S04T02N01GomezMarbelys.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fruits")
@Data
@NoArgsConstructor
public class Fruit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name", nullable = false, length = 150)
    private String name;
    @Column(name="kilos", length = 10)
    private int quantityKilos;

    public Fruit(String name, int quantity) {
        this.name = name;
        this.quantityKilos = quantity;
    }
    public Fruit(String name) {
        this.name = name;
        this.quantityKilos = 0;
    }
}
