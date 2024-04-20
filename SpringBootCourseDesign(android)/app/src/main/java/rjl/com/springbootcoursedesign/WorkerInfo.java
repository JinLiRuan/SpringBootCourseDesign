package rjl.com.springbootcoursedesign;


import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WorkerInfo extends AppCompatActivity {

    private WorkerAdapter adapter;
    private ListView listView;
    private String result = "";


    Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            if (msg.what == 0x602){

                JSONObject jsonObject = null;

                if (msg.obj.toString().equals("error")){
                    Toast.makeText(WorkerInfo.this,"抱歉，目前无您的详细信息！",Toast.LENGTH_LONG);
                }else {

                    try {
                        jsonObject = new JSONObject(msg.obj.toString());

                        AlertDialog.Builder builder = new AlertDialog.Builder(WorkerInfo.this);
                        builder.setTitle("工人信息");
                        StringBuilder sb = new StringBuilder();
                        sb.append("姓名：" + jsonObject.optString("wname") + "\n");
                        sb.append("工号：" + jsonObject.optString("wno") + "\n");
                        sb.append("性别：" + jsonObject.optString("wsex") + "\n");
                        sb.append("年龄：" + jsonObject.optString("wage") + "\n");
                        sb.append("工作类型：" + jsonObject.optString("wtype") + "\n");
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worker_info);
        adapter = new WorkerAdapter(this,R.layout.student_item,AdminMenuActivity.workerList);
        listView =findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        final EditText sno = findViewById(R.id.input_sno);
        Button select = findViewById(R.id.user_select);


        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        String no = sno.getText().toString();
                        Log.i("============","no的值为"+no.length());
                        if (no.length() == 0){
                            Toast.makeText(WorkerInfo.this,"数据不能为空，请输入工号",Toast.LENGTH_LONG);
                        }
                        else {
                            Log.i("=============","已发送查询工人信息请求！"+no);
                            String target = "http://172.18.33.64:8080/worker/selectWorker";
                            Map<String,String> params = new HashMap<>();
                            params.put("wno",no);
                            result = ConnectionUtils.postRequest(target,params);
                            Log.i("=======================",result);
                            Message msg = new Message();
                            msg.what = 0x602;
                            msg.obj = result;
                            handler.sendMessage(msg);
                        }
                    }
                }).start();
            }
        });

    }


    }

