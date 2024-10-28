package pl.edu.pjatk.MPR_Spring_PRJ;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.pjatk.MPR_Spring_PRJ.model.School;
import pl.edu.pjatk.MPR_Spring_PRJ.repository.SchoolRepository;
import pl.edu.pjatk.MPR_Spring_PRJ.service.SchoolService;
import pl.edu.pjatk.MPR_Spring_PRJ.service.StringUtilsService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SchoolServiceTest {
    private SchoolService schoolService;
    private StringUtilsService stringUtilsService;
    private SchoolRepository schoolRepository;

    @BeforeEach
    public void setUp() {
        this.stringUtilsService = Mockito.mock(StringUtilsService.class);
        this.schoolRepository = Mockito.mock(SchoolRepository.class);
        this.schoolService = new SchoolService(schoolRepository, stringUtilsService);

        clearInvocations(schoolRepository);
    }

    @Test
    public void createSetsSchoolNameToUpperCase() {
        School school = new School("Julkia", 1010);

        this.schoolService.createSchool(school);
        verify(stringUtilsService, times(1)).Upper(any());
        verify(schoolRepository, times(1)).save(any()); // 3 w Konstructorze
    }
}
