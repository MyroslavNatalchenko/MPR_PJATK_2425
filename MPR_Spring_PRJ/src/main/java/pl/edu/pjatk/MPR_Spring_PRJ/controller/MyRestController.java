package pl.edu.pjatk.MPR_Spring_PRJ.controller;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.MPR_Spring_PRJ.model.School;
import pl.edu.pjatk.MPR_Spring_PRJ.service.SchoolService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class MyRestController {
    private SchoolService schoolService;

    @Autowired
    public MyRestController(final SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping("school/name/{name}")
    public ResponseEntity<List<School>> getByName(@PathVariable String name) {
        return new ResponseEntity<>(this.schoolService.getByName(name), HttpStatus.OK);
    }

    @GetMapping("school/number/{number}")
    public ResponseEntity<List<School>> getByNumber(@PathVariable int number) {
        return new ResponseEntity<>(this.schoolService.getByNumber(number), HttpStatus.OK);
    }

    @GetMapping("school/id/{id}")
    public ResponseEntity<School> getByID(@PathVariable Long id) { //WAZNE
        return new ResponseEntity<>(this.schoolService.getByID(id),HttpStatus.OK);
    }

    @GetMapping("school/pdf/id/{id}")
    public ResponseEntity<byte[]> getPDFByID(@PathVariable Long id) {
        PDDocument document = this.schoolService.getPDFByID(id);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (out) {
            document.save(out);
            document.close();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            //headers.setContentDispositionFormData("inline", "school_" + id + ".pdf");

            // Убедимся, что Content-Disposition настроен корректно
            headers.set("Content-Disposition", "inline; filename=\"school_" + id + ".pdf\"");

            return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException("Error generating PDF response", e);
        }
    }

    @GetMapping("school/all")
    public ResponseEntity<List<School>> getAll() {
        return new ResponseEntity<>(this.schoolService.getAll(), HttpStatus.OK);
    }

    @PostMapping("school")
    public ResponseEntity<Object> addSchool(@RequestBody School school) {
        this.schoolService.createSchool(school);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("school/edit/{id}")
    public ResponseEntity<Object> editSchool(@PathVariable Long id, @RequestBody School school) {
        this.schoolService.editSchool(school, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("school/delete/{id}")
    public ResponseEntity<Object> deleteSchool(@PathVariable Long id) {
        this.schoolService.removeSchool(id);
        return new ResponseEntity<>(HttpStatus.OK);
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
