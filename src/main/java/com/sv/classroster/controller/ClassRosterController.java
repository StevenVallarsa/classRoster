/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sv.classroster.controller;

import com.sv.classroster.dao.ClassRosterPersistenceException;
import com.sv.classroster.dao.ClassRosterDaoFileImpl;
import com.sv.classroster.dto.Student;
import com.sv.classroster.ui.ClassRosterView;
import com.sv.classroster.ui.UserIOConsoleImpl;
import com.sv.classroster.ui.UserIO;
import java.util.List;
import com.sv.classroster.dao.ClassRosterDao;
import com.sv.classroster.service.ClassRosterDataValidationException;
import com.sv.classroster.service.ClassRosterDuplicateIdException;
import com.sv.classroster.service.ClassRosterServiceLayer;

/**
 *
 * @author: Steven Vallarsa
 * email: stevenvallarsa@gmail.com
 * date:
 * purpose:
 */
public class ClassRosterController {
    
//    private UserIOConsoleImpl io = new UserIOConsoleImpl(); // ???
    private ClassRosterServiceLayer service;
    private ClassRosterView view;
    
    public ClassRosterController(ClassRosterServiceLayer service, ClassRosterView view) {
        this.service = service;
        this.view = view;
    }
    
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        
        try {
        while (keepGoing) {            
            menuSelection = getMenuSelection();
            
            switch (menuSelection) {
                case 1:
                    listStudents();
                    break;
                case 2:
                    createStudent();
                    break;
                case 3:
                    viewStudent();
                    break;
                case 4:
                    removeStudent();
                    break;
                case 5:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }
        }
        exitMessage();
        } catch (ClassRosterPersistenceException e) {
        
            view.displayErrorMessage(e.getMessage());
        }
    }
    
private void createStudent() throws ClassRosterPersistenceException {
    view.displayCreateStudentBanner();
    boolean hasErrors = false;
    do {
        Student currentStudent = view.getNewStudentInfo();
        try {
            service.createStudent(currentStudent);
            view.displayCreateSuccessBanner();
            hasErrors = false;
        } catch (ClassRosterDuplicateIdException | ClassRosterDataValidationException e) {
            hasErrors = true;
            view.displayErrorMessage(e.getMessage());
        }
    } while (hasErrors);
}

    private void listStudents() throws ClassRosterPersistenceException {
//        view.displayDisplayAllBanner();
        List<Student> studentList = service.getAllStudents();
        view.displayStudentList(studentList);
    }
    
    private void viewStudent() throws ClassRosterPersistenceException {
//        view.displayDisplayStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student student = service.getStudent(studentId);
        view.displayStudent(student);
    }
    
    private void removeStudent() throws ClassRosterPersistenceException {
        view.displayRemovedStudentBanner();
        String studentId = view.getStudentIdChoice();
        service.removeStudent(studentId);
        view.displayRemovedStudentBanner();
    }
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }
}
