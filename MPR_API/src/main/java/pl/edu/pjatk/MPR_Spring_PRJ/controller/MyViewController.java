package pl.edu.pjatk.MPR_Spring_PRJ.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.MPR_Spring_PRJ.model.School;
import pl.edu.pjatk.MPR_Spring_PRJ.service.SchoolService;

import java.util.List;

@Controller
public class MyViewController {
    private SchoolService schoolService;

    public MyViewController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping("/view/all")
    public String displayAllSchools(Model model) {
        model.addAttribute("nazwazmiwennej", "wartosc");
        List<School> schoolList = this.schoolService.getAll();
        model.addAttribute("schoolList", schoolList);
        return "viewAll";
    }

    // ADD NEW SCHOOL
    @GetMapping("/addSchool")
    public String displayAddSchool(Model model) {
        model.addAttribute("school", new School());
        return "addForm";
    }
    @PostMapping("/addSchool")
    public String submitForm(@ModelAttribute School school) {
        this.schoolService.createSchool(school);
        return "redirect:/view/all";
    }

    // DELETE BY ID
    @GetMapping("/deleteSchool")
    public String displayDeleteSchool(@RequestParam("id") Long id, Model model) {
        School editSchool = this.schoolService.getByID(id);
        model.addAttribute("school", editSchool);
        return "deleteForm";
    }
    @PostMapping("/deleteSchool")
    public String submitDeleteForm(@ModelAttribute School school) {
        this.schoolService.removeSchool(school.getId());
        return "redirect:/view/all";
    }

    // DELETE BY ID
    @GetMapping("/editSchool")
    public String displayEditSchool(@RequestParam("id") Long id, Model model) {
        School editSchool = this.schoolService.getByID(id);
        model.addAttribute("school", editSchool);
        return "editForm";
    }
    @PostMapping("/editSchool")
    public String submitEditForm(@ModelAttribute School school) {
        this.schoolService.editSchool(school, school.getId());
        return "redirect:/view/all";
    }
}
