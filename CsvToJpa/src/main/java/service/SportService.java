package service;

import entite.Sport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import repository.SportRepository;

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
                    tempo.addAll(Arrays.asList(col));
                    if(col.length==2){//certains sports s'appellent de la meme facon en anglais et en fr
                    Sport tempoSport = new Sport(tempo.get(1).trim(),tempo.get(0).trim());
                        sports.add(tempoSport);
                    }
                    if(col.length==1){
                        Sport tempoSport = new Sport(tempo.get(0).trim());
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
    SportRepository sportRepository;
    public List<Sport> listAllSport() {
        List<Sport> sportList = sportRepository.findAll();
        return new ArrayList<>(sportList);
    }
}
