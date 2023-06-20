package entite;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Epreuve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    String nameFr;

    @Column
    String nameEn;

    @Column
    Sex sex;

    public Epreuve() {
    }

    public Epreuve(String name) {
        this.nameFr = name;
        this.nameEn =name;
        this.sex=Sex.MIXED;
    }

    public Epreuve(String nameFr, String nameEn) {
        this.nameFr = nameFr;
        this.nameEn = nameEn;
        this.sex=Sex.MIXED;
    }

    public Epreuve(String nameFr, String nameEn, Sex sex) {
        this.nameFr = nameFr;
        this.nameEn = nameEn;
        this.sex = sex;
    }
    public Epreuve(String name, Sex sex) {
        this.nameFr = name;
        this.nameEn = name;
        this.sex = sex;
    }


}