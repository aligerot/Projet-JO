
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import service.PlayerService;
import service.SportService;

import java.io.IOException;

public class PlayerTest {
    public static void main(String[] args) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jeuxolympiques");
        EntityManager em = emf.createEntityManager();
        PlayerService ss = new PlayerService();
        ss.addPlayer(em);
        em.close();
    }
}
