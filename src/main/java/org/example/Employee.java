package org.example;

import java.util.Scanner;

public class Employee {
    final private int employeeNumber;
    final private String employeeName;
    private String employeeContract;
    private int hours;
    private int [][] availableTimeMorning =new int[7][24];
    private int [][] availableTimeEvening =new int[7][24];
    private int assignedTime;
    private boolean overtime;
    private int totalAvailableTime;

    public Employee(int employeeNumber, String employeeName, String employeeContract, int[][] availableTimeMorning, int[][]availableTimeEvening, int assignedTime) {
        this.employeeNumber = employeeNumber;//the number of that employee to be used as a iterator
        this.employeeName = employeeName;//the name of the employee
        this.employeeContract = employeeContract;//part-time or full-time
        this.availableTimeEvening = availableTimeEvening;//the available time for evening is a 7x24 array which has 1 when the employee available -1 when the employee has not started to be free and 0 when he is not free
        this.availableTimeMorning = availableTimeMorning;//the available time for morning is same as evening just for morning
        if (employeeContract.equalsIgnoreCase("part-time")) {
            this.hours = 20;//available time for part-time employees in canada is 20 hours;
        } else if (employeeContract.equalsIgnoreCase("full-time")) {
            this.hours = 40;//available time for full-time employees in canada is 40 hours
        }
        while(assignedTime>hours) {
           System.out.println("the assigneed time entered is bigger then the "+employeeContract+" legal limit of "+hours+" !!!\n enter the aloted time which is lesser then "+hours+" hours!!!");
           Scanner scanner = new Scanner(System.in);
           assignedTime = scanner.nextInt();
        }
        this.assignedTime = assignedTime;//the time that the user wants this employee to work in a week
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }
    public int getAssignedTime() {
        return assignedTime;
    }
    public String getEmployeeContract() {
        return employeeContract;
    }
    public int getHours() {
        return hours;
    }
    public String getEmployeeName() {
        return employeeName;
    }
    public int[][] getAvailableTimeEvening() {
        return availableTimeEvening;
    }
    public int[][] getAvailableTimeMorning() {
        return availableTimeMorning;
    }
    public boolean isOvertime() {
        return overtime;
    }

    public void setOvertime(boolean overtime) {
        this.overtime = overtime;
    }
    public void setAvailableTimeEvening(int[][] availableTime) {
        this.availableTimeEvening = availableTime;//the available time is a 7x24 array which has 1 when the employee available
    }
    public void setAvailableTimeMorning(int[][] availableTimeMorning) {
        this.availableTimeMorning = availableTimeMorning;
    }
    public void setAssignedTime(int assignedTime) {
        this.assignedTime = assignedTime;
    }
    public void setEmployeeContract(String employeeContract) {
        if (employeeContract.equalsIgnoreCase("part-time")) {
            this.employeeContract = employeeContract;
            this.hours = 20;//available time for part-time employees in canada is 20 hours;
        } else if (employeeContract.equalsIgnoreCase("full-time")) {
            this.employeeContract = employeeContract;
            this.hours = 40;//available time for full-time employees in canada is 40 hours
        }
    }

    public void setTotalAvailableTime(Employee employee) {
        this.totalAvailableTime = totalAvailableTime;
    }
    public int getTotalAvailableTime() {
        return totalAvailableTime;
    }

    public int total (int hours) {

        return hours;
    }
}
//complete