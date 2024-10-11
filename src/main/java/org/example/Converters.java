package org.example;

public class Converters {
    public static String Converter(int []time){
        int start=0;
        int end=0;
        for(int i=0;i<time.length;i++){
            if (time[i]==1){
                start=i+1;
            }
        }
        if (start==0){
            return "OFF";
        }
        return timeConvert(start)+"-"+timeConvert(end);
    }
    public static String timeConvert(int i){
        if (i==1){return "1 AM";}
        else if (i==2){return "2 AM";}
        else if (i==3){return "3 AM";}
        else if (i==4){return "4 AM";}
        else if (i==5){return "5 AM";}
        else if (i==6){return "6 AM";}
        else if (i==7){return "7 AM";}
        else if (i==8){return "8 AM";}
        else if (i==9){return "9 AM";}
        else if (i==10){return "10 AM";}
        else if (i==11){return "11 AM";}
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
    public static int[][] hoursConverter(String[] businessHours,int value) {
        int[][] hoursOfOperation = new int[7][24];
        for (int i = 0; i < businessHours.length; i++) {
            int index = businessHours[i].indexOf("-");
            int start = Integer.parseInt(businessHours[i].substring(0, index))-1;
            int end = Integer.parseInt(businessHours[i].substring(index + 1, businessHours[i].length()))-1;
            for (int j = start; j < end; j++) {
                hoursOfOperation[i][j] = value;
            }
        }
        return hoursOfOperation;
    }
}
