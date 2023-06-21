package entite;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Evenement {

    @Id
    int id;

    @Column
    Sex sex;

    @Column
    int weight;

    @Column
    int height;

    @Column
    int age;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "id")
    )
    List<Country> team;

    String NOC;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    Player player;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    Game game;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    Epreuve epreuve;

    @Column
    Medal medal;

    @Column
    String city;




//ID;Name;Sex;Age;Height;Weight;Team;NOC;Games;Year;Season;City;Sport;Event;Medal


    public Evenement() {
    }
}