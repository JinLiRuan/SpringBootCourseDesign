package rjl.com.springbootcoursedesign;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rjl.com.springbootcoursedesign.admin.AlterPassword;

public class AdminMenuActivity extends AppCompatActivity {


    int sflag = 0;
    int wflag = 0;
    int rflag = 0;
    public static List<Student> studentList = new ArrayList<Student>();
    public static List<Worker> workerList = new ArrayList<Worker>();
    public static List<Check> checkList = new ArrayList<Check>();
    private String result = "";


    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 0x501) {
                try {

                    if (sflag == 0){

                    JSONArray jsonArray = new JSONArray(msg.obj.toString());

                    for (int i = 0; i < jsonArray.length(); i++) {  //通过for循环遍历JSON数据
                        JSONObject jsonObject = jsonArray.getJSONObject(i);  //解析JSON数据
                        //利用while循环，当有数据都添加到数组
                        Student student =new Student();
                        student.setId(i);
                        student.setSno(jsonObject.optString("sno"));
                        student.setName(jsonObject.optString("sname"));
                        student.setMajor(jsonObject.optString("classes"));
                        studentList.add(student);
                    }

                    }

                    sflag=1;
                    Intent intent = new Intent();
                    intent.setClass(AdminMenuActivity.this,StudentInfo.class);
                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if (msg.what == 0x502) {

                try {

                    if (wflag == 0){

                    JSONArray jsonArray = new JSONArray(msg.obj.toString());

                    for (int i = 0; i < jsonArray.length(); i++) {  //通过for循环遍历JSON数据
                        JSONObject jsonObject = jsonArray.getJSONObject(i);  //解析JSON数据
                        //利用while循环，当有数据都添加到数组
                        Worker worker =new Worker();
                        worker.setId(i);
                        worker.setWno(jsonObject.optString("wno"));
                        worker.setWname(jsonObject.optString("wname"));
                        worker.setWtype(jsonObject.optString("wtype"));
                        workerList.add(worker);
                    }
                    }
                    wflag=1;

                    Intent intent = new Intent();
                    intent.setClass(AdminMenuActivity.this,WorkerInfo.class);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if (msg.what == 0x503) {
                try {

                    if(rflag == 0){
                    JSONArray jsonArray = new JSONArray(msg.obj.toString());
                        JSONObject jsonObject2 = null;
                    Log.i("=====check====","jsonArray"+jsonArray.toString());

                    for (int i = 0; i < jsonArray.length(); i++) {  //通过for循环遍历JSON数据
                        JSONObject jsonObject = jsonArray.getJSONObject(i);  //解析JSON数据
                        if (!TextUtils.isEmpty(jsonObject.optString("student"))){
                           jsonObject2 = new JSONObject(jsonObject.optString("student"));
                        }

                        //解析JSON数据
                        //利用while循环，当有数据都添加到数组
                        Check check =new Check();
                        check.setId(i);
                        check.setSname(jsonObject.optString("sname"));
                        check.setSno(jsonObject2.optString("sno"));
                        check.setResult(jsonObject.optString("result"));
                        check.setWname(jsonObject.optString("wname"));
                        checkList.add(check);
                    }
                }
                rflag=1;

                Intent intent = new Intent();
                intent.setClass(AdminMenuActivity.this,CheckInfo.class);
                startActivity(intent);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }




            return true;
        }

    });




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_menu);

        final Button studentInfo = findViewById(R.id.studentInfo);
        final  Button workerInfo = findViewById(R.id.workerInfo);
        final Button admin_up =findViewById(R.id.admin_up);
        final Button admin_check = findViewById(R.id.admin_check);
        final Button password = findViewById(R.id.adminPassword);
        final Button exit = findViewById(R.id.admin_exit);


        studentInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Log.i("=============","已发送查询所有学生信息请求！");
                        String target = "http://172.18.33.64:8080/student/selectAllStudent";
                        Map<String,String> params = new HashMap<>();
                        params.put("flag","all");
                        result = ConnectionUtils.postRequest(target,params);
                        Log.i("==========students=============",result);
                        Message msg = new Message();
                        msg.what = 0x501;
                        msg.obj = result;
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });


        workerInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Log.i("=============","已发送查询所有学生信息请求！");
                        String target = "http://172.18.33.64:8080/worker/selectAllWorker";
                        Map<String,String> params = new HashMap<>();
                        params.put("flag","all");
                        result = ConnectionUtils.postRequest(target,params);
                        Log.i("==========workers=============",result);
                        Message msg = new Message();
                        msg.what = 0x502;
                        msg.obj = result;
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });


        admin_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(AdminMenuActivity.this,SubmitList.class);
                startActivity(intent);
            }
        });


        admin_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Log.i("=============","已发送查询所有核酸信息请求！");
                        String target = "http://172.18.33.64:8080/check/selectAllCheck";
                        Map<String,String> params = new HashMap<>();
                        params.put("flag","all");
                        result = ConnectionUtils.postRequest(target,params);
                        Log.i("==========checks=============",result);
                        Message msg = new Message();
                        msg.what = 0x503;
                        msg.obj = result;
                        handler.sendMessage(msg);
                    }
                }).start();



            }


        });

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(AdminMenuActivity.this,AlterPassword.class);
                startActivity(intent);
            }
        });


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(AdminMenuActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
