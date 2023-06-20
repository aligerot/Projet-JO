package service;

import entite.Sport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class SportService {
    public void addSport(EntityManager em)throws IOException {
        EntityTransaction transaction = em.getTransaction();
        List<String> tempo= new ArrayList<>();
        Set<Sport> sports = new HashSet<>();
        Path chemin = Paths.get("liste des sports.csv");
        if (Files.isRegularFile(chemin)) {
            List<String> lignes = Files.readAllLines(chemin);
            for (String ligne : lignes) {
                tempo.clear();
                if (!ligne.contains("Libelle")) {
                    String[] col = ligne.split(";");

                    for (String nom : col) {
                        while (nom.startsWith(" ") || nom.endsWith(" ")) {
                            nom = nom.trim();
                        }
                        tempo.add(nom);
                    }
                    if(col.length==2){//certains sports s'appellent de la meme facon en anglais et en fr
                    Sport tempoSport = new Sport(tempo.get(1),tempo.get(0));
                        sports.add(tempoSport);
                    }
                    if(col.length==1){
                        Sport tempoSport = new Sport(tempo.get(0));
                        sports.add(tempoSport);
                    }

                }
            }
        }
        else {
            System.out.println("PAS TROUVE");
        }
        transaction.begin();
        for (Sport sport : sports) {
            em.persist(sport);
        }
        transaction.commit();
    }
}
