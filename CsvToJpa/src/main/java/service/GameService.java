package service;

import entite.Country;
import entite.Game;
import entite.Season;
import entite.Sport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class GameService {
    public void addGame(EntityManager em)throws IOException {
        EntityTransaction transaction = em.getTransaction();
        List<String> tempo= new ArrayList<>();
        Set<Game> games = new HashSet<>();
        Path chemin = Paths.get("evenements.csv");
        if (Files.isRegularFile(chemin)) {
            List<String> lignes = Files.readAllLines(chemin);
            for (String ligne : lignes) {
                tempo.clear();
                if (!ligne.contains("ID;Name")) {
                    String[] col = ligne.split(";");
                    Game game= new Game();
                    tempo.addAll(Arrays.asList(col));
                    if(col.length==15){//certains sports s'appellent de la meme facon en anglais et en fr
                        game.setYear(Integer.parseInt(tempo.get(9)));
                        if(tempo.get(10).contains("Summer")||tempo.get(10).contains("summer")){
                            game.setSeason(Season.SUMMER);
                        }
                        if(tempo.get(10).contains("Winter")||tempo.get(10).contains("winter")){
                            game.setSeason(Season.WINTER);
                        }

                        games.add(game);

                }
            }}}

        else {
            System.out.println("PAS TROUVE");
        }
        transaction.begin();
        for (Game game : games) {
            em.persist(game);
        }
        transaction.commit();
    }
}
