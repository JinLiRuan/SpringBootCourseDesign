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
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserList extends AppCompatActivity {

        private String result = "";
        private ListView mListView;
        //需要适配的数据
        private String[] id = new String[10];
        private String[] sname = new String[10];
        private String[] sno =new String[10];
        private String[] status =new String[10];



    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 0x115) {
                try {

                    if (msg.obj.toString().equals("error")){
                        AlertDialog.Builder builder = new AlertDialog.Builder(UserList.this);
                        builder.setTitle("温馨提示");
                        StringBuilder sb = new StringBuilder();
                        sb.append("抱歉，查询出错了！" + "\n");
                        builder.setMessage(sb.toString());
                        builder.create().show();


                    }else {

                    JSONArray jsonArray = new JSONArray(msg.obj.toString());

                    for (int i = 0; i <= jsonArray.length(); i++) {  //通过for循环遍历JSON数据
                        JSONObject jsonObject = jsonArray.getJSONObject(i);  //解析JSON数据
                        JSONObject jsonObject1 = new JSONObject(jsonObject.optString("student"));

                        id[i] = String.valueOf(i);
                        sname[i] = jsonObject.optString("sname");
                        sno[i] = jsonObject1.optString("sno");
                        status[i] = jsonObject.optString("status");
                    }
                    Toast.makeText(UserList.this, "数据已更新", Toast.LENGTH_LONG);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            if (msg.what == 0x116) {
                JSONObject jsonObject =null;
                JSONObject jsonObject1=null;

                if (msg.obj.toString().equals("error")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(UserList.this);
                    builder.setTitle("查询结果");
                    builder.setMessage("抱歉，没有该用户信息！");
                    builder.setPositiveButton("确定", null);
                    builder.show();

                } else {

                    try {
                        jsonObject = new JSONObject(msg.obj.toString());
                        jsonObject1 = new JSONObject(jsonObject.optString("student"));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(UserList.this);
                    builder.setTitle("查询结果：");
                    StringBuilder sb = new StringBuilder();
                    sb.append("姓名：" + jsonObject.optString("sname")+"\n");
                    sb.append("学号:" +jsonObject1.optString("sno")+ "\n");
                    sb.append("状态:" + jsonObject.optString("status")+"\n");
                    sb.append("所在门岗:" + jsonObject.optString("address")+"\n");
                    builder.setMessage(sb.toString());
                    builder.create().show();
                }
            }


            if (msg.what == 0x117) {
                JSONObject jsonObject =null;

                if (msg.obj.toString().equals("error")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(UserList.this);
                    builder.setTitle("查询结果");
                    builder.setMessage("抱歉，没有该用户信息！");
                    builder.setPositiveButton("确定", null);
                    builder.show();

                } else {

                    try {
                        jsonObject = new JSONObject(msg.obj.toString());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(UserList.this);
                    builder.setTitle("查询结果：");
                    StringBuilder sb = new StringBuilder();
                    sb.append("姓名：" + jsonObject.optString("sname")+"\n");
                    sb.append("学号:" +jsonObject.optString("sno")+ "\n");
                    sb.append("状态:" + jsonObject.optString("status")+"\n");
                    sb.append("所在门岗:" + jsonObject.optString("address")+"\n");
                    builder.setMessage(sb.toString());
                    builder.create().show();
                }
            }

            return true;
            }

    });



    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.user_list);
            //初始化ListView控件
            mListView = (ListView) findViewById(R.id.lv);
            //创建一个Adapter的实例
            MyBaseAdapter mAdapter = new MyBaseAdapter();
            //设置Adapter
            mListView.setAdapter(mAdapter);

            final Button all = findViewById(R.id.user_all);
            final Button user = findViewById(R.id.user_select);
            final EditText sno = findViewById(R.id.user_sno);
            final Button update = findViewById(R.id.user_update);



            all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            Log.i("=============","已发送请求查询所有待处理信息请求！");
                            String target = "http://172.18.33.64:8080/preData/selectAllPreData";
                            Map<String,String> params = new HashMap<>();
                            params.put("id","all");
                            result = ConnectionUtils.postRequest(target,params);
                            Log.i("=======================",result);
                            Message msg = new Message();
                            msg.what = 0x115;
                            msg.obj = result;
                            handler.sendMessage(msg);
                        }
                    }).start();
                }
            });


            user.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final String no = sno.getText().toString();
                    if (no.equals("")){
                        AlertDialog.Builder builder = new AlertDialog.Builder(UserList.this);
                        builder.setTitle("查询结果");
                        builder.setMessage("抱歉，没有该用户信息！");
                        builder.setPositiveButton("确定", null);
                        builder.show();

                    }else {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {

                                Log.i("=============", "已发送查询个人待处理信息请求！");
                                String target = "http://172.18.33.64:8080/preData/selectPreData";
                                Map<String, String> params = new HashMap<>();
                                params.put("sno", no);
                                result = ConnectionUtils.postRequest(target, params);
                                Log.i("=======================", result);
                                Message msg = new Message();
                                msg.what = 0x116;
                                msg.obj = result;
                                handler.sendMessage(msg);
                            }
                        }).start();

                    }
                }
            });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getIntent();
                String wno = intent.getStringExtra("wno");
                String type = intent.getStringExtra("wtype");
                Log.i("======userlist========",wno+"xxx");
                Intent intent1 = new Intent();
                intent1.setClass(UserList.this,UpdataPreData.class);
                intent1.putExtra("wno",wno);
                intent1.putExtra("wtype",type);
                startActivity(intent1);


            }
        });

        }
        //创建一个类继承BaseAdapter
        class MyBaseAdapter extends BaseAdapter {
            //得到item的总数
            @Override
            public int getCount() {
                //返回ListView Item条目的总数
                return id.length;
            }
            //得到Item代表的对象
            @Override
            public Object getItem(int position) {
                //返回ListView Item条目代表的对象
                return id[position];
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
                    convertView  = View.inflate(UserList.this, R.layout.user_item, null);
                    //找到list_item.xml中创建的TextView
                    holder = new ViewHolder();
                    holder.user_id = (TextView) convertView.findViewById(R.id.user_id);
                    holder.user_sname = (TextView) convertView.findViewById(R.id.user_sname);
                    holder.user_sno = (TextView) convertView.findViewById(R.id.user_sno);
                    holder.user_status = (TextView) convertView.findViewById(R.id.user_status);

                    convertView.setTag(holder);
                }else{
                    holder = (ViewHolder) convertView.getTag();
                }
                holder.user_id.setText(id[position]);
                holder.user_sname.setText(sname[position]);
                holder.user_sno.setText(sno[position]);
                holder.user_status.setText(status[position]);

                return convertView;


            }

        }
        static class ViewHolder{
            TextView user_id;
            TextView user_sname;
            TextView user_sno;
            TextView user_status;

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

