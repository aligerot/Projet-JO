package service;

import entite.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class EventService {
    public void addEvent(EntityManager em)throws IOException {


        int nbLignePasse=0;


        EntityTransaction transaction = em.getTransaction();
        List<String> tempo= new ArrayList<>();
        Set<Evenement> events = new HashSet<>();
        Path chemin = Paths.get("evenements.csv");
        if (Files.isRegularFile(chemin)) {
            List<String> lignes = Files.readAllLines(chemin);
            for (String ligne : lignes) {
                tempo.clear();
                nbLignePasse++;
                if ((!ligne.contains("ID;")) && nbLignePasse<100 ) {
                    String[] col = ligne.split(";");
                    for (String nom : col) {
                        while (nom.startsWith(" ") || nom.endsWith(" ")) {
                            nom = nom.trim();
                        }
                        tempo.add(nom);
                    }
                    Evenement event = new Evenement();
                    event.setId(Integer.parseInt(tempo.get(0)));


                    TypedQuery<Player> queryPlayer = em.createQuery("select a from Player a where a.name=?1", Player.class);
                    queryPlayer.setParameter(1,tempo.get(1).replaceAll("\"([^\"]*)\"","$1"));
                    event.setPlayer(queryPlayer.getSingleResult());



                    if(tempo.get(2).equals("M")){
                    event.setSex(Sex.MEN);
                    }
                    if(tempo.get(2).equals("F")){
                    event.setSex(Sex.WOMEN);
                    }
                    event.setAge(Integer.parseInt(tempo.get(3)));
                    event.setHeight(Integer.parseInt(tempo.get(4)));
                    event.setWeight(Integer.parseInt(tempo.get(5)));
                    String[] col2 = tempo.get(6).split("/");
                    List<Country>team = new ArrayList<>();
                    for(String nom:col2) {
                        TypedQuery<Country> query1 = em.createQuery("select a from Country a where a.nameEn=?1", Country.class);
                        query1.setParameter(1,nom );

                        team.add(query1.getSingleResult());
                    }
                    event.setTeam(team);


                    event.setNOC(tempo.get(7));

                    TypedQuery<Game> query3 = em.createQuery("select a from Game a where a.year=?1 and a.season=?2" , Game.class);
                    query3.setParameter(1,Integer.parseInt(tempo.get(9)));
                    if(tempo.get(10).equals("Summer")){
                        query3.setParameter(2,Season.SUMMER);
                    }
                    if(tempo.get(10).equals("Winter")){
                        query3.setParameter(2,Season.WINTER);
                    }
                    event.setGame(query3.getSingleResult());

                    event.setCity(tempo.get(11));




                    TypedQuery<Epreuve> query5 = em.createQuery("select a from Epreuve a where a.nameEn=?1", Epreuve.class);
                    String nom=tempo.get(13);
                    //pour pouvoir récupérer l'epreuve en fonction du nom , il faut refaire le meme filtre
                    nom=nom.replaceFirst(tempo.get(12),"");
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
                    nom=nom.trim();
                    query5.setParameter(1,nom);
                    event.setEpreuve(query5.getSingleResult());

                    //ID;Name;Sex;Age;Height;Weight;Team;NOC;Games;Year;Season;City;Sport;Event;Medal

                    String medal = tempo.get(14);
                    if(medal.equals("Gold")){
                        event.setMedal(Medal.GOLD);
                    }
                    if(medal.equals("Bronze")){
                        event.setMedal(Medal.BRONZE);
                    }
                    if(medal.equals("Silver")){
                        event.setMedal(Medal.SILVER);
                    }
                    if(medal.equals("NA")){
                        event.setMedal(Medal.UNKNOWN);
                    }

                    events.add(event);
                }
            }
        }
        else {
            System.out.println("PAS TROUVE");
        }
        transaction.begin();
        for (Evenement event : events) {
            em.persist(event);
        }
        transaction.commit();
    }
}
