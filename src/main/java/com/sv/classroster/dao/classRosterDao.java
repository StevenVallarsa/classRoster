/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sv.classroster.dao;

import com.sv.classroster.dto.Student;
import java.util.List;

/**
 *
 * @author: Steven Vallarsa
 * email: stevenvallarsa@gmail.com
 * date:
 * purpose:
 */
public interface classRosterDao {
    
    Student addStudent(String studentId, Student student) throws ClassRosterDaoException;
    
    List<Student> getAllStudents() throws ClassRosterDaoException;
    
    Student getStudent(String studentId) throws ClassRosterDaoException;
    
    Student removeStudent(String studentId) throws ClassRosterDaoException;
}
