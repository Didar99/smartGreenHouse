package ne.iot.smartgreenhouse.adapter;


import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ne.iot.smartgreenhouse.R;
import ne.iot.smartgreenhouse.model.Sensors;

/**
 * Created by ankit on 27/10/17.
 */

public class SensorsAdapter extends RecyclerView.Adapter<SensorsAdapter.ViewHolder> {

    private Context context;
    private List<Sensors> list;
    int row_index = -1;

    public SensorsAdapter(Context context, List<Sensors> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_sensor, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Sensors sensors = list.get(position);

        holder.sensor_num.setText(String.valueOf(sensors.getId()));
        holder.sensor_name.setText(sensors.getCommand());
        holder.sensor_degree.setText(String.valueOf(sensors.getState()));

        holder.cardView.setOnClickListener(view -> {
            row_index = position;
            notifyDataSetChanged();
        });
        if(row_index == position) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#FF0091EA"));
            holder.sensor_name.setTextColor(Color.parseColor("#ffffff"));
            holder.sensor_degree.setTextColor(Color.parseColor("#ffffff"));
            holder.sensor_num.setTextColor(Color.parseColor("#ffffff"));
            holder.sensor_prefix.setTextColor(Color.parseColor("#ffffff"));
            holder.sensor_divider.setColorFilter(Color.parseColor("#ffffff"));
        } else {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#FFF5F5F5"));
            holder.sensor_name.setTextColor(Color.parseColor("#FF86909C"));
            holder.sensor_num.setTextColor(Color.parseColor("#FF86909C"));
            holder.sensor_prefix.setTextColor(Color.parseColor("#FF86909C"));
            holder.sensor_degree.setTextColor(Color.parseColor("#FF86909C"));
            holder.sensor_divider.setColorFilter(Color.parseColor("#FF86909C"));

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView sensor_num, sensor_name, sensor_degree, sensor_prefix;
        public ImageView sensor_icon, sensor_divider;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            sensor_icon = itemView.findViewById(R.id.sensorIcon);
            sensor_divider = itemView.findViewById(R.id.imgDivider);
            sensor_prefix= itemView.findViewById(R.id.sensorPrefix);
            sensor_num = itemView.findViewById(R.id.sensorNum);
            sensor_name = itemView.findViewById(R.id.sensorName);
            sensor_degree = itemView.findViewById(R.id.sensorDegree);
            cardView = itemView.findViewById(R.id.sensor_card);
        }
    }

}