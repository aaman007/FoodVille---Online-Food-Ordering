package app.aaman007.com.tablayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NameCustomAdapter extends BaseAdapter {
    List<String>orderersNameList = new ArrayList<>();
    Context context;
    NameCustomAdapter(Context context, List<String>orderersNameList)
    {
        this.context = context;
        this.orderersNameList = orderersNameList;
    }
    @Override
    public int getCount() {
        return orderersNameList.size();
    }

    @Override
    public Object getItem(int position) {
        return orderersNameList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.orderer_details_sample,parent,false);
        }
        TextView name = convertView.findViewById(R.id.ordererId);
        TextView phone = convertView.findViewById(R.id.ordererPhoneId);
        TextView address = convertView.findViewById(R.id.ordererAddressId);
        name.setText("Name     : "+orderersNameList.get(position));
        phone.setText("Phone    : "+OrdersList.orderersPhoneList.get(position));
        address.setText("Address : "+OrdersList.orderersAddressList.get(position));
        return convertView;
    }
}
