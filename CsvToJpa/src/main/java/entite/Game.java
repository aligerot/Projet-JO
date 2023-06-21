package entite;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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

  //  @ManyToMany
   // @JoinTable(
     //       joinColumns = @JoinColumn(referencedColumnName = "id"),
       //     inverseJoinColumns = @JoinColumn(referencedColumnName = "id")
    //)
    //List<Country> country;   plusieurs pays peuvent organiser les JO ensemble.
    //normalement on devrait mettre les pays organisateurs, mais ils ne sont inscrits nulle part dans les fichiers CSV.

    public Game(int year, Season season) {
        this.year = year;
        this.season = season;
    }

    public Game() {

    }
}
