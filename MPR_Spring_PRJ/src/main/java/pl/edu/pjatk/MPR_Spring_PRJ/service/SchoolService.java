package pl.edu.pjatk.MPR_Spring_PRJ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pjatk.MPR_Spring_PRJ.model.School;
import pl.edu.pjatk.MPR_Spring_PRJ.repository.SchoolRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SchoolService {
    private SchoolRepository schoolRepository;
    List<School> schoolList = new ArrayList<>();

    @Autowired
    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;

        this.schoolRepository.save(new School("PJATK WWA", 1));
        this.schoolRepository.save(new School("PJATK GDN", 2));
        this.schoolRepository.save(new School("KPNL", 145));
    }

    public List<School> getByName(String name) {
        return this.schoolRepository.findByName(name);
    }

    public List<School> getByNumber(int number) {
        return this.schoolRepository.findByNumber(number);
    }

    public Optional<School> getByID(Long id) {
        return schoolRepository.findById(id);
    } //Optional w extend clasie

    public List<School> getAll() {
        return (List<School>) this.schoolRepository.findAll(); //pereformat w List
    }

    public void createSchool(School school) {
        this.schoolRepository.save(school);
    }

    public void editSchool(School school, Long id) {
        if (id>0) {
            Optional<School> schoolOptional = this.schoolRepository.findById(id);
            if (schoolOptional.isPresent()) {
                schoolOptional.get().setName(school.getName());
                schoolOptional.get().setNumber(school.getNumber());
                schoolOptional.get().setIndetyfikator(school.countIndenticator());
                schoolRepository.save(schoolOptional.get());
            }
            else throw new RuntimeException("School not found");
        }
    }

    public void removeSchool(Long id) {
        this.schoolRepository.deleteById(id);
    }
}



/*
    public List<School> getSchoolList() {
        return this.schoolList;
    }
    public void createSchool(School school) {
        this.schoolList.add(school);
    }
    public School getSchool(int id) {
        return this.schoolList.get(id);
    }
    public void removeSchool(int id){
        this.schoolList.remove(id);
    }
    public void modSchool(int id, School school){
        schoolList.get(id).setName(school.getName());
        schoolList.get(id).setNumber(school.getNumber());
    }
*/