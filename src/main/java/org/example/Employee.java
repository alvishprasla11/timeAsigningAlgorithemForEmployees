package org.example;

public class Employee {
    final private int employeeNumber;
    final private String employeeName;
    private String employeeContract;
    private int hours;
    private int [][] availableTime=new int[7][24];
    private int assignedTime;
    private boolean overtime;

    public Employee(int employeeNumber, String employeeName, String employeeContract, int[][] availableTime, int assignedTime,String overtime) {
        this.employeeNumber = employeeNumber;
        this.employeeName = employeeName;
        this.employeeContract = employeeContract;
        this.availableTime = availableTime;//the available time is a 7x24 array which has 1 when the employee available
        this.assignedTime = assignedTime;//the time that the user wants this employee to work in a week
        if (employeeContract.equalsIgnoreCase("part-time")) {
            this.hours = 20;//available time for part-time employees in canada is 20 hours;
        } else if (employeeContract.equalsIgnoreCase("full-time")) {
            this.hours = 40;//available time for full-time employees in canada is 40 hours
        }
        this.overtime= overtime.equalsIgnoreCase("yes");
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
    public int[][] getAvailableTime() {
        return availableTime;
    }
    public boolean isOvertime() {
        return overtime;
    }

    public void setOvertime(boolean overtime) {
        this.overtime = overtime;
    }
    public void setAvailableTime(int[][] availableTime) {
        this.availableTime = availableTime;//the available time is a 7x24 array which has 1 when the employee available
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
}
