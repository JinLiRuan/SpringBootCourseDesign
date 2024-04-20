package rjl.com.springbootcoursedesign;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class UpdataPreData extends AppCompatActivity {

    private String result = "";
    String type = "";
    EditText sno;
    EditText jkm;
    TextView mg;
    String stu_jkm;


     Handler handler = new Handler(new Handler.Callback() {
         @Override
         public boolean handleMessage(Message msg) {

             JSONObject jsonObject = null;
             if (msg.what==0x301){

                 if (msg.obj.toString().equals("error")){
                     AlertDialog.Builder builder = new AlertDialog.Builder(UpdataPreData.this);
                     builder.setTitle("温馨提示");
                     StringBuilder sb = new StringBuilder();
                     sb.append("抱歉！系统繁忙！"+ "\n");
                     builder.setMessage(sb.toString());
                     builder.create().show();
                 }else {
                     try{
                         jsonObject = new JSONObject(msg.obj.toString());
                         String mw = jsonObject.optString("address");
                         mg.setText(jsonObject.optString("address"));
                         Log.i("=======contain=====",type.contains(mw)+"type:"+type+"mw:"+mw);
                         if (!type.contains(mw)){
                             AlertDialog.Builder builder = new AlertDialog.Builder(UpdataPreData.this);
                             builder.setTitle("温馨提示");
                             StringBuilder sb = new StringBuilder();
                             sb.append("抱歉！该生信息您无权处理！"+ "\n");
                             builder.setMessage(sb.toString());
                             builder.create().show();
                         }else {

                         if (stu_jkm.equals("y")){
                            AlertDialog.Builder builder = new AlertDialog.Builder(UpdataPreData.this);
                             builder.setTitle("温馨提示" ) ;
                             builder.setMessage("健康上报成功！") ;
                             builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                 @Override
                                 public void onClick(DialogInterface dialog, int which) {
                                     Intent intent = new Intent();
                                     intent.setClass(UpdataPreData.this,WorkerMenuActivity.class);
                                     startActivity(intent);
                                 }
                             });
                             builder.create().show();
                         }else {
                             AlertDialog.Builder builder = new AlertDialog.Builder(UpdataPreData.this);
                             builder.setTitle("温馨提示" ) ;
                             builder.setMessage("健康码为非绿码，请带学生去核酸检测！") ;
                             builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                 @Override
                                 public void onClick(DialogInterface dialog, int which) {
                                     Intent intent = new Intent();
                                     intent.setClass(UpdataPreData.this,WorkerMenuActivity.class);
                                     startActivity(intent);
                                 }
                             });
                             builder.create().show();
                         }
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
        setContentView(R.layout.update_predata);

        Button upload = findViewById(R.id.upload);
        sno = findViewById(R.id.update_sno);
        jkm = findViewById(R.id.update_jkm);
        mg =findViewById(R.id.mg);



        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String stu_sno = sno.getText().toString();
                stu_jkm = jkm.getText().toString();

                if (stu_sno.equals("")|| stu_jkm.equals("")){
                    Toast.makeText(UpdataPreData.this, "数据不能为空！", Toast.LENGTH_SHORT).show();
                }else {



                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Intent intent = getIntent();
                        String wno = intent.getStringExtra("wno");
                        type = intent.getStringExtra("wtype");
                        Log.i("=====updatePreData========",wno+"stu_sno:"+stu_sno+"jkm:"+stu_jkm);
                        Log.i("=============","已发送更新待处理信息请求！");
                        String target = "http://172.18.33.64:8080/preData/updatePreData";
                        Map<String,String> params = new HashMap<>();
                        params.put("sno",stu_sno);
                        params.put("jkm",stu_jkm);
                        params.put("wno",wno);
                        result = ConnectionUtils.postRequest(target,params);
                        Log.i("=======================",result);
                        Message msg = new Message();
                        msg.what = 0x301;
                        msg.obj = result;
                        handler.sendMessage(msg);
                    }
                }).start();
                }
            }
        });
    }




}
