/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sv.classroster;

import com.sv.classroster.controller.ClassRosterController;
import com.sv.classroster.dao.*;
import com.sv.classroster.ui.*;

/**
 *
 * @author: Steven Vallarsa
 * email: stevenvallarsa@gmail.com
 * date:
 * purpose:
 */
public class App {
    
    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        ClassRosterView myView = new ClassRosterView(myIo);
        
        classRosterDao myDao = new ClassRosterDaoFileImpl();
                
        ClassRosterController controller = new ClassRosterController(myDao, myView);
        controller.run();
    }

}
