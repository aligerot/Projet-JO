package entite;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    int year;

    @Column
    Season season ;

    public Game(int year, Season season) {
        this.year = year;
        this.season = season;
    }

    public Game() {

    }
}
