import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import service.GameService;
import service.SportService;

import java.io.IOException;

public class GameTest {
    public static void main(String[] args) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jeuxolympiques");
        EntityManager em = emf.createEntityManager();
        GameService ss = new GameService();
        ss.addGame(em);
        em.close();
    }
}
