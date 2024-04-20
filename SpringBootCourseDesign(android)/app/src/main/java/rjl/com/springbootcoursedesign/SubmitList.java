package rjl.com.springbootcoursedesign;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SubmitList extends AppCompatActivity {

        String checked = "";
        private String result = "";
        private ListView mListView;
        //需要适配的数据
        private String[] sno = new String[10];
        private String[] sname = new String[10];
        private String[] status =new String[10];
        private String[] comment =new String[10];



    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 0x701) {
                try {

                    if (msg.obj.toString().equals("error")){
                        AlertDialog.Builder builder = new AlertDialog.Builder(SubmitList.this);
                        builder.setTitle("温馨提示");
                        StringBuilder sb = new StringBuilder();
                        sb.append("抱歉，查询出错了！" + "\n");
                        builder.setMessage(sb.toString());
                        builder.create().show();
                    }else {

                    JSONArray jsonArray = new JSONArray(msg.obj.toString());

                    for (int i = 0; i <= jsonArray.length(); i++) {  //通过for循环遍历JSON数据
                        JSONObject jsonObject = jsonArray.getJSONObject(i);  //解析JSON数据
                        JSONObject jsonObject1 = new JSONObject(jsonObject.optString("checkResult"));  //解析JSON数据
                        JSONObject jsonObject2 = new JSONObject(jsonObject1.optString("student"));
                        sname[i] = jsonObject.optString("sname");
                        sno[i] = jsonObject2.optString("sno");
                        status[i] = jsonObject.optString("status");
                        comment[i] = jsonObject.optString("comment");
                    }
                    Toast.makeText(SubmitList.this, "数据已更新", Toast.LENGTH_LONG);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                SubmitList.this.onResume();
            }else if (msg.what==0x702){
                try {

                    if (msg.obj.toString().equals("error")){
                        AlertDialog.Builder builder = new AlertDialog.Builder(SubmitList.this);
                        builder.setTitle("温馨提示");
                        StringBuilder sb = new StringBuilder();
                        sb.append("抱歉，查询出错了！" + "\n");
                        builder.setMessage(sb.toString());
                        builder.create().show();
                    }else {

                        JSONArray jsonArray = new JSONArray(msg.obj.toString());
//                        JSONObject jsonObject9 = jsonArray.getJSONObject(0);
//                        Log.i("=====jsonArray1=====",jsonObject9.toString());
                        for (int i = 0; i <= 5; i++) {  //通过for循环遍历JSON数据
                            JSONObject jsonObject = jsonArray.getJSONObject(i);  //解析JSON数据
                            JSONObject jsonObject1 = new JSONObject(jsonObject.optString("checkResult"));  //解析JSON数据
                            JSONObject jsonObject2 = new JSONObject(jsonObject1.optString("student"));
                            Log.i("=====jsonObject2=====",jsonObject2.toString());
                            sname[i] = jsonObject.optString("sname");
                            sno[i] = jsonObject2.optString("sno");
                            status[i] = jsonObject.optString("status");
                            comment[i] = jsonObject.optString("comment");
                        }
                        Toast.makeText(SubmitList.this, "数据已更新", Toast.LENGTH_LONG);
                        SubmitList.this.onResume();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            return true;
            }

    });



    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.submit_list);
            //初始化ListView控件
            mListView = (ListView) findViewById(R.id.lv);
            //创建一个Adapter的实例
            MyBaseAdapter mAdapter = new MyBaseAdapter();
            //设置Adapter
            mListView.setAdapter(mAdapter);

            final Button select = findViewById(R.id.submit_select);
            final Button allSelect = findViewById(R.id.submit_allSelect);
            final RadioButton submit = findViewById(R.id.submit_submit);
            final RadioButton other = findViewById(R.id.submit_other);


            select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (submit.isChecked()){
                        checked = submit.getText().toString();
                    }else {
                        checked = other.getText().toString();
                    }
                    if (checked.equals("")){
                        AlertDialog.Builder builder = new AlertDialog.Builder(SubmitList.this);
                        builder.setTitle("查询结果");
                        builder.setMessage("请选择查询条件");
                        builder.setPositiveButton("确定", null);
                        builder.show();

                    }else {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {

                                Log.i("=============", "已发送按状态查询信息请求！"+checked);
                                String target = "http://172.18.33.64:8080/submit/selectSubmitByStatus";
                                Map<String, String> params = new HashMap<>();
                                params.put("status", checked);
                                result = ConnectionUtils.postRequest(target, params);
                                Log.i("=======================", result);
                                Message msg = new Message();
                                msg.what = 0x701;
                                msg.obj = result;
                                handler.sendMessage(msg);
                            }
                        }).start();

                    }
                }
            });



        allSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Log.i("=============", "已发送全查询上报信息请求！");
                        String target = "http://172.18.33.64:8080/submit/selectAllSubmit";
                        Map<String, String> params = new HashMap<>();
                        result = ConnectionUtils.postRequest(target, params);
                        Log.i("=======================", result);
                        Message msg = new Message();
                        msg.what = 0x702;
                        msg.obj = result;
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });

        }
        //创建一个类继承BaseAdapter
        class MyBaseAdapter extends BaseAdapter {
            //得到item的总数
            @Override
            public int getCount() {
                //返回ListView Item条目的总数
                return sno.length;
            }
            //得到Item代表的对象
            @Override
            public Object getItem(int position) {
                //返回ListView Item条目代表的对象
                return sno[position];
            }
            //得到Item的id
            @Override
            public long getItemId(int position) {
                //返回ListView Item的id
                return position;
            }
            //得到Item的View视图
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                ViewHolder holder = null;
                if(convertView == null){
                    //将list_item.xml文件找出来并转换成View对象
                    convertView  = View.inflate(SubmitList.this, R.layout.user_item, null);
                    //找到list_item.xml中创建的TextView
                    holder = new ViewHolder();
                    holder.submit_sno = (TextView) convertView.findViewById(R.id.user_id);
                    holder.submit_sname = (TextView) convertView.findViewById(R.id.user_sname);
                    holder.status = (TextView) convertView.findViewById(R.id.user_sno);
                    holder.comment = (TextView) convertView.findViewById(R.id.user_status);

                    convertView.setTag(holder);
                }else{
                    holder = (ViewHolder) convertView.getTag();
                }
                holder.submit_sno.setText(sno[position]);
                holder.submit_sname.setText(sname[position]);
                holder.status.setText(status[position]);
                holder.comment.setText(comment[position]);

                return convertView;


            }

        }
        static class ViewHolder{
            TextView submit_sno;
            TextView submit_sname;
            TextView status;
            TextView comment;

        }
    }


////点击进行修改操作
//        ((ListView) this.findViewById(R.id.listClass))
//                .setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//@Override
//public void onItemClick(AdapterView<?> parent, View view,
//        int position, long id) {
//        Intent in = new Intent("com.example.smis.stu_info");
//        //将id数据放置到Intent
//        in.putExtra("id", id);
//        System.out.println(id);
//        startActivity(in);
//        }
//        });

