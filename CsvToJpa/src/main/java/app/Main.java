package app;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import service.CountryService;
import service.EpreuveService;
import service.SportService;

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
        em.close();

    }
}