import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import service.EpreuveService;
import service.EventService;

import java.io.IOException;

public class EventTest {
    public static void main(String[] args) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jeuxolympiques");
        EntityManager em = emf.createEntityManager();
        EventService ss= new EventService();
        ss.addEvent(em);
        em.close();
    }
}
