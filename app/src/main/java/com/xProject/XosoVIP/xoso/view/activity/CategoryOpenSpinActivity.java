package com.xProject.XosoVIP.xoso.view.activity;

import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xProject.XosoVIP.R;

import java.util.Calendar;

public class CategoryOpenSpinActivity extends BasicActivity {

    private TextView tv_north, tv_central, tv_south;

    private TextView tv_title_2, tv_n_2, tv_c_2, tv_s_2;
    private TextView tv_title_3, tv_n_3, tv_c_3, tv_s_3;
    private TextView tv_title_4, tv_n_4, tv_c_4, tv_s_4;
    private TextView tv_title_5, tv_n_5, tv_c_5, tv_s_5;
    private TextView tv_title_6, tv_n_6, tv_c_6, tv_s_6;
    private TextView tv_title_7, tv_n_7, tv_c_7, tv_s_7;
    private TextView tv_title_cn, tv_n_cn, tv_c_cn, tv_s_cn;
    private LinearLayout ln_2, ln_3, ln_4, ln_5, ln_6, ln_7, ln_cn;

    int dayOfWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.mipmap.background_home);
        setContentView(R.layout.activity_category_open_spin);
        initToolbar(R.id.toolbar, "Lịch mở thưởng");
        initCheckDay();
        initView();
    }

    private void initCheckDay() {
        Calendar calendar = Calendar.getInstance();
         dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
    }

    private void initView() {
        initLn();
        initTitle();
        initHai();
        initBa();
        initBon();
        initNam();
        initSau();
        initBay();
        initCN();
        initSelectToday();
    }

    private void initSelectToday() {
        switch (dayOfWeek){
            case 1:
                ln_cn.setBackgroundColor(getResources().getColor(R.color.table_x));
                String result_cn ="Chủ nhật\n<br><font color='#eb2227'>(Hôm nay)</font>";
                tv_title_cn.setText(Html.fromHtml(result_cn));
                break;
            case 2:
                ln_2.setBackgroundColor(getResources().getColor(R.color.table_x));
                String result_2 ="Hai\n<br><font color='#eb2227'>(Hôm nay)</font>";
                tv_title_2.setText(Html.fromHtml(result_2));
                break;
            case 3:
                ln_3.setBackgroundColor(getResources().getColor(R.color.table_x));
                String result_3 ="Ba\n<br><font color='#eb2227'>(Hôm nay)</font>";
                tv_title_3.setText(Html.fromHtml(result_3));
                break;
            case 4:
                ln_4.setBackgroundColor(getResources().getColor(R.color.table_x));
                String result_4 ="Bốn\n<br><font color='#eb2227'>(Hôm nay)</font>";
                tv_title_4.setText(Html.fromHtml(result_4));
                break;
            case 5:
                ln_5.setBackgroundColor(getResources().getColor(R.color.table_x));
                String result_5 ="Năm\n<br><font color='#eb2227'>(Hôm nay)</font>";
                tv_title_5.setText(Html.fromHtml(result_5));
                break;
            case 6:
                ln_6.setBackgroundColor(getResources().getColor(R.color.table_x));
                String result_6 ="Sáu\n<br><font color='#eb2227'>(Hôm nay)</font>";
                tv_title_6.setText(Html.fromHtml(result_6));
                break;
            case 7:
                ln_7.setBackgroundColor(getResources().getColor(R.color.table_x));
                String result_7 ="Bảy\n<br><font color='#eb2227'>(Hôm nay)</font>";
                tv_title_7.setText(Html.fromHtml(result_7));
                break;

        }
    }

    private void initLn() {
        ln_2 = findLinearLayout(R.id.ln2_2);
        ln_3 = findLinearLayout(R.id.ln_3);
        ln_4 = findLinearLayout(R.id.ln_4);
        ln_5 = findLinearLayout(R.id.ln_5);
        ln_6 = findLinearLayout(R.id.ln_6);
        ln_7 = findLinearLayout(R.id.ln_7);
        ln_cn = findLinearLayout(R.id.ln_cn);
    }

    private void initTitle(){
        tv_north = findTextView(R.id.title_north);
        tv_central = findTextView(R.id.title_central);
        tv_south = findTextView(R.id.title_south);
        String north = "MIỀN BẮC \n<font color='#bf000000'>  (18:15)  </font>";
        String central = "MIỀN TRUNG \n<font color='#bf000000'>  (17:15)  </font>";
        String south = "MIỀN NAM \n<font color='#bf000000'>  (16:15)  </font>";
        tv_north.setText(Html.fromHtml(north));
        tv_central.setText(Html.fromHtml(central));
        tv_south.setText(Html.fromHtml(south));
    }

    private void initHai() {
        tv_title_2 =findTextView(R.id.tv_title_2);
        tv_n_2 = findTextView(R.id.tv_n_2);
        tv_c_2 = findTextView(R.id.tv_c_2);
        tv_s_2 = findTextView(R.id.tv_s_2);

        tv_title_2.setText("Hai");
        String result_n_2 = "* Truyền thống \n\n* Điện toán 123 \n\n* Thần tài";
        tv_n_2.setText(result_n_2);

        String result_c_2 = "* Phú Yên \n\n* Thừa Thiên Huế";
        tv_c_2.setText(result_c_2);

        String result_s_2 = "* Đồng Tháp \n\n* Hồ Chí Minh \n\n* Cà Mau";
        tv_s_2.setText(result_s_2);
    }

    private void initBa() {
        tv_title_3 =findTextView(R.id.tv_title_3);
        tv_n_3 = findTextView(R.id.tv_n_3);
        tv_c_3 = findTextView(R.id.tv_c_3);
        tv_s_3 = findTextView(R.id.tv_s_3);

        tv_title_3.setText("Ba");
        String result_n_3 = "* Truyền thống \n\n* Điện toán 123 \n\n* Thần tài";
        tv_n_3.setText(result_n_3);

        String result_c_3 = "* Đắc Lắc \n\n* Thừa Thiên Huế";
        tv_c_3.setText(result_c_3);

        String result_s_3 = "* Bạc Liêu \n\n* Bến Tre \n\n* Vũng Tàu";
        tv_s_3.setText(result_s_3);
    }

    private void initBon() {
        tv_title_4 =findTextView(R.id.tv_title_4);
        tv_n_4 = findTextView(R.id.tv_n_4);
        tv_c_4 = findTextView(R.id.tv_c_4);
        tv_s_4 = findTextView(R.id.tv_s_4);

        tv_title_4.setText("Bốn");
        String result_n_4 = "* Truyền thống \n\n* Điện toán 123 \n\n* Thần tài \n\n* Điện toán 6x36";
        tv_n_4.setText(result_n_4);

        String result_c_4 = "* Đà Nẵng \n\n* Khánh Hoà";
        tv_c_4.setText(result_c_4);

        String result_s_4 = "* Cần Thơ \n\n* Đồng Nai \n\n* Sóc Trăng";
        tv_s_4.setText(result_s_4);
    }

    private void initNam() {
        tv_title_5 =findTextView(R.id.tv_title_5);
        tv_n_5 = findTextView(R.id.tv_n_5);
        tv_c_5 = findTextView(R.id.tv_c_5);
        tv_s_5 = findTextView(R.id.tv_s_5);

        tv_title_5.setText("Năm");
        String result_n_5 = "* Truyền thống \n\n* Điện toán 123 \n\n* Thần tài";
        tv_n_5.setText(result_n_5);

        String result_c_5 = "* Bình Định \n\n* Quảng Bình \n\n* Quảng Trị";
        tv_c_5.setText(result_c_5);

        String result_s_5 = "* An Giang \n\n* Bình Thuận \n\n* Tây Ninh";
        tv_s_5.setText(result_s_5);
    }

    private void initSau() {
        tv_title_6 =findTextView(R.id.tv_title_6);
        tv_n_6 = findTextView(R.id.tv_n_6);
        tv_c_6 = findTextView(R.id.tv_c_6);
        tv_s_6 = findTextView(R.id.tv_s_6);

        tv_title_6.setText("Sáu");
        String result_n_6 = "* Truyền thống \n\n* Điện toán 123 \n\n* Thần tài";
        tv_n_6.setText(result_n_6);

        String result_c_6 = "* Gia Lai \n\n* Ninh Thuận";
        tv_c_6.setText(result_c_6);

        String result_s_6 = "* Bình Dương \n\n* Vĩnh Long \n\n* Trà Vinh";
        tv_s_6.setText(result_s_6);
    }

    private void initBay() {
        tv_title_7 =findTextView(R.id.tv_title_7);
        tv_n_7 = findTextView(R.id.tv_n_7);
        tv_c_7 = findTextView(R.id.tv_c_7);
        tv_s_7 = findTextView(R.id.tv_s_7);

        tv_title_7.setText("Bảy");
        String result_n_7 = "* Truyền thống \n\n* Điện toán 123 \n\n* Thần tài \n\n* Điện toán 6x36";
        tv_n_7.setText(result_n_7);

        String result_c_7 = "* Đắc Nông \n\n* Quảng Ngãi \n\n* Đà Nẵng";
        tv_c_7.setText(result_c_7);

        String result_s_7 = "* Bình Phước \n\n* Hậu Giang \n\n* Long An \n\n* Hồ Chí Minh";
        tv_s_7.setText(result_s_7);
    }

    private void initCN() {
        tv_title_cn =findTextView(R.id.tv_title_cn);
        tv_n_cn = findTextView(R.id.tv_n_cn);
        tv_c_cn = findTextView(R.id.tv_c_cn);
        tv_s_cn = findTextView(R.id.tv_s_cn);

        tv_title_cn.setText("Chủ Nhật");
        String result_n_cn = "* Truyền thống \n\n* Điện toán 123 \n\n* Thần tài";
        tv_n_cn.setText(result_n_cn);

        String result_c_cn = "* Khánh Hoà \n\n* Kon Tum";
        tv_c_cn.setText(result_c_cn);

        String result_s_cn = "* Đà Lạt \n\n* Kiên Giang \n\n* Tiền Giang";
        tv_s_cn.setText(result_s_cn);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
