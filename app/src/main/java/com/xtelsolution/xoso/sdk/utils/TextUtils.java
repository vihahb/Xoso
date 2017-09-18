package com.xtelsolution.xoso.sdk.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class TextUtils {
    private static TextUtils instance;

    public synchronized static TextUtils getInstance() {
        if (instance == null)
            instance = new TextUtils();
        return instance;
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
}