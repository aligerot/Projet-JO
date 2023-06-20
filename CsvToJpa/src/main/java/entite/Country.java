package entite;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Country {//CIO code;Nom du pays FR;Nom du pays EN;Code ISO Alpha 3;Obsolète
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    String CodeCIO;

    @Column
    String nameFr;

    @Column
    String nameEn;

    @Column
    String CodeISO;

    @Column
    boolean obsolete;


    public Country(String codeCIO, String nameFr, String nameEn, String codeISO, boolean obsolete) {
        CodeCIO = codeCIO;
        this.nameFr = nameFr;
        this.nameEn = nameEn;
        CodeISO = codeISO;
        this.obsolete = obsolete;
    }



    public Country() {

    }
}