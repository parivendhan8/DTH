package com.example.dth_project.helper;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import com.example.dth_project.EmailVerify;
import com.example.dth_project.Login;
import com.example.dth_project.RegisterActivity;

public class Functions {
    //Main URL192.168.137.1
    public static String MAIN_URL="http://dthsoft.in/api/mobile/";
//    private static String MAIN_URL = "http://192.168.137.1/androidlogin1/";
    // Login URL
//    public static String LOGIN_URL ="http://dthsoft.in/api/mobile/?type=1&username=943815&pass=yjd4z3";
//   public static String LOGIN_URL="http://dthsoft.in/api/mobile/?type=1.php/";
    public static String LOGIN_URL ="http://dthsoft.in/api/mobile/";
//    public static String LOGIN_URL="http://dthsoft.in/api/";
//        public static String LOGIN_URL ="http://megaasoft.com/android_login_api/login.php";
//    // Register URL
//    public static String REGISTER_URL = "http://megaasoft.com/android_login_api/register.php";
    // OTP Verification
    public static String OTP_VERIFY_URL = "http://192.168.137.1/androidlogin1/verification.php";
    // Forgot Password
    public static String RESET_PASS_URL = "http://192.168.137.1/androidlogin1/reset-password.php";
//    public static String URL_LOGIN = "http://megaasoft.com/android_login_api/login.php";
//    public static String URL_MENU = "http://megaasoft.com/android_login_api/login.php";
//    // Server user login url
//    public static String URL_AMOUNT = "http://megaasoft.com/chit/files/receipts/msave.php";
//    // Server user register url
//    public static String URL_REGISTER = "http://megaasoft.com/android_login_api/register.php";

    public static void hideSoftKeyboard(Login loginActivity) {
        Activity activity = new Activity();
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
//
//    public static boolean isValidEmailAddress(String email) {
//            String ePattern;
//        ePattern = "a-zA-Z0-9.!#";
//            java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
//            java.util.regex.Matcher m = p.matcher(email);
//            return m.matches();
//
//    }
    public static boolean isValidEmailAddress(String email) {
//        String ePattern="01234567890";[a-zA-z0-9]*
        String ePattern="[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]*";
//        String ePattern = "[^a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public static void hideSoftKeyboard(RegisterActivity registerActivity) {
        Activity activity = new Activity();
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static void hideSoftKeyboard(EmailVerify emailVerify) {
        Activity activity = new Activity();
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);

    }

    public boolean logoutUser(Context context){
        DatabaseHandler db = new DatabaseHandler(context);
        db.resetTables();
        return true;
    }
}
