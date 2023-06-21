import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import service.EpreuveService;

import java.io.IOException;

public class EpreuveTest {
    public static void main(String[] args) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jeuxolympiques");
        EntityManager em = emf.createEntityManager();
        EpreuveService ss = new EpreuveService();
        ss.addEpreuve(em);
        em.close();
    }
}
