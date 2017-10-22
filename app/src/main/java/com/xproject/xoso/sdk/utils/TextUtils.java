package com.xproject.xoso.sdk.utils;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Handler;
import android.widget.TextView;

import com.appolica.flubber.Flubber;
import com.appolica.flubber.annotations.RepeatMode;
import com.xproject.xoso.xoso.ProjectApplication;
import com.xtelsolution.xoso.R;

import java.text.Normalizer;
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

    public void setAnimationTextView(TextView textView){
        Flubber.with()
                .animation(Flubber.AnimationPreset.FADE_IN)
                .interpolator(Flubber.Curve.BZR_EASE_IN)
                .duration(500)
                .createFor(textView)
                .start();
    }
}