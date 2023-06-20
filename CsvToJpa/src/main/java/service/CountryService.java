package service;

import entite.Country;
import entite.Sport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CountryService {
    public void addCountry(EntityManager em)throws IOException {
        EntityTransaction transaction = em.getTransaction();
        List<String> tempo= new ArrayList<>();
        Set<Country> countries = new HashSet<>();
        boolean tempoBool=false;
        Path chemin = Paths.get("liste_des_organisations.csv");
        if (Files.isRegularFile(chemin)) {
            List<String> lignes = Files.readAllLines(chemin);
            for (String ligne : lignes) {
                tempo.clear();
                if (!ligne.contains("Obsolète")) {
                    String[] col = ligne.split(";");
                    for (String nom : col) {
                        nom= nom.replaceAll("\\s*\u00A0\\s*", "");//pour enlever les "nbsp" aleatoire dans le fichier
                        while (nom.startsWith(" ") || nom.endsWith(" ")) {
                            nom = nom.trim();
                        }
                        tempo.add(nom);
                    }
                    if(Objects.equals(tempo.get(col.length-1), "N")){
                        tempoBool=false;  //false = pas obsolète
                    }
                    if(Objects.equals(tempo.get(col.length-1), "0")){
                        tempoBool=true;
                    }

                    if(col.length==5){
                        Country tempoCountry;
                        if(!tempo.get(3).equals("")){
                            tempoCountry = new Country(tempo.get(0), tempo.get(1), tempo.get(2), tempo.get(3), tempoBool);
                        } else{
                            tempoCountry = new Country(tempo.get(0), tempo.get(1), tempo.get(2), tempo.get(0), tempoBool);//le code ISO n'est pas mentionné des fois, par défaut je met qu'il est identique au code CIO
                        }
                        countries.add(tempoCountry);
                    }
                }
            }
        }
        else {
            System.out.println("PAS TROUVE");
        }
        transaction.begin();
        for (Country country : countries) {
            em.persist(country);
        }
        transaction.commit();
    }
}
