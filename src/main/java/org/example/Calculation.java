package org.example;

public class Calculation {
    public static String[][] alotTime(Employee [] employees,Business business) {

        // this makes it so that each employee gets job continus untill it raaches 8 hours which is wrong way to put this as this would also make it so that employye
        //gets employeed twice in a day and next day gets employeeed when he gets free time this wont work

        // needs fixing


        int [] extra_hours=hours(employees,business);
        int maxWorkingHours=0;
        if (extra_hours[0]!=0&&extra_hours[1]!=0&&extra_hours[2]!=0) {
            employees=overTime(extra_hours,employees);
        }
        String[][] alotedTime= new String[employees.length][8];
        for (int i = 0; i < employees.length; i++) {
            alotedTime[i][0]=employees[i].getEmployeeName();

            int maxHoursForEmployees = employees[i].getAssignedTime();
            int[][] businessHours = business.getHoursOfOperation();
            int[][] numberOfEmployeesInAnHour = business.getNumberOfEmployeesInAnHour();
            for (int j = 1; j < 8; j++) {
                int []time= new int[24];
                for(int k=0;k<24;k++) { //not correct from here
                    if(maxHoursForEmployees!=0) {
                        if (businessHours[j][k] == 1 && numberOfEmployeesInAnHour[j][k] != 0) {
                            time[k]=1;
                            employees[i].setAssignedTime(employees[i].getAssignedTime()-1);
                            maxWorkingHours++;
                            numberOfEmployeesInAnHour[j][k]--;
                        }
                        if(maxWorkingHours==8){
                            break;
                        }
                    }
                    else{
                        break;
                    }
                }
                alotedTime[i][j] = Converters.Converter(time);
            }
        }
        return alotedTime;
    }

    public static Employee [] overTime(int[] extra_hours,Employee [] employees) {//not perfect!!
        int temp=0;
        while(extra_hours[2]!=0 && extra_hours[1]!=0) {
            for (Employee employee : employees) {
                if (!employee.isOvertime() && extra_hours[0] + employee.getAssignedTime() <= employee.getHours()) {
                    if (extra_hours[0] != 0) {
                        employee.setAssignedTime(employee.getAssignedTime() + extra_hours[0]);
                        extra_hours[2]--;
                    } else if (extra_hours[1] != 0) {
                        employee.setAssignedTime(employee.getAssignedTime() + 1);
                        extra_hours[1]--;
                    }
                } else if (employee.isOvertime()) {
                    if (extra_hours[0] != 0) {
                        employee.setAssignedTime(employee.getAssignedTime() + extra_hours[0]);
                        extra_hours[2]--;
                        temp++;
                    } else if (extra_hours[1] != 0) {
                        employee.setAssignedTime(employee.getAssignedTime() + 1);
                        extra_hours[1]--;
                        temp++;
                    }
                }
            }
            int totalExtraHours;
            if (extra_hours[0] != 0) {
                totalExtraHours=extra_hours[0]*extra_hours[2];
            }
            else {
                totalExtraHours = extra_hours[1];
            }
            extra_hours = hoursContinued(totalExtraHours,temp);
        }
        return employees;
    }

    public static int[] hours(Employee [] employees,Business business) {
        int numberOfMaxHours=0;
        for (Employee employee : employees) {
            numberOfMaxHours=+employee.getHours();
        }
        int maxPossibleHours=business.getTotalSlots();
        int temp=maxPossibleHours-numberOfMaxHours;
        if (temp==0) {
            return hoursContinued(temp,employees.length);
        }
        else {
            return new int[]{0,0,0};
        }
    }
    public static int[] hoursContinued(int temp,int length) {
        int extra=0;
        int itarator=0;
        if(temp>=length) {
            if (temp % length == 0 ) {
                temp = temp / length;
                itarator = length;
            } else if (temp % length != 0) {
                temp = temp / length;
                extra = temp % length;
                itarator = length;
            }
        }
        else {
            extra=temp;
            temp=0;// to distinct that extra time is less then employees
            itarator=1;//to allow the use of while loop
        }
        return new int[]{temp,extra,itarator};
    }
}
