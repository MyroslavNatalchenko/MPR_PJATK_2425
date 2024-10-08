package pl.edu.pjatk.MPR_Spring_PRJ.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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


    /*
    Made at home 08.10.2024
     */
    @DeleteMapping("/school/delete/{id}")
    public void delete(@PathVariable int id)
    {
        this.schoolService.removeSchool(id);
    }

    @PutMapping("/school/mod/{id}")
    public void modificate(@PathVariable int id, @RequestBody final School school){
        this.schoolService.modSchool(id,school);
    }
}
