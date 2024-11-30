package pl.edu.pjatk.MPR_Spring_PRJ;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.pjatk.MPR_Spring_PRJ.exception.*;
import pl.edu.pjatk.MPR_Spring_PRJ.model.School;
import pl.edu.pjatk.MPR_Spring_PRJ.repository.SchoolRepository;
import pl.edu.pjatk.MPR_Spring_PRJ.service.SchoolService;
import pl.edu.pjatk.MPR_Spring_PRJ.service.StringUtilsService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SchoolServiceTest {
    private SchoolService schoolService;
    private StringUtilsService stringUtilsServiceSpy;
    private SchoolRepository schoolRepositorySpy;

    @BeforeEach
    public void setUp() {
        // Создаем реальные объекты
        StringUtilsService stringUtilsService = new StringUtilsService();

        // Используем мок для репозитория
        SchoolRepository schoolRepository = mock(SchoolRepository.class);

        // Создаем Spy для stringUtilsService
        this.stringUtilsServiceSpy = spy(stringUtilsService);

        // Используем мок напрямую для репозитория
        this.schoolRepositorySpy = schoolRepository;

        // Инициализируем тестируемый сервис
        this.schoolService = new SchoolService(schoolRepositorySpy, stringUtilsServiceSpy);

        clearInvocations(schoolRepository);
        clearInvocations(schoolRepositorySpy);
    }


    // ********************************************
    // УСПЕШНЫЕ ТЕСТЫ
    // ********************************************

    @Test
    public void createSetsSchoolNameToUpperCase() {
        School school = new School("julia", 1010);

        schoolService.createSchool(school);

        verify(stringUtilsServiceSpy, times(1)).Upper(school);
        verify(schoolRepositorySpy, times(1)).save(school);
    }

    @Test
    public void testGetByNameReturnsLowerCaseSchools() {
        School school = new School("Julia", 1010);
        doReturn(Collections.singletonList(school)).when(schoolRepositorySpy).findByName("Julia");

        List<School> result = schoolService.getByName("Julia");

        assertEquals(1, result.size());
        verify(stringUtilsServiceSpy, times(1)).Lower(school);
    }

    @Test
    public void testGetByNumberReturnsLowerCaseSchools() {
        School school = new School("Julia", 1010);
        doReturn(Collections.singletonList(school)).when(schoolRepositorySpy).findByNumber(1010);

        List<School> result = schoolService.getByNumber(1010);

        assertEquals(1, result.size());
        verify(stringUtilsServiceSpy, times(1)).Lower(school);
    }

    @Test
    public void testGetByIDReturnsSchoolInLowerCase() {
        School school = new School("Julia", 1010);
        doReturn(Optional.of(school)).when(schoolRepositorySpy).findById(1L);

        School result = schoolService.getByID(1L);

        assertEquals("Julia", result.getName());
        verify(stringUtilsServiceSpy, times(1)).Lower(school);
    }

    @Test
    public void testGetAllReturnsLowerCaseSchools() {
        School school1 = new School("School1", 1);
        School school2 = new School("School2", 2);
        doReturn(Arrays.asList(school1, school2)).when(schoolRepositorySpy).findAll();

        List<School> result = schoolService.getAll();

        assertEquals(2, result.size());
        verify(stringUtilsServiceSpy, times(1)).Lower(school1);
        verify(stringUtilsServiceSpy, times(1)).Lower(school2);
    }

    @Test
    public void testEditSchoolUpdatesSchoolWhenExists() {
        School existingSchool = new School("OldName", 100);
        School updatedSchool = new School("NewName", 200);
        doReturn(Optional.of(existingSchool)).when(schoolRepositorySpy).findById(1L);

        schoolService.editSchool(updatedSchool, 1L);

        assertEquals("NewName", existingSchool.getName());
        assertEquals(200, existingSchool.getNumber());
        verify(schoolRepositorySpy, times(1)).save(existingSchool);
    }

    @Test
    public void testRemoveSchoolDeletesById() {
        School school = new School("PJATKA", 1);
        doReturn(Optional.of(school)).when(schoolRepositorySpy).findById(1L);

        schoolService.removeSchool(1L);

        verify(schoolRepositorySpy, times(1)).deleteById(1L);
    }

    // ********************************************
    // НЕГАТИВНЫЕ ТЕСТЫ
    // ********************************************

    @Test
    public void testGetByIDThrowsSchoolNotFoundExceptionWhenNotFound() {
        doReturn(Optional.empty()).when(schoolRepositorySpy).findById(1L);

        Exception exception = assertThrows(SchoolNotFoundException.class, () -> schoolService.getByID(1L));

        assertEquals("School not found", exception.getMessage());
        verify(stringUtilsServiceSpy, never()).Lower(any());
    }

    @Test
    public void testGetByNameThrowsSchoolNotFoundExceptionWhenNotFound() {
        doReturn(Collections.emptyList()).when(schoolRepositorySpy).findByName("Julia");

        Exception exception = assertThrows(SchoolNotFoundException.class, () -> schoolService.getByName("Julia"));

        assertEquals("School not found", exception.getMessage());
        verify(stringUtilsServiceSpy, never()).Lower(any());
    }

    @Test
    public void testEditSchoolThrowsExceptionWhenSchoolNotFound() {
        School updatedSchool = new School("NewName", 200);
        doReturn(Optional.empty()).when(schoolRepositorySpy).findById(1L);

        Exception exception = assertThrows(CanNotEditSchoolException.class, () -> {
            schoolService.editSchool(updatedSchool, 1L);
        });

        assertEquals("School not found. It is impossible to edit School that does not exist", exception.getMessage());
        verify(schoolRepositorySpy, never()).save(any());
    }

    @Test
    public void testDeleteSchoolThrowsExceptionWhenSchoolNotFound() {
        doReturn(Optional.empty()).when(schoolRepositorySpy).findById(1L);

        Exception exception = assertThrows(CanNotDeleteSchoolException.class, () -> schoolService.removeSchool(1L));

        assertEquals("Can Not Delete School. School with such ID does not exist", exception.getMessage());
        verify(schoolRepositorySpy, never()).deleteById(anyLong());
    }

//    @Test
//    public void testCreateThrowsExceptionWhenIdentifierExists() {
//        School school1 = new School("PJATK WWA", 11);
//        School school2 = new School("KPNL", 13);
//        doReturn(Arrays.asList(school1, school2)).when(schoolRepositorySpy).findAll();
//
//        Exception exception = assertThrows(SchoolWithThisIndentificatorAlreadyExistException.class, () -> {
//            schoolService.createSchool(new School("New School", 11));
//        });
//
//        assertEquals("Can not create School. School with such inditificator already exist", exception.getMessage());
//        verify(stringUtilsServiceSpy, never()).Lower(any());
//    }
}
