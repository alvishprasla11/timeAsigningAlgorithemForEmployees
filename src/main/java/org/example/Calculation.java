package org.example;

public class Calculation {
    public static String[][] alotTime(Employee [] employees,Business business) {

        // this makes it so that each employee gets job continus untill it raaches 8 hours which is wrong way to put this as this would also make it so that employye
        //gets employeed twice in a day and next day gets employeeed when he gets free time this wont work

        // needs fixing

        // what we can do is check at what time what employees are free and according to that
        // we can alot them time add

        String[][] alotedTime= new String[employees.length][8];
        for (int i = 0; i < employees.length; i++) {
            alotedTime[i][0] = employees[i].getEmployeeName();
        }
        return alotedTime;
    }

    public static Employee [] overTime(int[] extra_hours,Employee [] employees) {

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
