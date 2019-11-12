package app.aaman007.com.tablayout;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    List<String>drinksName;
    List<Integer>drinksPrice;
    Context context;

    public ListViewAdapter(FragmentActivity context, List<String> drinksName, List<Integer> drinksPrice) {
        this.drinksName = drinksName;
        this.drinksPrice = drinksPrice;
        this.context = context;
    }

    @Override
    public int getCount() {
        return drinksName.size();
    }

    @Override
    public Object getItem(int position) {
        return drinksName.get(position);
    }
    public Integer getPrice(int position){
        return drinksPrice.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            LayoutInflater layoutInflater = null;
            layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.sample_view,parent,false);
        }
        TextView name = convertView.findViewById(R.id.nameId);
        TextView price = convertView.findViewById(R.id.priceId);

        name.setText(getItem(position).toString());
        price.setText("TK. "+getPrice(position));
        return convertView;
    }
}
