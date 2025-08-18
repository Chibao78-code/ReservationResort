/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;
/**
 *
 * @author zzzdi
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
}
