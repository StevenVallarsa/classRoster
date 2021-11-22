/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.sv.classroster.dao;

import com.sv.classroster.dto.Student;
import java.io.FileWriter;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author StevePro
 */
public class ClassRosterDaoFileImplTest {
    
        ClassRosterDao testDao;

    
    public ClassRosterDaoFileImplTest() {
    }

//    @org.junit.BeforeClass
//    public static void setUpClass() throws Exception {
//    }
//
//    @org.junit.AfterClass
//    public static void tearDownClass() throws Exception {
//    }
//
//    @org.junit.Before
//    public void setUp() throws Exception {
//    }
//
//    @org.junit.After
//    public void tearDown() throws Exception {
//    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        String testFile = "testroster.txt";
        new FileWriter(testFile);
        testDao = new ClassRosterDaoFileImpl(testFile);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAddGetSTudent() throws Exception {
        
        // ARRANGE
        // Create test student
        String studentId = "0001";
        Student student = new Student(studentId);
        student.setFirstName("Ada");
        student.setLastName("Lovelace");
        student.setCohort("Java-May-1845");
        
        // ACT
        // Add student to the DAO
        testDao.addStudent(studentId, student);
        
        // Get student from the DAO
        Student retrievedStudent = testDao.getStudent(studentId);
        
        // ASSERT
        // Test the data for equality
        assertEquals(retrievedStudent.getStudentId(), student.getStudentId(), "Checking student id.");
        assertEquals(retrievedStudent.getFirstName(), student.getFirstName(), "Checking student first name.");
        assertEquals(retrievedStudent.getLastName(), student.getLastName(), "Checking student last name.");
        assertEquals(retrievedStudent.getCohort(), student.getCohort(), "Checking student cohort.");
    }
    
    @Test
    public void testAddGetAllStudents() throws Exception {
        
        // Create 2 students
        Student firstStudent = new Student("0001");
        firstStudent.setFirstName("Ada");
        firstStudent.setLastName("Lovelace");
        firstStudent.setCohort("Java-May-1845");
        
        Student secondStudent = new Student("0002");
        secondStudent.setFirstName("Charles");
        secondStudent.setLastName("Babbage");
        secondStudent.setCohort("Java-May-1845");
        
        // Add the two students to DAO
        testDao.addStudent(firstStudent.getStudentId(), firstStudent);
        testDao.addStudent(secondStudent.getStudentId(), secondStudent);
        
        // Get list of students in DAO
        List<Student> allStudents = testDao.getAllStudents();
        
        // Check general content
        assertNotNull("The list of students must not be null", allStudents);
        assertEquals(2, allStudents.size(), "List of students should have 2 students.");
        
        // Check specific content
        assertTrue(testDao.getAllStudents().contains(firstStudent));
        assertTrue(testDao.getAllStudents().contains(secondStudent));
        
    }
    
    @Test
    public void testRemoveStudent() throws Exception {
        // Create 2 students
        Student firstStudent = new Student("0001");
        firstStudent.setFirstName("Ada");
        firstStudent.setLastName("Lovelace");
        firstStudent.setCohort("Java-May-1845");
        
        Student secondStudent = new Student("0002");
        secondStudent.setFirstName("Charles");
        secondStudent.setLastName("Babbage");
        secondStudent.setCohort("Java-May-1845");
        
        // Add the two students to DAO
        testDao.addStudent(firstStudent.getStudentId(), firstStudent);
        testDao.addStudent(secondStudent.getStudentId(), secondStudent);
        
        // Remove first student - Ada
        Student removedStudent = testDao.removeStudent(firstStudent.getStudentId());
        
        // Check to make sure correct object was removed
        assertEquals(removedStudent, firstStudent, "The moved student should be Ada.");
        
        List<Student> allStudents = testDao.getAllStudents();
        
        assertNotNull( "All students list should not be null.", allStudents);
        assertEquals(1, allStudents.size(), "All students should only have one student");
        
        assertFalse("All students should not include Ada.", allStudents.contains(firstStudent));
        assertTrue( "All students should include Charles", allStudents.contains(secondStudent));
        
        removedStudent = testDao.removeStudent(secondStudent.getStudentId());
        assertEquals(removedStudent, secondStudent, "The removed student is Charles" );
        
        allStudents = testDao.getAllStudents();
        
        assertTrue("The retreived list of students should be empty.", allStudents.isEmpty());
        
        Student retrievedStudent = testDao.getStudent(firstStudent.getStudentId());
        assertNull("Ada was removed so this should be null.", retrievedStudent);
        
        retrievedStudent = testDao.getStudent(secondStudent.getStudentId());
        assertNull("Charles was removed so this should be null.", retrievedStudent);
        
    }
    
    
}
