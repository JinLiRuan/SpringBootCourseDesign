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

import rjl.com.springbootcoursedesign.student.AlterPassword;

public class WorkerMenuActivity extends AppCompatActivity {

    private String result="";
    String type= "";

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            JSONObject jsonObject = null;

            if (msg.what==0x201){

                if (msg.obj.toString().equals("error")){
                    Toast.makeText(WorkerMenuActivity.this,"抱歉，目前无您的详细信息！",Toast.LENGTH_LONG);
                }else {

                    try{
                        jsonObject = new JSONObject(msg.obj.toString());
                        type = jsonObject.optString("wtype");
                        AlertDialog.Builder builder = new AlertDialog.Builder(WorkerMenuActivity.this);
                        builder.setTitle("个人信息");
                        StringBuilder sb = new StringBuilder();
                        sb.append("姓名：" + jsonObject.optString("wname") + "\n");
                        sb.append("工号：" + jsonObject.optString("wno")  + "\n");
                        sb.append("性别：" + jsonObject.optString("wsex")  + "\n");
                        sb.append("年龄：" + jsonObject.optString("wage")+ "\n");
                        sb.append("工作类型：" + type + "\n");
                        builder.setMessage(sb.toString());
                        builder.create().show();

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }


            }else if (msg.what==0x118){

                if (msg.obj.toString().equals("error")){
                    Toast.makeText(WorkerMenuActivity.this,"抱歉，暂无需审核的数据",Toast.LENGTH_LONG);
                }else{
                    Intent intent = new Intent();
                    intent.setClass(WorkerMenuActivity.this,UserList.class);
                    startActivity(intent);
                }

            }

            return true;
        }
    });


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worker_menu);

        final Button workerInfo = findViewById(R.id.PersonInfo);
        final  Button worker_data = findViewById(R.id.worker_check_data);
        final Button worker_check =findViewById(R.id.worker_check);
        final Button worker_exit = findViewById(R.id.worker_exit);



        workerInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Intent intent = getIntent();
                        String no =intent.getStringExtra("wno");
                        Log.i("=============","已发送查询工人信息请求！");
                        String target = "http://172.18.33.64:8080/worker/selectWorker";
                        Map<String,String> params = new HashMap<>();
                        params.put("wno",no);
                        result = ConnectionUtils.postRequest(target,params);
                        Log.i("=======================",result);
                        Message msg = new Message();
                        msg.what = 0x201;
                        msg.obj = result;
                        handler.sendMessage(msg);

                    }
                }).start();
            }
        });




        worker_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getIntent();
                String no =intent.getStringExtra("wno");
                Log.i("=====workermenu========",no+"xxx");
                Log.i("=============","已发送工作人员处理上报信息请求！");
                Intent intent1 = new Intent();
                intent1.setClass(WorkerMenuActivity.this,UserList.class);
                intent1.putExtra("wno",no);
                intent1.putExtra("wtype",type);
                startActivity(intent1);

            }
        });


        worker_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getIntent();
                String no =intent.getStringExtra("wno");

                Intent intent1 = new Intent();
                intent1.setClass(WorkerMenuActivity.this,CheckResult.class);
                intent1.putExtra("wno",no);
                startActivity(intent1);

            }
        });




        worker_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkerMenuActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });



    }
    }

