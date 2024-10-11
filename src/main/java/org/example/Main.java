import java.util.Arrays;
import java.util.Scanner;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    final static public String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    public static void main(String[] args) {
        System.out.println("Hello! welcome to employee time management software!/n");
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the business name: ");
        String businessName = sc.nextLine();
        String[] businessHoursString = new String[7];
        for (int i = 0; i < businessHoursString.length; i++) {
            System.out.println("Please enter the business working hours for " + days[i] + "\n\nin 24 hours format(separating the start and end time by a - )\nif it is same as the last day then enter 'last'");
            String businessHoursDay = sc.nextLine();
            businessHoursString = noLast(businessHoursDay, businessHoursString, i,sc);
        }
        int[][] businessHours = Converters.hoursConverter(businessHoursString,1);
        System.out.println(Arrays.deepToString(businessHours));
        System.out.println("Please enter the maximum number of employees in an hour: ");
        int perHourEmployeeNumber = sc.nextInt();
        sc.nextLine();// to Consume the newline left-over
        int[][] numberOfEmployeesInAnHour=new int[7][24];
        String[][] perHourString = new String[perHourEmployeeNumber][7];
        for (int i = 0; i < perHourEmployeeNumber; i++) {//some error over here
            for (int j = 0; j < perHourString.length; j++) {
                System.out.println("Please enter the hours when number of employees in an hour are " + (i + 1) + " for "+days[j]+" \n\nin 24 hours format(separating the start and end time by a - )\nif it is same as the last day then enter 'last'");
                String perDayEmployee = sc.nextLine();
                perHourString[i]=noLast(perDayEmployee,perHourString[i],j,sc);

            }
            numberOfEmployeesInAnHour = Converters.hoursConverter(perHourString[i],i+1);
        }
        Business Final = new Business(businessHours, businessName,numberOfEmployeesInAnHour);
        System.out.println("Please enter the number of employees employed: ");
        int employed = sc.nextInt();
        Employee[] employees = new Employee[employed];
        for (int i = 0; i < employed; i++) {
            int employeeNumber = i + 1;
            System.out.println("Please enter the employee name for employee number " + employeeNumber);
            String employeeName = sc.nextLine();
            System.out.println("Please enter the Employment Type(full-time or part-time) for the same: ");
            String employeeContract = sc.nextLine();
            System.out.println("Please enter if the employee can work overtime or not (yes or no) : ");
            String overtime = sc.nextLine();
            System.out.println("Please enter the maximum hours you want that employee to work for a week: ");
            int employeeAsignedTime = sc.nextInt();
            String[] availableHoursString = new String[7];
            for (int j = 0; j < 7; j++) {
                System.out.println("Please enter the available hours for the employee for" + days[j] + " /n/n in 24 hours format(seprateing the start and end time by a - ):");
                String availableHoursDay = sc.nextLine();
                availableHoursString[j] = availableHoursDay;
            }
            int[][] availableHours = Converters.hoursConverter(availableHoursString,1);
            Employee employee = new Employee(employeeNumber, employeeName, employeeContract, availableHours,employeeAsignedTime,overtime);
            employees[i] = employee;
        }
        String [][] CSV= Calculation.alotTime(employees,Final);
        CSV_Creator(CSV);
    }
    public static String[] noLast(String perDayEmployee, String[] perHourString, int j, Scanner sc) {

        if (perDayEmployee.equalsIgnoreCase("last")) {
            if (j > 0) {
                perHourString[j] = perHourString[j - 1];  // Copy the previous day's value
            } else {
                System.out.println("There is no last to choose from, enter the values for the first day:");
                perDayEmployee = sc.nextLine();
                perHourString[j] = perDayEmployee;
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