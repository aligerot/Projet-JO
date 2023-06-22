package repository;



import entite.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

    @Repository
    public interface SportRepository  extends JpaRepository<Sport, Integer> {

        List<Sport> findAllById(int id);

        Optional<Sport> findByNameEn(String nameEn);
    }


