package rjl.com.springbootcoursedesign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;


public class CheckInfo extends AppCompatActivity {


    private CheckAdapter adapter;
    private ListView listView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_info);
        adapter = new CheckAdapter(this,R.layout.student_item,AdminMenuActivity.checkList);
        listView =findViewById(R.id.check_list);
        listView.setAdapter(adapter);

    }
}
