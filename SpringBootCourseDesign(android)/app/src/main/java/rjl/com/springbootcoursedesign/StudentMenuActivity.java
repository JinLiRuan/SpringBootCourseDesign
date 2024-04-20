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
import android.widget.Toast;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;


public class StudentMenuActivity extends AppCompatActivity {

    private String result="";
    private String no="";
    private String preData="";

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {



            if (msg.what==0x117){

                JSONObject jsonObject2 = null;
                if (msg.obj.toString().equals("error")){
                    Toast.makeText(StudentMenuActivity.this,"抱歉，目前无您的详细信息！",Toast.LENGTH_LONG);
                }else {

                    try{
//                        jsonObject = new JSONObject(msg.obj.toString());

                        Intent intent = getIntent();
                        String user = intent.getStringExtra("user");
                        jsonObject2 = new JSONObject(user);

                        AlertDialog.Builder builder = new AlertDialog.Builder(StudentMenuActivity.this);
                        builder.setTitle("个人信息");
                        StringBuilder sb = new StringBuilder();
                        sb.append("姓名：" + jsonObject2.optString("sname") + "\n");
                        sb.append("学号：" + jsonObject2.optString("sno")  + "\n");
                        sb.append("性别：" + jsonObject2.optString("sex")  + "\n");
                        sb.append("年龄：" + jsonObject2.optString("sage")+ "\n");
                        sb.append("班级：" + jsonObject2.optString("classes") + "\n");
                        builder.setMessage(sb.toString());
                        builder.create().show();

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }


            }else if (msg.what==0x118){

                if (msg.obj.toString().equals("error")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(StudentMenuActivity.this);
                    builder.setTitle("温馨提示");
                    StringBuilder sb = new StringBuilder();
                    sb.append("您还没有提交过数据！"  + "\n");
                    builder.setMessage(sb.toString());
                    builder.create().show();
                }else {
                    JSONObject jsonObject = null;
                    JSONObject jsonObject3 = null;

                    try{
                        jsonObject3 = new JSONObject(msg.obj.toString());
                        jsonObject = new JSONObject(jsonObject3.optString("student"));

                            AlertDialog.Builder builder = new AlertDialog.Builder(StudentMenuActivity.this);
                            builder.setTitle("已提交信息");
                            StringBuilder sb = new StringBuilder();
                            sb.append("姓名：" + jsonObject3.optString("sname") + "\n");
                            sb.append("学号：" + jsonObject.optString("sno")  + "\n");
                            sb.append("健康码：" + jsonObject3.optString("jkm")  + "\n");
                            sb.append("门岗：" + jsonObject3.optString("address")+ "\n");
                            sb.append("状态：" + jsonObject3.optString("status") + "\n");
                            builder.setMessage(sb.toString());
                            builder.create().show();


                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }

            }

            else if (msg.what==0x119){

                if (msg.obj.toString().equals("error")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(StudentMenuActivity.this);
                    builder.setTitle("温馨提示");
                    StringBuilder sb = new StringBuilder();
                    sb.append("暂无您的核酸检测结果"  + "\n");
                    builder.setMessage(sb.toString());
                    builder.create().show();
                }else {
                    JSONObject jsonObject4 = null;
                    JSONObject jsonObject = null;


                    try{
                        jsonObject4 = new JSONObject(msg.obj.toString());
                        jsonObject = new JSONObject(jsonObject4.optString("student"));

                        AlertDialog.Builder builder = new AlertDialog.Builder(StudentMenuActivity.this);
                        builder.setTitle("已提交信息");
                        StringBuilder sb = new StringBuilder();
                        sb.append("姓名：" + jsonObject4.optString("sname") + "\n");
                        sb.append("学号：" + jsonObject.optString("sno")  + "\n");
                        sb.append("检测人：" + jsonObject4.optString("wname")  + "\n");
                        sb.append("检测结果：" + jsonObject4.optString("result")+ "\n");
                        builder.setMessage(sb.toString());
                        builder.create().show();


                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }

            }


            else if (msg.what==0x120){

                if (msg.obj.toString().equals("error")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(StudentMenuActivity.this);
                    builder.setTitle("温馨提示");
                    StringBuilder sb = new StringBuilder();
                    sb.append("请先进行健康上报或未审核通过！"  + "\n");
                    builder.setMessage(sb.toString());
                    builder.create().show();
                }else {
                    JSONObject jsonObject = null;
                    JSONObject jsonObject2 = null;
                    JSONObject jsonObject3 = null;

                    try{
                        jsonObject3 = new JSONObject(msg.obj.toString());
                        Log.i("==================","" + "opt:"+jsonObject3.optString("checkResult"));

                        if (jsonObject3.optString("checkResult").isEmpty()){
                            jsonObject2 = new JSONObject(jsonObject3.optString("student"));
                            AlertDialog.Builder builder = new AlertDialog.Builder(StudentMenuActivity.this);
                            builder.setTitle("上报信息情况");
                            StringBuilder sb = new StringBuilder();
                            sb.append("姓名：" + jsonObject3.optString("sname") + "\n");
                            sb.append("学号：" + jsonObject2.optString("sno")  + "\n");
                            sb.append("健康码：" + jsonObject3.optString("jkm")  + "\n");
                            sb.append("状态：" + jsonObject3.optString("status")+ "\n");
                            sb.append("时间：" + jsonObject3.optString("data") + "\n");
                            sb.append("负责人：" + jsonObject3.optString("wname")+ "\n");
                            sb.append("备注：" + jsonObject3.optString("comment") + "\n");
                            builder.setMessage(sb.toString());
                            builder.create().show();
                        }else {

                        jsonObject = new JSONObject(jsonObject3.optString("checkResult"));
                        jsonObject2 = new JSONObject(jsonObject.optString("student"));

                        AlertDialog.Builder builder = new AlertDialog.Builder(StudentMenuActivity.this);
                        builder.setTitle("上报信息情况");
                        StringBuilder sb = new StringBuilder();
                        sb.append("姓名：" + jsonObject3.optString("sname") + "\n");
                        sb.append("学号：" + jsonObject2.optString("sno")  + "\n");
                        sb.append("健康码：" + jsonObject3.optString("jkm")  + "\n");
                        sb.append("状态：" + jsonObject3.optString("status")+ "\n");
                        sb.append("时间：" + jsonObject3.optString("data") + "\n");
                        sb.append("负责人：" + jsonObject3.optString("wname")+ "\n");
                        sb.append("备注：" + jsonObject3.optString("comment") + "\n");
                        builder.setMessage(sb.toString());
                        builder.create().show();
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }

            }

            return true;
        }
    });


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_menu);

        final Button studentInfo = findViewById(R.id.stu_PersonInfo);
        final  Button health = findViewById(R.id.stu_health);
        final Button pre_check =findViewById(R.id.stu_pre_check);
        final Button check = findViewById(R.id.stu_check);
        final Button up_message = findViewById(R.id.stu_up_message);
        final Button exit = findViewById(R.id.stu_exit);

        Intent intent = getIntent();
        no =intent.getStringExtra("sno");
        preData =intent.getStringExtra("preData");

        studentInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Log.i("=============","已发送学生信息请求！");
                        String target = "http://172.18.33.64:8080/student/selectStudent";
                        Map<String,String> params = new HashMap<>();
                        params.put("sno",no);
                        result = ConnectionUtils.postRequest(target,params);
                        Log.i("=======================",result);
                        Message msg = new Message();
                        msg.what = 0x117;
                        msg.obj = result;
                        handler.sendMessage(msg);

                    }
                }).start();
            }
        });



        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("sno",no);
                intent.putExtra("preData",preData);
                Log.i("=========",preData);
                intent.setClass(StudentMenuActivity.this,HealthlyUpActivity.class);
                startActivity(intent);
            }
        });

        pre_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Intent intent = getIntent();
                        String no =intent.getStringExtra("sno");
                        Log.i("=============","已发送预处理信息请求！");
                        String target = "http://172.18.33.64:8080/preData/selectPreData";
                        Map<String,String> params = new HashMap<>();
                        params.put("sno",no);
                        result = ConnectionUtils.postRequest(target,params);
                        Log.i("=======================",result);
                        Message msg = new Message();
                        msg.what = 0x118;
                        msg.obj = result;
                        handler.sendMessage(msg);

                    }
                }).start();


            }
        });


        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Intent intent = getIntent();
                        String no =intent.getStringExtra("sno");
                        Log.i("=============","已发送查找核酸结果信息请求！");
                        String target = "http://172.18.33.64:8080/check/selectCheck";
                        Map<String,String> params = new HashMap<>();
                        params.put("sno",no);
                        result = ConnectionUtils.postRequest(target,params);
                        Log.i("=======================",result);
                        Message msg = new Message();
                        msg.what = 0x119;
                        msg.obj = result;
                        handler.sendMessage(msg);

                    }
                }).start();
            }
        });

        up_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Intent intent = getIntent();
                        String no =intent.getStringExtra("sno");
                        Log.i("=============","已发送查找已提交数据的信息请求！");
                        String target = "http://172.18.33.64:8080/submit/selectSubmit";
                        Map<String,String> params = new HashMap<>();
                        params.put("sno",no);
                        result = ConnectionUtils.postRequest(target,params);
                        Log.i("=======================",result);
                        Message msg = new Message();
                        msg.what = 0x120;
                        msg.obj = result;
                        handler.sendMessage(msg);

                    }
                }).start();
            }
        });


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentMenuActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });



    }
    }

