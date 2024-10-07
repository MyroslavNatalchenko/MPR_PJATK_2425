package pl.edu.pjatk.MPR_Spring_PRJ.services;

import org.springframework.stereotype.Component;
import pl.edu.pjatk.MPR_Spring_PRJ.model.School;

import java.util.ArrayList;
import java.util.List;

@Component
public class SchoolService {
    List<School> schoolList = new ArrayList<>();

    public SchoolService() {
        schoolList.add(new School("PJATK WWA", 1));
        schoolList.add(new School("PJATK GDN", 2));
        schoolList.add(new School("KPNL", 145));
    }

    public List<School> getSchoolList() {
        return this.schoolList;
    }

    public void createSchool(School school) {
        this.schoolList.add(school);
    }

    public School getSchool(int id) {
        return this.schoolList.get(id);
    }


}
