/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.sv.classroster.service;

import com.sv.classroster.dao.ClassRosterAuditDao;
import com.sv.classroster.dao.ClassRosterDao;
import com.sv.classroster.dao.ClassRosterPersistenceException;
import com.sv.classroster.dto.Student;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author StevePro
 */
public class ClassRosterServiceLayerImplTest {
    
    private ClassRosterServiceLayer service;
    
    public ClassRosterServiceLayerImplTest() {
//        ClassRosterDao dao = new ClassRosterDaoStubImpl();
//        ClassRosterAuditDao auditDao = new ClassRosterAuditDaoStubImpl();
//        
//        service = new ClassRosterServiceLayerImpl(dao, auditDao);
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", ClassRosterServiceLayer.class);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
        
        // ARRANGE
        Student student = new Student("0002");
        student.setFirstName("Charles");
        student.setLastName("Babbage");
        student.setCohort("Java-May-1845");
        
        // ACT
        try {
            service.createStudent(student);
        } catch (ClassRosterDuplicateIdException | ClassRosterDataValidationException | ClassRosterPersistenceException e) {
        
        // ASSERT
            fail("Student was valid. No exception should have been thrown.");
        }
    }
    
    @Test
    public void testCreateDuplicateIdStudent() {

        // ARRANGE
        Student student = new Student("0001");
        student.setFirstName("Charles");
        student.setLastName("Babbage");
        student.setCohort("Java-May-1845");
        
        // ACT
        try {
            service.createStudent(student);
            fail("Expected DupeId Exception was not thrown");
        } catch (ClassRosterDataValidationException | ClassRosterPersistenceException e) {
            
        // ASSERT
            fail("Incorrect exception was thrown.");
        } catch(ClassRosterDuplicateIdException e) {
            return;
        }
        
    }
    
    @Test
    public void testCreateStudentInvalidData() throws Exception {
        
        // ARRANGE
        Student student = new Student("0002");
        student.setFirstName("");
        student.setLastName("Babbage");
        student.setCohort("Java-May-1845");
        
        // ACT
        try {
            service.createStudent(student);
            fail("Expected ValidationException was not thrown.");
        } catch(ClassRosterDuplicateIdException | ClassRosterPersistenceException e) {
            
        // ASSERT
            fail("Incorrect exception was thrown.");
        } catch(ClassRosterDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testGetAllStudents() throws Exception {
        
        // ARRANGE
        Student testClone = new Student("0001");
        testClone.setFirstName("Ada");
        testClone.setLastName("Lovelace");
        testClone.setCohort("Java-May-1845");
        
        // ACT & ASSERT
        assertEquals("Should only have one student.", 1, service.getAllStudents().size());
        assertTrue("The one student should be Ada.", service.getAllStudents().contains(testClone));
        
    }
    
    @Test
    public void testGetStudent() throws Exception {
        
        // ARRANGE
        Student testClone = new Student("0001");
        testClone.setFirstName("Ada");
        testClone.setLastName("Lovelace");
        testClone.setCohort("Java-May-1845");
        
        // ACT & ASSERT
        Student shouldBeAda = service.getStudent("0001");
        assertNotNull("Getting 0001 should be not null.", shouldBeAda);
        assertEquals("Student stored under 0001 should be Ada.", testClone, shouldBeAda);
        
        Student shouldBeNull = service.getStudent("007");
        assertNull("Getting 007 should be null.", shouldBeNull);
    }
    
    @Test
    public void testRemoveStudent() throws Exception {
        
        // ARRANGE
        Student testClone = new Student("0001");
        testClone.setFirstName("Ada");
        testClone.setLastName("Lovelace");
        testClone.setCohort("Java-May-1845");
        
        //ACT & ASSERT
        Student shouldBeAda = service.removeStudent("0001");
        assertNotNull("Removing 0001 should be not null", shouldBeAda);
        assertEquals("STudent removed from 0001 should be Ada.", testClone, shouldBeAda);
        
        Student shouldBeNull = service.removeStudent("007");
        assertNull("Removing 007 should be null.", shouldBeNull);
        
    }
}
