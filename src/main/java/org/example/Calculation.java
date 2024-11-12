package org.example;

import java.util.Arrays;
import java.util.HashMap;

import static org.example.Main.days;

public class Calculation {
    public static String[][] alotTime(Employee[] employees, Business business) {

        //todo this is currently returning the time for the employees to work with accordance with the business time so it is giveing the correct time
        //todo just the employees in an hour criteria is not met rest the output is sorting with time
        hireMorePeople(employees, business);
        String[][] finalAlotedTime = new String[employees.length][8]; // 1st column is name, remaining 7 for days
        String[][][] alotedTime = new String[employees.length][8][25];

        int[][] businessHours = business.getHoursOfOperation(); // Get the business hours for the week
        int[][] numberOfEmployeesInAnHour = business.getNumberOfEmployeesInAnHour();

        for (int i = 0; i < employees.length; i++) {
            Employee employee = employees[i];
            finalAlotedTime[i][0] = employee.getEmployeeName(); // Set employee name in the first column
            alotedTime[i][0][0]= employee.getEmployeeName();

            int[][] availableMorning = employee.getAvailableTimeMorning();
            int[][] availableEvening = employee.getAvailableTimeEvening();
            int assignedHours = employee.getAssignedTime();
            int totalAssignedHours = 0; // Track total hours assigned to the employee

            for(int j = 0; j < businessHours.length; j++) {

                for(int k = 0; k < businessHours[j].length; k++) {
                    if (numberOfEmployeesInAnHour[j][k] >= 1&& availableMorning[j][k] == 1) {

                    }
                    else if (numberOfEmployeesInAnHour[j][k] == 1 && availableEvening[j][k] == 1) {

                    }
                }
            }


        }

        return finalAlotedTime;
    }

    public static int[][] sortedFreeTime(Employee[] employees) {
        int [][]freeTime= new int[employees.length][2];
        int j=-1;
        for(int i = 0; i < employees.length; i++) {
            Employee employee = employees[i];
            freeTime[i][0]=j++;
            for(int index = 0; index < employee.getAvailableTimeMorning().length; index++ ) {

            }
            freeTime[i][1]=0;
        }

        return freeTime;
    }


    public static void hireMorePeople(Employee[] employees, Business business) {
        int numberOfMaxHours = 0;
        for (Employee employee : employees) {
            numberOfMaxHours =+ employee.getAssignedTime();
        }
        int maxPossibleHours = business.getTotalSlots();
        int temp = maxPossibleHours - numberOfMaxHours;
        if (temp > 0) {
            System.out.println("Hire more people! the business times arent met! or increase time given to employees! ");

            System.exit(0);
        }
    }
}
