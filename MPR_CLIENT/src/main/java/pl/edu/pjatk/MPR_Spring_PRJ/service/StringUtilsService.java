package pl.edu.pjatk.MPR_Spring_PRJ.service;

import org.springframework.stereotype.Component;
import pl.edu.pjatk.MPR_Spring_PRJ.model.School;

@Component
public class StringUtilsService {
    public void Upper(School school){
        school.setName(school.getName().toUpperCase());
    }

    public void Lower(School school){
        if (school.getName() != null && !school.getName().isEmpty()) {
            String trimmedName = school.getName().trim();
            String firstPart = trimmedName.substring(0, 1).toUpperCase();
            String secondPart = trimmedName.substring(1).toLowerCase();
            school.setName(firstPart + secondPart);
        }
    }
}
