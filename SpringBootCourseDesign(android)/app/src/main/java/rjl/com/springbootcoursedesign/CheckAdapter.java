package rjl.com.springbootcoursedesign;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class CheckAdapter extends ArrayAdapter<Check> {
    private int resourceId;
    private List<Check> checkList;

    public CheckAdapter(Context context, int resource, List<Check> objects) {
        super(context, resource, objects);
        resourceId = resource;
        checkList = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Check check = checkList.get(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.check_sname = view.findViewById(R.id.student_name);
            viewHolder.check_sno = view.findViewById(R.id.id);
            viewHolder.result = view.findViewById(R.id.student_sno);
            viewHolder.check_wname =  view.findViewById(R.id.student_major);

            view.setTag(viewHolder);

        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.check_sname.setText(String.valueOf(check.getSname()));
        viewHolder.check_sno.setText(String.valueOf(check.getSno()));
        viewHolder.result.setText(String.valueOf(check.getResult()));
        viewHolder.check_wname.setText(String.valueOf(check.getWname()));

        return view;

    }

    class ViewHolder {
        TextView check_sno;
        TextView check_sname;
        TextView result;
        TextView check_wname;

    }

}
