package rjl.com.springbootcoursedesign;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class StudentInfo extends AppCompatActivity {

    private StudentAdapter adapter;
    private ListView listView;
private String result = "";

    Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            if (msg.what == 0x601){


                JSONObject jsonObject = null;

                if (msg.obj.toString().equals("error")){
                    Toast.makeText(StudentInfo.this,"抱歉，目前无您的详细信息！",Toast.LENGTH_LONG);
                }else {

                    try {
                        jsonObject = new JSONObject(msg.obj.toString());

                        AlertDialog.Builder builder = new AlertDialog.Builder(StudentInfo.this);
                        builder.setTitle("个人信息");
                        StringBuilder sb = new StringBuilder();
                        sb.append("姓名：" + jsonObject.optString("sname") + "\n");
                        sb.append("学号：" + jsonObject.optString("sno") + "\n");
                        sb.append("性别：" + jsonObject.optString("sex") + "\n");
                        sb.append("年龄：" + jsonObject.optString("sage") + "\n");
                        sb.append("班级：" + jsonObject.optString("classes") + "\n");
                        builder.setMessage(sb.toString());
                        builder.create().show();

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }

            }


            return true;
        }
    });

           @Override
        protected void onCreate (@Nullable Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.student_info);
            adapter = new StudentAdapter(this, R.layout.student_item, AdminMenuActivity.studentList);
            listView = findViewById(R.id.list_view);
            listView.setAdapter(adapter);

            final EditText sno = findViewById(R.id.input_sno);
            Button select = findViewById(R.id.user_select);


            select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            String no = sno.getText().toString().trim();
                            Log.i("============","no的值为"+no);
                            if (TextUtils.isEmpty(no)){
                                AlertDialog.Builder builder = new AlertDialog.Builder(StudentInfo.this);
                                builder.setTitle("温馨提示" ) ;
                                builder.setMessage("数据不能为空！");
                                builder.setPositiveButton("确定", null);
                                builder.create().show();
                            }
                            else {


                            Log.i("=============","已发送学生信息请求！"+no);
                            String target = "http://172.18.33.64:8080/student/selectStudent";
                            Map<String,String> params = new HashMap<>();
                            params.put("sno",no);
                            result = ConnectionUtils.postRequest(target,params);
                            Log.i("=======================",result);
                            Message msg = new Message();
                            msg.what = 0x601;
                            msg.obj = result;
                            handler.sendMessage(msg);
                            }
                        }
                    }).start();
                }
            });

        }


    }


