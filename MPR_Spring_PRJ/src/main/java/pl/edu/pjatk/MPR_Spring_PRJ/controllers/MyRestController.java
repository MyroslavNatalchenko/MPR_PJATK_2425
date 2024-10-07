package pl.edu.pjatk.MPR_Spring_PRJ.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.MPR_Spring_PRJ.model.School;
import pl.edu.pjatk.MPR_Spring_PRJ.services.SchoolService;

import java.util.List;

@RestController
public class MyRestController {
    private SchoolService schoolService;

    @Autowired
    public MyRestController(final SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping("school/all")
    public List<School> getAll() {
        return this.schoolService.getSchoolList();
    }

    @GetMapping("school/{id}")
    public School getSchoolById(@PathVariable int id) {
        return this.schoolService.getSchool(id);
    }

    @PostMapping("school") //POST - tworzyc
    public void create(@RequestBody final School school) {
        this.schoolService.createSchool(school);
    }
}
