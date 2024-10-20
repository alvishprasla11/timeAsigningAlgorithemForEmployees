package org.example;

import java.util.Arrays;

import static org.example.Main.days;

public class Calculation {
    public static String[][] alotTime(Employee[] employees, Business business) {
        String[][] alotedTime = new String[employees.length][8]; // 1st column is name, remaining 7 for days

        int[][] businessHours = business.getHoursOfOperation(); // Get the business hours for the week

        for (int i = 0; i < employees.length; i++) {
            Employee employee = employees[i];
            alotedTime[i][0] = employee.getEmployeeName(); // Set employee name in the first column

            int[][] availableMorning = employee.getAvailableTimeMorning();
            int[][] availableEvening = employee.getAvailableTimeEvening();
            int assignedHours = employee.getAssignedTime();

            int totalAssignedHours = 0; // Track total hours assigned to the employee

            // Loop through each day of the week
            for (int day = 0; day < 7; day++) {
                StringBuilder assignedTimeForDay = new StringBuilder();

                // Assign morning hours if available and within limits
                for (int hour = 0; hour < 24 && totalAssignedHours < assignedHours; hour++) {
                    if (availableMorning[day][hour] == 1 && businessHours[day][hour] == 1) {
                        assignedTimeForDay.append(hour).append("-");
                        totalAssignedHours++;
                    } else if (assignedTimeForDay.length() > 0) {
                        assignedTimeForDay.append(hour);
                        break;
                    }
                }

                // Assign evening hours if necessary and within limits
                for (int hour = 0; hour < 24 && totalAssignedHours < assignedHours; hour++) {
                    if (availableEvening[day][hour] == 1 && businessHours[day][hour] == 1) {
                        assignedTimeForDay.append(hour).append("-");
                        totalAssignedHours++;
                    } else if (assignedTimeForDay.length() > 0) {
                        assignedTimeForDay.append(hour);
                        break;
                    }
                }

                // Set assigned times or mark as "OFF" if none assigned
                if (assignedTimeForDay.length() > 0) {
                    alotedTime[i][day + 1] = assignedTimeForDay.toString();
                } else {
                    alotedTime[i][day + 1] = "OFF";
                }
            }
        }

        return alotedTime;
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
