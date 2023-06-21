package app;

import entite.Evenement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import service.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jeuxolympiques");
        EntityManager em = emf.createEntityManager();
        SportService ss= new SportService();
        EpreuveService es= new EpreuveService();
        CountryService cs= new CountryService();
        ss.addSport(em);
        es.addEpreuve(em);
        cs.addCountry(em);
        GameService gs= new GameService();
        gs.addGame(em);
        PlayerService ps= new PlayerService();
        ps.addPlayer(em);

        EventService evs = new EventService();
        evs.addEvent(em);


        em.close();



    }
}