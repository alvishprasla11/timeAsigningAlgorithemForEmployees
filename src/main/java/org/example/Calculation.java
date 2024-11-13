package org.example;

import static org.example.Converters.Converter;

public class Calculation {
    final static public String[] days = {"","Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    public static String[][] alotTime(Employee[] employees, Business business) {
        hireMorePeople(employees, business);
        String[][] finalAlotedTime = new String[employees.length+1][8]; // 1st column is name, remaining 7 for days
        int[][][] alotedTime = new int[employees.length][7][24];

        int[][] businessHours = business.getHoursOfOperation(); // Get the business hours for the week
        int[][] numberOfEmployeesInAnHour = business.getNumberOfEmployeesInAnHour();
        int[][]freeTime=sortedFreeTime(employees, business);
        for (int i = 0; i < employees.length; i++) {
            int []currEmployee=freeTime[i];
            Employee employee = employees[currEmployee[0]];
            finalAlotedTime[0]=days;
            finalAlotedTime[i+1][0] = employee.getEmployeeName(); // Set employee name in the first column


            int[][] availableMorning = employee.getAvailableTimeMorning();
            int[][] availableEvening = employee.getAvailableTimeEvening();
            int assignedHours = employee.getAssignedTime();
            int totalAssignedHours = 0; // Track total hours assigned to the employee

            for(int j = 0; j < businessHours.length; j++) {
                for(int k = 0; k < businessHours[j].length; k++) {
                    if(assignedHours !=0 && totalAssignedHours < 8) {
                        if (numberOfEmployeesInAnHour[j][k] >= 1 && availableMorning[j][k] == 1) {
                            alotedTime[i][j][k]=1;
                            totalAssignedHours++;
                            assignedHours--;
                            numberOfEmployeesInAnHour[j][k]--;
                        } else if (numberOfEmployeesInAnHour[j][k] >= 1 && availableEvening[j][k] == 1 && totalAssignedHours == 0) {
                            alotedTime[i][j+1][k+1]=1;
                            totalAssignedHours++;
                            assignedHours--;
                            numberOfEmployeesInAnHour[j][k]--;
                        }
                    }
                    finalAlotedTime[i+1][j+1]= Converter(alotedTime[i][j]);
                }
            }
        }
        return finalAlotedTime;
    }

    public static int[][] sortedFreeTime(Employee[] employees,Business business) {

        int[][] businessHours = business.getHoursOfOperation();
        int [][]freeTime= new int[employees.length][2];
        int employeeNumber=-1;
        for(int i = 0; i < employees.length; i++) {
            int posibleHours=0;
            Employee employee = employees[i];
            freeTime[i][0]=employeeNumber++;
            for(int k = 0; k < employee.getAvailableTimeMorning().length; k++ ) {
                for(int l = 0; l < employee.getAvailableTimeMorning()[k].length; l++) {
                    if (employee.getAvailableTimeMorning()[k][l] == 1&& businessHours[k][l]==1) {
                        posibleHours++;
                    }
                    if(employee.getAvailableTimeEvening()[k][l]==1&& businessHours[k][l]==1) {
                        posibleHours++;
                    }
                }
            }
            freeTime[i][1]=posibleHours;
        }
        freeTime=freeTimeSorter(freeTime);
        return freeTime;
    }
    public static int[][] freeTimeSorter(int [][]freeTime) {
        //liner sort
        int []temp;
        for(int i = 0; i < freeTime.length; i++) {
            for (int j = i; j < freeTime.length; j++) {
                if (freeTime[i][1] > freeTime[j][1]) {
                    temp = freeTime[i];
                    freeTime[i] = freeTime[j];
                    freeTime[j] = temp;
                }
            }
        }
        for(int i = 0; i < freeTime.length; i++) {
            for (int j = i; j < freeTime.length; j++) {
                if (freeTime[i][1] == freeTime[j][1]) {
                    if(freeTime[i][0] > freeTime[j][0]) {
                        temp = freeTime[i];
                        freeTime[i] = freeTime[j];
                        freeTime[j] = temp;
                    }
                }
            }
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
//complete
