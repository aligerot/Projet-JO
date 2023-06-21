package service;

import entite.Epreuve;
import entite.Player;
import entite.Sex;
import entite.Sport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class EpreuveService {
    public void addEpreuve(EntityManager em)throws IOException {
        int compteurTour = 0;
        EntityTransaction transaction = em.getTransaction();
        Map<Integer,List<String>> map = new HashMap<>();
        List<String> tempo= new ArrayList<>();
        List<String> tempoEvent= new ArrayList<>();
        List<String> tempoSport= new ArrayList<>();
        Set<Epreuve> epreuves = new HashSet<>();
        Set<List<String>> set= new HashSet<>();

        boolean boo = false;
        Sex tempoSex;
        Sport futureSport= new Sport();
        Path chemin = Paths.get("liste_des_epreuves.csv");
        Path cheminEvent = Paths.get("evenements.csv");
        if (Files.isRegularFile(cheminEvent)) {
            List<String> lignesEvent = Files.readAllLines(cheminEvent);
            for (String ligne : lignesEvent) {
                tempo.clear();
                if (!ligne.contains("Event")) {
                    String[] col = ligne.split(";");
                    tempo.addAll(Arrays.asList(col));
                    tempoEvent.add(tempo.get(13));
                    tempoSport.add(tempo.get(12));

            }
        }
        }
        for (int i = 1; i < tempoEvent.size(); i++) {
            List<String> listForSet = new ArrayList<>();
            listForSet.add(tempoEvent.get(i));
            listForSet.add(tempoSport.get(i));
            set.add(listForSet);
        }
        for (List<String> listed:set){
            System.out.println(listed);
        }
        List<List<String>> listOfList = new ArrayList<>(set);

            //ID;Name;Sex;Age;Height;Weight;Team;NOC;Games;Year;Season;City;Sport;Event;Medal



            if (Files.isRegularFile(chemin)) {
            List<String> lignes = Files.readAllLines(chemin);
            for (String ligne : lignes) {
                tempo.clear();
                compteurTour++;
                if(compteurTour%70==1){
                    System.out.println(compteurTour);
                }
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


                        nom=nom.replaceAll("\\(W\\)","");
                        nom=nom.replaceAll("\\(M\\)","");
                        nom=nom.replaceAll("\\(H\\)","");
                        nom=nom.replaceAll("\\(F\\)","");
                        nom=nom.replaceAll("\\(F\\+H\\)","");
                        nom=nom.replaceAll("\\(H\\+F\\)","");
                        nom=nom.replaceAll("\\(W\\+M\\)","");
                        nom=nom.replaceAll("\\(M\\+W\\)","");
                        nom=nom.replaceAll("\\(men\\)","");
                        for (int i = 1; i < listOfList.size(); i++) {
                            if(tempoEvent.get(i).trim().equals((listOfList.get(i).get(1)+" "+nom).trim())){
                                TypedQuery<Sport> query = em.createQuery("select a from Sport a where a.nameEn=?1", Sport.class);
                                query.setParameter(1, listOfList.get(i).get(1));
                                futureSport=query.getSingleResult();
                            }
                        }
                        nom=nom.replaceAll("Women's|women's","");
                        nom=nom.replaceAll("Men's|men's","");
                        nom=nom.replaceAll("women|Women","");
                        nom=nom.replaceAll("men|Men","");
                        nom=nom.replaceAll("mixte|Mixte|mixtes|Mixtes|mixed|Mixed","");
                        nom=nom.trim();

                        tempo.add(nom);
                    }
                    Epreuve epreuve = new Epreuve();


                    if(col.length==2){
                        epreuve.setNameEn(tempo.get(0));
                        epreuve.setNameFr(tempo.get(1));
                        epreuve.setSex(tempoSex);
                    }
                    if(col.length==1){
                        epreuve.setNameEn(tempo.get(0));
                        epreuve.setNameFr(tempo.get(0));
                        epreuve.setSex(tempoSex);
                    }

                    epreuve.setSport(futureSport);

                    epreuves.add(epreuve);
                    }
                }
            }
        else {
            System.out.println("PAS TROUVE");
        }

        System.out.println(listOfList.size());
        transaction.begin();
        for (Epreuve epreuve : epreuves) {
            em.persist(epreuve);
        }
        transaction.commit();



    }
}
