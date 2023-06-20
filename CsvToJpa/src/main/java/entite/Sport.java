package entite;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Sport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    String nameFr;

    @Column
    String nameEn;




    public Sport() {
    }

    public Sport(String name) {
        this.nameFr = name;
        this.nameEn =name;
    }

    public Sport(String nameFr, String nameEn) {
        this.nameFr = nameFr;
        this.nameEn = nameEn;
    }
}