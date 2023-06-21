import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import service.CountryService;
import service.SportService;

import java.io.IOException;

public class CountryTest {
    public static void main(String[] args) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jeuxolympiques");
        EntityManager em = emf.createEntityManager();
        CountryService ss = new CountryService();
        ss.addCountry(em);
        em.close();
    }
}
