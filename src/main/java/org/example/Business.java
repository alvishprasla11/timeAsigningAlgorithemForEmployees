package org.example;

public class Business {
    private int [][] hoursOfOperation = new int[7][24];
    final private String businessName;
    private int [][] numberOfEmployeesInAnHour;
    private int totalSlots=0;
    public Business(int [][] HoursOfOperation,String businessName,int [][]numberOfEmployeesInAnHour){
        this.hoursOfOperation=HoursOfOperation;
        this.businessName=businessName;
        this.numberOfEmployeesInAnHour=numberOfEmployeesInAnHour;
        for(int i=0;i<numberOfEmployeesInAnHour.length;i++){
            for(int j=0;j<numberOfEmployeesInAnHour[i].length;j++){
                if (numberOfEmployeesInAnHour[i][j]!=0){
                    this.totalSlots=+numberOfEmployeesInAnHour[i][j];
                }
            }
        }

    }

    public int[][] getHoursOfOperation() {
        return hoursOfOperation;
    }

    public String getBusinessName() {
        return businessName;
    }

    public int[][] getNumberOfEmployeesInAnHour() {
        return numberOfEmployeesInAnHour;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public void setHoursOfOperation(int[][]hoursOfOpration) {
        this.hoursOfOperation = hoursOfOpration;
    }

    public void setNumberOfEmployeesInAnHour(int[][] numberOfEmployeesInAnHour) {
        this.numberOfEmployeesInAnHour = numberOfEmployeesInAnHour;
        for(int i=0;i<numberOfEmployeesInAnHour.length;i++){
            for(int j=0;j<numberOfEmployeesInAnHour[i].length;j++){
                if (numberOfEmployeesInAnHour[i][j]!=0){
                    this.totalSlots=+hoursOfOperation[i][j];
                }
            }
        }
    }
}
//complete
