package service;

import entite.Epreuve;
import entite.Sex;
import entite.Sport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class EpreuveService {
    public void addEpreuve(EntityManager em)throws IOException {
        EntityTransaction transaction = em.getTransaction();
        List<String> tempo= new ArrayList<>();
        Set<Epreuve> epreuves = new HashSet<>();
        Sex tempoSex= Sex.MIXED;
        Path chemin = Paths.get("liste_des_epreuves.csv");
        if (Files.isRegularFile(chemin)) {
            List<String> lignes = Files.readAllLines(chemin);
            for (String ligne : lignes) {
                tempo.clear();
                tempoSex=Sex.MIXED;
                if (!ligne.contains("Event")) {
                    String[] col = ligne.split(";");
                    for (String nom : col) {
                        if(nom.contains("(H)")){
                            tempoSex=Sex.MEN;
                        }
                        if(nom.contains("F")){
                            tempoSex=Sex.WOMEN;
                        }
                        nom=nom.replaceAll("Women's|women's","");
                        nom=nom.replaceAll("Men's|men's","");
                        nom=nom.replaceAll("women|Women","");
                        nom=nom.replaceAll("men|Men","");
                        nom=nom.replaceAll("mixte|Mixte|mixtes|Mixtes|mixed|Mixed","");
                        nom=nom.replaceAll("\\(W\\)","");
                        nom=nom.replaceAll("\\(M\\)","");
                        nom=nom.replaceAll("\\(H\\)","");
                        nom=nom.replaceAll("\\(F\\)","");
                        nom=nom.replaceAll("\\(F\\+H\\)","");
                        nom=nom.replaceAll("\\(H\\+F\\)","");
                        nom=nom.replaceAll("\\(W\\+M\\)","");
                        nom=nom.replaceAll("\\(M\\+W\\)","");


                        while (nom.startsWith(" ") || nom.endsWith(" ")) {
                            nom = nom.trim();
                        }
                        tempo.add(nom);
                    }
                    if(col.length==2){
                        Epreuve tempoEpreuve = new Epreuve(tempo.get(1),tempo.get(0),tempoSex);//je prefere avoir toutes mes bases avec l'anglais' en premier
                        epreuves.add(tempoEpreuve);
                    }
                    if(col.length==1){
                        Epreuve tempoEpreuve = new Epreuve(tempo.get(0),tempoSex);
                        epreuves.add(tempoEpreuve);
                    }

                }
            }
        }
        else {
            System.out.println("PAS TROUVE");
        }
        transaction.begin();
        for (Epreuve epreuve : epreuves) {
            em.persist(epreuve);
        }
        transaction.commit();
    }
}
