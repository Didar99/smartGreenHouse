package ne.iot.smartgreenhouse.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import ne.iot.smartgreenhouse.R;

public class DeviceAdapter extends BaseAdapter {
    // Variables -> Each Fragment GridView items
    private final Context context;
    private LayoutInflater inflater;
    private final String[] numberWord;
    private final int[] numberImage;
    boolean light = false;

    public DeviceAdapter(Context c, String[] numberWord, int[] numberImage) {
        context = c;
        this.numberWord = numberWord;
        this.numberImage = numberImage;
    }
    @Override
    public int getCount() {
        return numberWord.length;
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null){
            convertView = inflater.inflate(R.layout.row_device, null);
        }
        // Get data from Fragments and set to row_item


        CardView cardView = convertView.findViewById(R.id.dev_panel);
        ImageView imageView = convertView.findViewById(R.id.devIcon);
        TextView devName = convertView.findViewById(R.id.devName);


//        if (position == 2 && !light) {
//            cardView.setCardBackgroundColor(convertView.getContext().getResources().getColor(R.color.blue_200));
//        } else if (position == 2 && light) {
//            cardView.setCardBackgroundColor(convertView.getContext().getResources().getColor(R.color.gray_200));
//
//        }


        imageView.setImageResource(numberImage[position]);
        devName.setText(numberWord[position]);

        return convertView;
    }
}
