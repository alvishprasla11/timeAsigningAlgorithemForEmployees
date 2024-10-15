package org.example;

import java.awt.font.FontRenderContext;

public class Converters {
    public static String Converter(int []time){//for converting array of time consiating 0s and ones  to reaable english value of time
        int start=0;
        int end=0;
        for(int i=0;i<time.length;i++){
            if (time[i]==1){
                start=i+1;
                break;
            }
        }
        for(int i=time.length-1;i>=0;i--){
            if (time[i]==1){
                end=i+1;
                break;
            }
        }
        if (start==0){
            return "OFF";
        }
        else {
            return timeConvert(start) + "-" + timeConvert(end);
        }
    }
    public static String timeConvert(int i){//converting the int value of time into string to be readable by the user
        if (i==1){return "1 AM";}
        else if (i==2){return "2AM";}
        else if (i==3){return "3AM";}
        else if (i==4){return "4AM";}
        else if (i==5){return "5AM";}
        else if (i==6){return "6AM";}
        else if (i==7){return "7AM";}
        else if (i==8){return "8AM";}
        else if (i==9){return "9AM";}
        else if (i==10){return "10AM";}
        else if (i==11){return "11AM";}
        else if (i==12){return "12PM";}
        else if (i==13){return "1PM";}
        else if (i==14){return "2PM";}
        else if (i==15){return "3PM";}
        else if (i==16){return "4PM";}
        else if (i==17){return "5PM";}
        else if (i==18){return "6PM";}
        else if (i==19){return "7PM";}
        else if (i==20){return "8PM";}
        else if (i==21){return "9PM";}
        else if (i==22){return "10PM";}
        else if (i==23){return "11PM";}
        else {return "12PM";}
    }
    public static int[][] hoursConverter(String[] businessHours,int value) {//converts string value for example 6-7 into 1s and 0 to be stored in an array
        int[][] hoursOfOperation = new int[7][24];
        for (int i = 0; i < businessHours.length; i++) {
            int index = businessHours[i].indexOf("-");
            int start = Integer.parseInt(businessHours[i].substring(0, index))-1;
            int end = Integer.parseInt(businessHours[i].substring(index + 1, businessHours[i].length()));
            for (int j = 0; j < start; j++) {
                hoursOfOperation[i][j] = -1;//so that we know when does it really start
            }
            for (int j = start; j < end; j++) {
                hoursOfOperation[i][j] = value;
            }
            for (int j = end+1; j > 25; j++) {
                hoursOfOperation[i][j] = 0;
            }
        }
        return hoursOfOperation;
    }
    public static int[][] mergeArrays(int[][][] Array) {//merges a 3d array to be a 2d array
        int[][]result = new int[7][24];
        for (int i = 0; i < Array.length; i++) {
            for (int j = 0; j < Array[i].length; j++) {
                for (int k = 0; k < Array[i][j].length; k++) {
                    if (Array[i][j][k] != -1 && Array[i][j][k] != 0){
                        result[j][k] = Array[i][j][k];
                    }
                }
            }
        }
        return result;
    }
}
