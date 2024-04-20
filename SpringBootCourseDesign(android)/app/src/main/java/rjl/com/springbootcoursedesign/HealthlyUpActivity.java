package rjl.com.springbootcoursedesign;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Map;

public class HealthlyUpActivity extends AppCompatActivity {


    private String result="";
    String address = "";
    String no = "";
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            if (msg.what==0x121) {

                if (msg.obj.toString().equals("error")) {
                    Toast.makeText(HealthlyUpActivity.this, "抱歉，提交失败！", Toast.LENGTH_LONG);
                } else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(HealthlyUpActivity.this);
                    builder.setTitle("温馨提示");
                    builder.setMessage("数据已上传成功！等待工作人员审核...");
                    builder.setPositiveButton("确定", null);
                    builder.create().show();
                }
            }
            return true;
        }
    });


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.healthly_up);


        Button submit = findViewById(R.id.stu_submit);
        final RadioButton btn1 = findViewById(R.id.address_n);
        final RadioButton btn2 = findViewById(R.id.address_b);




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getIntent();
                no =intent.getStringExtra("sno");
                String preData =intent.getStringExtra("preData");
                Log.i("======preData======",preData+"upload");
                if (preData.length() != 0){
                    Log.i("=========","该账户已健康上报！");
                    AlertDialog.Builder builder = new AlertDialog.Builder(HealthlyUpActivity.this);
                    builder.setTitle("温馨提示");
                    StringBuilder sb = new StringBuilder();
                    sb.append("您已经提交过数据！不能重复提交"  + "\n");
                    builder.setMessage(sb.toString());
                    builder.create().show();
                }else {
                    if (btn1.isChecked()){
                        address = btn1.getText().toString();
                    }else {
                        address = btn2.getText().toString();
                    }


                    if (address.equals("")){
                                Toast.makeText(HealthlyUpActivity.this, "数据不能为空！", Toast.LENGTH_SHORT).show();
                            }else {

                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {

                                Log.i("=============", "已发送添加预处理信息请求！");
                                String target = "http://172.18.33.64:8080/preData/addPreData";
                                Map<String, String> params = new HashMap<>();
                                params.put("sno", no);
                                params.put("address", address);
                                Log.i("=========",no+":no"+"address:"+address);
                                result = ConnectionUtils.postRequest(target, params);
                                Log.i("=======================", result);
                                Message msg = new Message();
                                msg.what = 0x121;
                                msg.obj = result;
                                handler.sendMessage(msg);

                            }
                        }).start();

                            }
                }
            }
        });

    }
}
