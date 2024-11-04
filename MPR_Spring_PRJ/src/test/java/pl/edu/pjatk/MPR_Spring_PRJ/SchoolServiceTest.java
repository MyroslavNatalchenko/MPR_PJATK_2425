package pl.edu.pjatk.MPR_Spring_PRJ;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.pjatk.MPR_Spring_PRJ.model.School;
import pl.edu.pjatk.MPR_Spring_PRJ.repository.SchoolRepository;
import pl.edu.pjatk.MPR_Spring_PRJ.service.SchoolService;
import pl.edu.pjatk.MPR_Spring_PRJ.service.StringUtilsService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    public void testGetByNameReturnsLowerCaseSchools() {
        School school = new School("Julkia", 1010);
        when(schoolRepository.findByName("Julkia")).thenReturn(Arrays.asList(school));

        List<School> result = schoolService.getByName("Julkia");

        assertEquals(1, result.size());
        verify(stringUtilsService, times(1)).Lower(school);
    }

    @Test
    public void testGetByNumberReturnsLowerCaseSchools() {
        School school = new School("Julkia", 1010);
        when(schoolRepository.findByNumber(1010)).thenReturn(Arrays.asList(school));

        List<School> result = schoolService.getByNumber(1010);

        assertEquals(1, result.size());
        verify(stringUtilsService, times(1)).Lower(school);
    }

    @Test
    public void testGetByIDReturnsLowerCaseSchool() {
        School school = new School("Julkia", 1010);
        when(schoolRepository.findById(1L)).thenReturn(Optional.of(school));

        Optional<School> result = schoolService.getByID(1L);

        assertTrue(result.isPresent());
        assertEquals(school, result.get());
        verify(stringUtilsService, times(1)).Lower(school);
    }

    @Test
    public void testGetByIDReturnsEmptyOptionalWhenNotFound() {
        when(schoolRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<School> result = schoolService.getByID(1L);

        assertTrue(result == null);
        verify(stringUtilsService, never()).Lower(any());
    }

    @Test
    public void testGetAllReturnsLowerCaseSchools() {
        School school1 = new School("School1", 1);
        School school2 = new School("School2", 2);
        when(schoolRepository.findAll()).thenReturn(Arrays.asList(school1, school2));

        List<School> result = schoolService.getAll();

        assertEquals(2, result.size());
        verify(stringUtilsService, times(1)).Lower(school1);
        verify(stringUtilsService, times(1)).Lower(school2);
    }

    @Test
    public void testEditSchoolUpdatesSchoolWhenExists() {
        School existingSchool = new School("OldName", 100);
        School updatedSchool = new School("NewName", 200);
        when(schoolRepository.findById(1L)).thenReturn(Optional.of(existingSchool));

        schoolService.editSchool(updatedSchool, 1L);

        assertEquals("NewName", existingSchool.getName());
        assertEquals(200, existingSchool.getNumber());
        verify(schoolRepository, times(1)).save(existingSchool);
    }

    @Test
    public void testEditSchoolThrowsExceptionWhenSchoolNotFound() {
        School updatedSchool = new School("NewName", 200);
        when(schoolRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            schoolService.editSchool(updatedSchool, 1L);
        });

        assertEquals("School not found", exception.getMessage());
        verify(schoolRepository, never()).save(any());
    }

    @Test
    public void testRemoveSchoolDeletesById() {
        Long schoolId = 1L;

        schoolService.removeSchool(schoolId);

        verify(schoolRepository, times(1)).deleteById(schoolId);
    }
}
