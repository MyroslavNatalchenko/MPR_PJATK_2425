package pl.edu.pjatk.MPR_Spring_PRJ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.MPR_Spring_PRJ.model.School;
import pl.edu.pjatk.MPR_Spring_PRJ.service.SchoolService;

import java.util.List;
import java.util.Optional;

@RestController
public class MyRestController {
    private SchoolService schoolService;

    @Autowired
    public MyRestController(final SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping("school/name/{name}")
    public List<School> getByName(@PathVariable String name) {
        return this.schoolService.getByName(name);
    }

    @GetMapping("school/number/{number}")
    public List<School> getByNumber(@PathVariable int number) {
        return this.schoolService.getByNumber(number);
    }

    @GetMapping("school/id/{id}")
    public Optional<School> getByID(@PathVariable Long id) { //WAZNE
        return this.schoolService.getByID(id);
    }

    @GetMapping("school/all")
    public List<School> getAll() {
        return this.schoolService.getAll();
    }

    @PostMapping("school")
    public void addSchool(@RequestBody School school) {
        this.schoolService.createSchool(school);
    }

    @PutMapping("school/edit/{id}")
    public void editSchool(@PathVariable Long id, @RequestBody School school) {
        this.schoolService.editSchool(school, id);
    }

    @DeleteMapping("school/delete/{id}")
    public void deleteSchool(@PathVariable Long id) {
        this.schoolService.removeSchool(id);
    }
}

/*
    Made at home 08.10.2024
    @GetMapping("school/all")
    public List<School> getAll() {
        return this.schoolService.getSchoolList();
    }
    @DeleteMapping("/school/delete/{id}")
    public void delete(@PathVariable int id)
    {
        this.schoolService.removeSchool(id);
    }

    @PutMapping("/school/mod/{id}")
    public void modificate(@PathVariable int id, @RequestBody final School school){
        this.schoolService.modSchool(id,school);
    }
    @GetMapping("school/{id}")
    public School getSchoolById(@PathVariable int id) {
        return this.schoolService.getSchool(id);
    }
    @PostMapping("school") //POST - tworzyc
    public void create(@RequestBody final School school) {
        this.schoolService.createSchool(school);
    }
*/
