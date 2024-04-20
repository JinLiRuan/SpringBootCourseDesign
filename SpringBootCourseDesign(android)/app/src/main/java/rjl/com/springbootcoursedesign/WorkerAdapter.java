package rjl.com.springbootcoursedesign;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class WorkerAdapter extends ArrayAdapter<Worker> {
    private int resourceId;
    List<Worker> workerList;

    public WorkerAdapter(Context context, int resource, List<Worker> objects) {
        super(context, resource, objects);
        resourceId = resource;
        workerList = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Worker worker = workerList.get(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.worker_id = view.findViewById(R.id.id);
            viewHolder.worker_name = view.findViewById(R.id.student_name);
            viewHolder.worker_wno = view.findViewById(R.id.student_sno);
            viewHolder.worker_type =  view.findViewById(R.id.student_major);

            view.setTag(viewHolder);

        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.worker_id.setText(String.valueOf(worker.getId()));
        viewHolder.worker_name.setText(String.valueOf(worker.getWname()));
        viewHolder.worker_wno.setText(String.valueOf(worker.getWno()));
        viewHolder.worker_type.setText(String.valueOf(worker.getWtype()));

        return view;

    }

    class ViewHolder {
        TextView worker_id;
        TextView worker_name;
        TextView worker_wno;
        TextView worker_type;

    }

}
