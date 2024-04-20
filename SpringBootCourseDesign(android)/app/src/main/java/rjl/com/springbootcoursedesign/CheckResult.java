package rjl.com.springbootcoursedesign;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class CheckResult extends AppCompatActivity {


    private String result = "";
    private  String check_result = "";
    EditText check_sno;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            if (msg.what==0x401){

                if (msg.obj.toString().equals("error")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CheckResult.this);
                    builder.setTitle("操作结果");
                    builder.setMessage("抱歉，添加失败！");
                    builder.setPositiveButton("确定", null);
                    builder.show();
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CheckResult.this);
                    builder.setTitle("操作结果");
                    builder.setMessage("核酸检测数据已保存！");
                    builder.setPositiveButton("确定", null);
                    builder.show();
                    check_sno.setText("");
                }
            }


            return true;
        }
    });






    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check);

        check_sno = findViewById(R.id.check_id);
        final RadioButton yin = findViewById(R.id.yin);
        final RadioButton yang = findViewById(R.id.yang);
        final Button submit = findViewById(R.id.check_submit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                        final String sno = check_sno.getText().toString();

                        if (yin.isChecked()){
                            check_result = yin.getText().toString();
                        }else {
                            check_result = yang.getText().toString();
                        }

                        if (sno.equals("")|| check_result.equals("")){
                            Toast.makeText(CheckResult.this, "数据不能为空！", Toast.LENGTH_SHORT).show();
                        }

                        else {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {

                                    Intent intent = getIntent();
                                    String no =intent.getStringExtra("wno");
                                    Log.i("=============","已发送添加核酸检测信息请求！"+no);
                                    String target = "http://172.18.33.64:8080/check/addCheck";
                                    Map<String,String> params = new HashMap<>();
                                    params.put("wno",no);
                                    params.put("sno",sno);
                                    params.put("result",check_result);
                                    result = ConnectionUtils.postRequest(target,params);
                                    Log.i("=======================",result);
                                    Message msg = new Message();
                                    msg.what = 0x401;
                                    msg.obj = result;
                                    handler.sendMessage(msg);
                                }
                            }).start();

                        }


            }
        });




    }





}
