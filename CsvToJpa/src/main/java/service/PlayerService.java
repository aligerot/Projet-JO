package service;

import entite.Player;
import entite.Sport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class PlayerService {
    public void addPlayer(EntityManager em)throws IOException {
        EntityTransaction transaction = em.getTransaction();
        List<String> tempo= new ArrayList<>();
        Set<Player> players = new HashSet<>();
        Path chemin = Paths.get("evenements.csv");
        if (Files.isRegularFile(chemin)) {
            List<String> lignes = Files.readAllLines(chemin);
            for (String ligne : lignes) {
                tempo.clear();
                if (!ligne.contains("ID;")) {
                    String[] col = ligne.split(";");

                    for (String nom : col) {
                        while (nom.startsWith(" ") || nom.endsWith(" ")) {
                            nom = nom.trim();
                        }
                        tempo.add(nom);
                    }


                    Player player = new Player(tempo.get(1).replaceAll("\"([^\"]*)\"","$1"));
                    players.add(player);

                }
            }
        }
        else {
            System.out.println("PAS TROUVE");
        }
        transaction.begin();
        for (Player playertempo : players) {
            em.persist(playertempo);
        }
        transaction.commit();
    }
}
