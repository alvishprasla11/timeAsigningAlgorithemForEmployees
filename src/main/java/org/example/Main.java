package org.example;

import org.example.Calculation;
import org.example.Converters;
import org.example.Employee;

import java.util.Arrays;
import java.util.Scanner;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    //employee management app

    // further possible improvements adding front end

    //adding sql support so that data of employees are not needed to be entered every time

    //few assumptions are made in the code
    //1. the business operates at a continues time i.e. once it starts it stops only when it ends with no break in between
    //2. the employees can only give the only two best timeslot of morning and evening with just one break of (preferably at least and) at most 8 hours
    //3. the employee names should be given in decreasing  order for priority

    final static public String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    public static void main(String[] args) {
        //start
        System.out.println("Hello! welcome to employee time management software!/n");

        //business name
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the business name: ");
        String businessName = sc.nextLine();

        //the working hours for the business
        String[] businessHoursString = new String[7];
        for (int i = 0; i < businessHoursString.length; i++) {
            System.out.println("Please enter the business working hours for " + days[i] + "\n\nin 24 hours format(separating the start and end time by a - )\nif it is same as the last day then enter 'L'");
            String businessHoursDay = sc.nextLine();
            noLast(businessHoursDay, businessHoursString, i, sc);
        }

        //converts business hours string to 1s and 0s array for 7 days
        int[][] businessHours = Converters.hoursConverter(businessHoursString,1);
        System.out.println(Arrays.deepToString(businessHours));

        //maximum possible employees in one hour
        System.out.println("Please enter the maximum number of employees in an hour: ");
        int perHourEmployeeNumber = sc.nextInt();//maximum possible employees in one hour
        sc.nextLine();// to Consume the newline left-over

        //employees per working hour
        int[][][] numberOfEmployeesPerHourAllValues=new int[perHourEmployeeNumber][7][24];
        int [][] numberOfEmployeesPerHourPerNumber = new int[7][24];
        int [][] numberOfEmployeesInAnHour = new int[7][24];
        String[][] perHourString = new String[perHourEmployeeNumber][7];
        for (int i = 0; i < perHourEmployeeNumber; i++) {//some error over here
            System.out.println("\n\n");
            for (int j = 0; j < perHourString[i].length; j++) {
                System.out.println("Please enter the hours when number of employees in an hour are " + (i + 1) + " for "+days[j]+" \n\nin 24 hours format(separating the start and end time by a - )\nif it is same as the last day then enter 'L'");
                String perDayEmployee = sc.nextLine();
                perHourString[i]=noLast(perDayEmployee,perHourString[i],j,sc);

            }
            numberOfEmployeesPerHourPerNumber= Converters.hoursConverter(perHourString[i],i+1);
            numberOfEmployeesPerHourAllValues[i] = numberOfEmployeesPerHourPerNumber;

        }
        numberOfEmployeesInAnHour=Converters.mergeArrays(numberOfEmployeesPerHourAllValues);
        System.out.println(Arrays.deepToString(numberOfEmployeesInAnHour));
        Business Final = new Business(businessHours, businessName,numberOfEmployeesInAnHour);

        //starting with employee details
        // the number of employees employeed
        int employeeTemp=0;
        int employed=0;
        while(employeeTemp==0){
        System.out.println("Please enter the number of employees employed: ");
        employed = sc.nextInt();
        sc.nextLine();
        if (employed<perHourEmployeeNumber){
            System.out.println("the number of employees entered are too less to meet the per hour number\n please enter the value greater then max employees in an hour: ");
            employed = sc.nextInt();
            sc.nextLine();
            }
        else{
            employeeTemp++;}
        }
        Employee[] employees = new Employee[employed];

        //getting employee data
        for (int i = 0; i < employed; i++) {
            int employeeNumber = i + 1;
            System.out.println("Please enter the employee name for employee number " + employeeNumber);
            String employeeName = sc.nextLine();
            System.out.println("Please enter the Employment Type(full-time or part-time) for the same: ");
            String employeeContract = sc.nextLine();
            System.out.println("Please enter the maximum hours you want that employee to work for a week: ");
            int employeeAssignedTime = sc.nextInt();
            String[] availableHoursMorningString = new String[7];
            String[] availableHoursEveningString = new String[7];

            //getting employee available time for each day of the week
            for (int j = 0; j < 7; j++) {
                System.out.println("Please enter the available hours for the employee for" + days[j] + "\n first enter morning available time then enter evening time \n\n in 24 hours format(seprateing the start and end time by a - )\nif it is same as the last day then enter 'L':");
                String availableHoursDayMorning = sc.nextLine();
                String availableHoursDayEvening = sc.nextLine();

                availableHoursMorningString = noLast(availableHoursDayMorning, availableHoursMorningString, j,sc);
                availableHoursEveningString = noLast(availableHoursDayEvening, availableHoursEveningString, j,sc);
            }

            //converting time into 1s and zeros
            int[][] availableHoursMorning = Converters.hoursConverter(availableHoursMorningString,1);
            int[][] availableHoursEvening = Converters.hoursConverter(availableHoursEveningString,1);

            //adding the employee to employees
            employees[i] = new Employee(employeeNumber, employeeName, employeeContract, availableHoursMorning,availableHoursEvening,employeeAssignedTime);
            ;
        }
        //getting the final alloted time array from calculaitons
        String [][] CSV= Calculation.alotTime(employees,Final);
        //createing a CSV file to convert array to csv.
        System.out.println(Arrays.deepToString(CSV));
        //CSV_Creator(CSV);
    }
    public static String[] noLast(String perDayEmployee, String[] perHourString, int j, Scanner sc) {
            //checks and alots the last entered time to the input and if no last time then it accepts input unless right is entered
        if (perDayEmployee.equalsIgnoreCase("L")) {
            if (j > 0) {
                perHourString[j] = perHourString[j - 1];  // Copy the previous day's value
            } else {
                while(!perDayEmployee.contains("-")) {
                    System.out.println("There is no last to choose from, enter the values for the first day seprated by '-' :");
                    perDayEmployee = sc.nextLine();
                    perHourString[j] = perDayEmployee;
                }
            }
        } else {
            perHourString[j] = perDayEmployee;
        }
        // Ensure the input is not empty
        while (perHourString[j] == null || perHourString[j].trim().isEmpty()) {
            System.out.println("Input cannot be empty. Please enter valid hours:");
            perHourString[j] = sc.nextLine();
        }
        return perHourString;
    }
    public static void CSV_Creator(String[][] data){
        try (FileWriter csvWriter = new FileWriter("output.csv")) {
            for (String[] row : data) {
                csvWriter.append(String.join(",", row));
                csvWriter.append("\n");
            }
            System.out.println("CSV file created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//complete