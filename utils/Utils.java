  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

/**
 *
 */
public class Utils {
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    public static String NATIONAL_ID_VALID = "^\\d{12}$";
    public static String NAME_VALID = "^.{2,25}$";
    public static String PHONE_VALID = "^[0]\\d{9}$";
    public static final int MIN=1;
    public static final int MAX=200;
    
    public static String generateString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return LocalDateTime.now().format(formatter);
    }
    
    public static String getString(String welcome) {
        boolean check = true;
        String result = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println("Input text!!!");
            } else {
                check = false;
            }
        } while (check);
        return result;
    }
    
    public static String getString(String welcome, String format) {
        String id;
        boolean match;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print(welcome);
            id = sc.nextLine().trim();
            match = id.matches(format);
            if (id.length() == 0 || match == false) {
                System.out.println("Wrong format!");
            } else {
                return id;
            }
        }
    }

    public static String updateString(String welcome, String oldData) {
        String result = oldData;
        Scanner sc = new Scanner(System.in);
        System.out.print(welcome);
        String tmp = sc.nextLine();
        if (!tmp.isEmpty()) {
            result = tmp;
        }
        return result;
    }
    
    public static String updateString(String welcome, String oldData, String regex) {
        String result = oldData;
        System.out.printf(welcome);
        Scanner sc = new Scanner(System.in);
        String tmp = sc.nextLine();
        if (!tmp.isEmpty()) {
            boolean check = tmp.matches(regex);
            if (!check) {
                tmp = Utils.getString(welcome, regex);
            }
            result = tmp;
        }
        return result;
    }

    public static int getInt(String welcome, int min, int max) {
        boolean check = true;
        int number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }

    public static int updateInt(String welcome, int min, int max, int oldData) {
        boolean check = true;
        int number = oldData;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    number = Integer.parseInt(tmp);
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }

    public static boolean confirmYesNo(String welcome) {
        boolean result = false;
        String confirm = Utils.getString(welcome, "^[Y|y|N|n]$");
        if ("Y".equalsIgnoreCase(confirm)) {
            result = true;
        }
        return result;
    }

    public static double getDouble(String welcome, double min, double max) {
        boolean check = true;
        double number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                number = Double.parseDouble(sc.nextLine());
                check = false;
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }
    public static double updateDouble(String welcome, double min, double max, double oldData) {
        boolean check = true;
        double number = oldData;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    number = Double.parseDouble(tmp);
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }
    
    public static Date updateDate(String inputMsg, Date oldDate) {
        boolean check = true;
        Date resultDate = oldDate;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(inputMsg);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    sdf.setLenient(false);
                    resultDate = sdf.parse(tmp);
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check == true);
        return resultDate;
    }

    public static Date getDate(String inputMsg) {
        String data;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print(inputMsg);
            data = sc.nextLine().trim();
            try {
                sdf.setLenient(false);
                return sdf.parse(data);
            } catch (Exception e) {
                System.out.println("Invalid Date!");
            }
        }
    }
}
