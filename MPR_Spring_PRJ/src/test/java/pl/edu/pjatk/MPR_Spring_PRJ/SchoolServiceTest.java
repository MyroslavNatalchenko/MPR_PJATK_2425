package pl.edu.pjatk.MPR_Spring_PRJ;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.pjatk.MPR_Spring_PRJ.exception.CanNotDeleteSchoolException;
import pl.edu.pjatk.MPR_Spring_PRJ.exception.CanNotEditSchoolException;
import pl.edu.pjatk.MPR_Spring_PRJ.exception.SchoolNotFoundException;
import pl.edu.pjatk.MPR_Spring_PRJ.exception.SchoolWithThisIndentificatorAlreadyExistException;
import pl.edu.pjatk.MPR_Spring_PRJ.model.School;
import pl.edu.pjatk.MPR_Spring_PRJ.repository.SchoolRepository;
import pl.edu.pjatk.MPR_Spring_PRJ.service.SchoolService;
import pl.edu.pjatk.MPR_Spring_PRJ.service.StringUtilsService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SchoolServiceTest {
    private SchoolService schoolService;
    private StringUtilsService stringUtilsService;
    private SchoolRepository schoolRepository;

    // Базовое выполнени всего
    @BeforeEach
    public void setUp() {
        this.stringUtilsService = Mockito.mock(StringUtilsService.class);
        this.schoolRepository = Mockito.mock(SchoolRepository.class);
        this.schoolService = new SchoolService(schoolRepository, stringUtilsService);

        clearInvocations(schoolRepository); //истить информацию о том сколько раз была применена функция
    }

    // ********************************************
    // ********************************************
    // НАЧАЛО - ПРОВЕРКА УСПЕШНЫХ СОЗДАНИЙ
    // ********************************************
    // ********************************************

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


    // СПРОСИТЬ ПОЧЕМУ НЕ ХОЧЕТ ДЕЛАТЬ LOWER
    @Test
    public void testGetByIDReturnsSchoolInLowerCase() {
        School school = new School("Julkia", 1010);
        when(this.schoolRepository.findById(1L)).thenReturn(Optional.of(school));

        School result = schoolService.getByID(1L);

        verify(stringUtilsService, times(1)).Lower(school);
        System.out.println(result.getName());
        assertEquals("Julkia", result.getName());
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
    public void testRemoveSchoolDeletesById() {
        Long schoolId = 1L;

        schoolService.removeSchool(schoolId);

        verify(schoolRepository, times(1)).deleteById(schoolId);
    }

    // ********************************************
    // ********************************************
    // КОНЕЦ - ПРОВЕРКА УСПЕШНЫХ СОЗДАНИЙ
    // ********************************************
    // ********************************************

    @Test
    public void testGetByIDthrowsNOTfoundEXCEPTION() {
        when(schoolRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(SchoolNotFoundException.class,
                () -> {schoolService.getByID(1L);
        });

        assertEquals("School not found", exception.getMessage());
        verify(stringUtilsService, never()).Lower(any());
    }

    @Test
    public void testGetByNAMEthrowsNOTfoundEXCEPTION() {
        when(schoolRepository.findByName("julia")).thenReturn(Collections.emptyList());

//        String message = null;
//        try{
//            List<School> list = schoolService.getByName("Julia");
//        } catch (Exception e){ message=e.getMessage(); }

        Exception exception = assertThrows(SchoolNotFoundException.class,
                () -> {schoolService.getByName("julia");
                });

        assertEquals("School not found", exception.getMessage());
        //assertEquals("School not found", message);
        verify(stringUtilsService, never()).Lower(any());
    }

    @Test
    public void testEditSchoolThrowsExceptionWhenSchoolNotFound() {
        School updatedSchool = new School("NewName", 200);
        when(schoolRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(CanNotEditSchoolException.class, () -> {
            schoolService.editSchool(updatedSchool, 1L);
        });

        assertEquals("School not found. It is impossible to edit School that does not exist", exception.getMessage());
        verify(schoolRepository, never()).save(any());
    }

    @Test
    public void testDeleteByIDSchoolThatDontExist() {
        when(schoolRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(CanNotDeleteSchoolException.class,
                () -> {schoolService.removeSchool(1L);
                });

        assertEquals("Can Not Delete School. School with such ID does not exist", exception.getMessage());
        verify(stringUtilsService, never()).Lower(any());
    }

    @Test
    public void testCreateWithSuchIndentificator() {
        School school = new School("PJATK WWA", 11), school2 = new School("KPNL", 13);
        when(schoolRepository.findAll()).thenReturn(Arrays.asList(school,school2));

        Exception exception = assertThrows(SchoolWithThisIndentificatorAlreadyExistException.class,
                () -> {schoolService.createSchool(new School("PJATK WWA", 11));
                });

        assertEquals("Can not create School. School with such inditificator already exist", exception.getMessage());
        verify(stringUtilsService, never()).Lower(any());
    }
}











//    @Test
//    public void createSetsSchoolNameToUpperCase() {
//        School school = new School("Julkia", 1010);
//
//        this.schoolService.createSchool(school);
//        verify(stringUtilsService, times(1)).Upper(any());
//        verify(schoolRepository, times(1)).save(any()); // 3 w Konstructorze
//    }
//
//    @Test
//    public void testGetByNameReturnsLowerCaseSchools() {
//        School school = new School("Julkia", 1010);
//        when(schoolRepository.findByName("Julkia")).thenReturn(Arrays.asList(school));
//
//        List<School> result = schoolService.getByName("Julkia");
//
//        assertEquals(1, result.size());
//        verify(stringUtilsService, times(1)).Lower(school);
//    }
//
//    @Test
//    public void testGetByNumberReturnsLowerCaseSchools() {
//        School school = new School("Julkia", 1010);
//        when(schoolRepository.findByNumber(1010)).thenReturn(Arrays.asList(school));
//
//        List<School> result = schoolService.getByNumber(1010);
//
//        assertEquals(1, result.size());
//        verify(stringUtilsService, times(1)).Lower(school);
//    }
//
//    @Test
//    public void testGetByIDReturnsSchoolInLowerCase() {
//        School school = new School("Julkia", 1010);
//        when(schoolRepository.findById(0L)).thenReturn(Optional.of(school));
//
//        School result = schoolService.getByID(0L);
//
//        verify(stringUtilsService, times(1)).Lower(school);
//
//        assertEquals("Julkia", result.getName());
//    }
//
//
//    @Test
//    public void testGetByIDThrowsSchoolNotFoundExceptionWhenNotFound() {
//        when(schoolRepository.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(SchoolNotFoundException.class, () -> schoolService.getByID(1L));
//
//        verify(stringUtilsService, never()).Lower(any());
//    }
//
//
//    @Test
//    public void testGetAllReturnsLowerCaseSchools() {
//        School school1 = new School("School1", 1);
//        School school2 = new School("School2", 2);
//        when(schoolRepository.findAll()).thenReturn(Arrays.asList(school1, school2));
//
//        List<School> result = schoolService.getAll();
//
//        assertEquals(2, result.size());
//        verify(stringUtilsService, times(1)).Lower(school1);
//        verify(stringUtilsService, times(1)).Lower(school2);
//    }
//
//    @Test
//    public void testEditSchoolUpdatesSchoolWhenExists() {
//        School existingSchool = new School("OldName", 100);
//        School updatedSchool = new School("NewName", 200);
//        when(schoolRepository.findById(1L)).thenReturn(Optional.of(existingSchool));
//
//        schoolService.editSchool(updatedSchool, 1L);
//
//        assertEquals("NewName", existingSchool.getName());
//        assertEquals(200, existingSchool.getNumber());
//        verify(schoolRepository, times(1)).save(existingSchool);
//    }
//
//    @Test
//    public void testEditSchoolThrowsExceptionWhenSchoolNotFound() {
//        School updatedSchool = new School("NewName", 200);
//        when(schoolRepository.findById(1L)).thenReturn(Optional.empty());
//
//        Exception exception = assertThrows(RuntimeException.class, () -> {
//            schoolService.editSchool(updatedSchool, 1L);
//        });
//
//        assertEquals("School not found", exception.getMessage());
//        verify(schoolRepository, never()).save(any());
//    }
//
//    @Test
//    public void testRemoveSchoolDeletesById() {
//        Long schoolId = 1L;
//
//        schoolService.removeSchool(schoolId);
//
//        verify(schoolRepository, times(1)).deleteById(schoolId);
//    }