package org.example;

import java.util.Arrays;

import static org.example.Main.days;

public class Calculation {
    public static String[][] alotTime(Employee[] employees, Business business) {
        int[][] businessHours = business.getHoursOfOperation();
        int[][] employeeHoursInAnHour = business.getNumberOfEmployeesInAnHour();
        String[][] alotedTime = new String[7][24]; // 7 days, 24 hours

        // Step 1: Sort employees based on priority (full-time, then part-time)
        Arrays.sort(employees, (Employee e1, Employee e2) -> {
            if (e1.getEmployeeContract().equalsIgnoreCase("full-time") &&
                    e2.getEmployeeContract().equalsIgnoreCase("part-time")) {
                return -1;
            } else if (e1.getEmployeeContract().equalsIgnoreCase("part-time") &&
                    e2.getEmployeeContract().equalsIgnoreCase("full-time")) {
                return 1;
            }
            return 0;
        });

        // Step 2: Iterate through each hour in business operation and allocate employees
        for (int day = 0; day < 7; day++) {
            for (int hour = 0; hour < 24; hour++) {
                if (businessHours[day][hour] == 1) { // Check if the business is open during this hour
                    int employeesNeeded = employeeHoursInAnHour[day][hour];
                    int employeesAssigned = 0;

                    for (Employee employee : employees) {
                        if (employeesAssigned >= employeesNeeded) {
                            break; // Stop if we have assigned the required number of employees
                        }

                        int[][] availableMorning = employee.getAvailableTimeMorning();
                        int[][] availableEvening = employee.getAvailableTimeEvening();
                        int totalAssigned = employee.getAssignedTime();

                        if ((availableMorning[day][hour] == 1 || availableEvening[day][hour] == 1)
                                && totalAssigned < employee.getHours()) {
                            // Assign the employee to this time slot if available and hasn't reached max hours
                            alotedTime[day][hour] = employee.getEmployeeName();
                            employee.setAssignedTime(totalAssigned + 1);
                            employeesAssigned++;
                        }
                    }

                    // If not enough employees are available, flag this for management
                    if (employeesAssigned < employeesNeeded) {
                        System.out.println("Warning: Not enough employees available for " +
                                days[day] + " at " + hour + ":00. Consider hiring more staff.");
                    }
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
