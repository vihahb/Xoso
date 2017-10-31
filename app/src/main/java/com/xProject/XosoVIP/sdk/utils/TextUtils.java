package com.xProject.XosoVIP.sdk.utils;

import android.widget.TextView;

import com.appolica.flubber.Flubber;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.regex.Pattern;

public class TextUtils {
    private static TextUtils instance;

    public synchronized static TextUtils getInstance() {
        if (instance == null)
            instance = new TextUtils();
        return instance;
    }

    public static boolean isInteger(String s) {
        return isInteger(s, 2);
    }

    public static boolean isInteger(String s, int radix) {
        if (s.isEmpty()) return false;
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1) return false;
                else continue;
            }
            if (Character.digit(s.charAt(i), radix) < 0) return false;
        }
        return true;
    }

    /* Method to remove duplicates in a sorted array */
    static String removeDupsSorted(String str) {
        int res_ind = 1, ip_ind = 1;

        // Character array for removal of duplicate characters
        char arr[] = str.toCharArray();

        /* In place removal of duplicate characters*/
        while (ip_ind != arr.length) {
            if (arr[ip_ind] != arr[ip_ind - 1]) {
                arr[res_ind] = arr[ip_ind];
                res_ind++;
            }
            ip_ind++;

        }

        str = new String(arr);
        return str.substring(0, res_ind);
    }

    /* Method removes duplicate characters from the string
       This function work in-place and fills null characters
       in the extra space left */
    public static String removeDups(String str) {
        // Sort the character array
        char temp[] = str.toCharArray();
        Arrays.sort(temp);
        str = new String(temp);

        // Remove duplicates from sorted
        return removeDupsSorted(str);
    }

    /**
     * Bỏ dấu tiếng việt
     *
     * @param s tiếng Việt có dấu
     * @return tiếng Việt không dấu
     */
    public String unicodeToKoDau(String s) {
        if (android.text.TextUtils.isEmpty(s)) {
            return "";
        }

        String nfdNormalizedString = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

        return pattern.matcher(nfdNormalizedString).replaceAll("").replaceAll("\u0111", "d").replaceAll("\u0110", "D");
    }

    public String unicodeToKoDauLowerCase(String text) {
        if (android.text.TextUtils.isEmpty(text)) {
            return "";
        }

        String nfdNormalizedString = Normalizer.normalize(text.toLowerCase(), Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

        return pattern.matcher(nfdNormalizedString).replaceAll("").replaceAll("\u0111", "d").replaceAll("\u0110", "D");
    }

    public String getTypeFile(String filePatch) {
        String[] spilt = filePatch.split("\\.");
        String sss = spilt[spilt.length - 1];
        return "." + spilt[spilt.length - 1];
    }

//    public static void test(float v) {
//        String formatted = NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(v);
//    }
//
////    public void textFixerUpper(EditText et, String value) {
////        et.removeTextChangedListener(et);
////    }
//
//    public static void main(String[] agr) {
////        float value = 44434234.444f;
////        if (value == (int) value) {
////            System.out.print("Integer");
////        } else
////            System.out.print("Integer not");
//        test(242342343.5f);
//
//    }

    public void setAnimationTextView(TextView textView) {
        Flubber.with()
                .animation(Flubber.AnimationPreset.FADE_IN)
                .repeatCount(4)
                .duration(500)
                .createFor(textView)
                .start();
    }
}