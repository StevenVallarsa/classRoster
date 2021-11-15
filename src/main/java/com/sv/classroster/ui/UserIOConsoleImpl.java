/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sv.classroster.ui;

import java.util.Scanner;

/**
 *
 * @author: Steven Vallarsa
 * email: stevenvallarsa@gmail.com
 * date:
 * purpose:
 */
public class UserIOConsoleImpl implements UserIO {
    
    private Scanner console = new Scanner(System.in);

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public double readDouble(String prompt) {
        while(true) {
            try {
                return Double.parseDouble(this.readString(prompt));
            } catch(NumberFormatException e) {
                this.readString("Try again d");
            }
        }
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        while(true) {
            try {
                double pick = Double.parseDouble(this.readString(prompt));
                if (pick >= min && pick <=
                        max) {
                    return pick;
                }
            } catch(NumberFormatException e) {
                this.readString("Try again dmm");
            }
        }

    }

    @Override
    public float readFloat(String prompt) {
        while(true) {
            try {
                return Float.parseFloat(this.readString(prompt));
            } catch(NumberFormatException e) {
                this.readString("Try again f");
            }
        }

    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        while(true) {
            try {
                float pick = Float.parseFloat(this.readString(prompt));
                if (pick >= min && pick <= max) {
                    return pick;
                }
            } catch(NumberFormatException e) {
                this.readString("Try again fmm");
            }
        }

    }

    @Override
    public int readInt(String prompt) {
        while(true) {
            try {
                return Integer.parseInt(this.readString(prompt));
            } catch(NumberFormatException e) {
                this.readString("Try again i");
            }
        }
    }

    @Override
    public int readInt(String prompt, int min, int max) {

        while(true) {
            try {
                int pick = Integer.parseInt(this.readString(prompt));
                if (pick >= min && pick <= max) {
                    return pick;
                }
            } catch(NumberFormatException e) {
                this.readString("Press ENTER to try again.");
            }
        }
    }

    @Override
    public long readLong(String prompt) {
        while(true) {
            try {
                return Long.parseLong(this.readString(prompt));
            } catch(NumberFormatException e) {
                this.readString("Try again l");
            }
        }
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        while(true) {
            try {
                long pick = Long.parseLong(this.readString(prompt));
                if (pick >= min && pick <= max) {
                    return pick;
                }
            } catch(NumberFormatException e) {
                this.readString("Try again lmm");
            }
        }
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return console.nextLine();
    }

}
