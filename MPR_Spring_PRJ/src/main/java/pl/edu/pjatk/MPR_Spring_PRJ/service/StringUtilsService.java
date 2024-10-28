package pl.edu.pjatk.MPR_Spring_PRJ.service;

import org.springframework.stereotype.Component;
import pl.edu.pjatk.MPR_Spring_PRJ.model.School;

@Component
public class StringUtilsService {
    public void Upper(School school){
        school.setName(school.getName().toUpperCase());
    }

    public void Lower(School school){
        String first_part = school.getName().substring(0, 1).toUpperCase();
        int length = school.getName().length();
        String second_part = school.getName().substring(1,length).toLowerCase();
        school.setName(first_part + second_part);
    }
}
