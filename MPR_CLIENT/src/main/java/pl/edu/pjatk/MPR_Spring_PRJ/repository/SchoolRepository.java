package pl.edu.pjatk.MPR_Spring_PRJ.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjatk.MPR_Spring_PRJ.model.School;

import java.util.List;

@Repository
//@Component ale odarzu mowi czym jest ta klasa (komunikacja z db)
public interface SchoolRepository extends CrudRepository<School, Long> {
    public List<School> findByName(String Name);
    public List<School> findByNumber(int id);
}
