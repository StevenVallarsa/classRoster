/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sv.classroster;

import com.sv.classroster.controller.ClassRosterController;
import com.sv.classroster.dao.*;
import com.sv.classroster.service.ClassRosterServiceLayer;
import com.sv.classroster.service.ClassRosterServiceLayerImpl;
import com.sv.classroster.ui.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author: Steven Vallarsa
 * email: stevenvallarsa@gmail.com
 * date:
 * purpose:
 */
public class App {
    
    public static void main(String[] args) {
//        UserIO myIo = new UserIOConsoleImpl();
//        ClassRosterView myView = new ClassRosterView(myIo);
//        ClassRosterDao myDao = new ClassRosterDaoFileImpl();
//        ClassRosterAuditDao myAuditDao = new ClassRosterAuditDaoFileImpl();
//        ClassRosterServiceLayer myService = new ClassRosterServiceLayerImpl(myDao, myAuditDao);
//        ClassRosterController controller = new ClassRosterController(myService, myView);
           ApplicationContext ctx = 
           new ClassPathXmlApplicationContext("applicationContext.xml");
           ClassRosterController controller = ctx.getBean("controller", ClassRosterController.class);

        controller.run();
    }

}
