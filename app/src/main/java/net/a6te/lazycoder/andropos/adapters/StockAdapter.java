package net.a6te.lazycoder.andropos.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import net.a6te.lazycoder.andropos.R;
import net.a6te.lazycoder.andropos.modelClass.StockModel;

import java.util.ArrayList;

/**
 * Created by Programmer on 5/7/2017.
 */

public class StockAdapter extends ArrayAdapter<StockModel>{

    private Context context;
    private ArrayList<StockModel> stocks;

    public StockAdapter(@NonNull Context context,@NonNull ArrayList<StockModel> objects) {
        super(context, R.layout.custom_stock_lv, objects);
        this.context = context;
        stocks = objects;
    }

    class ViewHolder{
        TextView pName;
        TextView brandName;
        TextView pCode;
        TextView pSize;
        TextView stockLimit;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.custom_stock_lv,parent,false);

            holder.pName = (TextView) convertView.findViewById(R.id.stockPNameTv);
            holder.brandName = (TextView) convertView.findViewById(R.id.stockPBrandTv);
            holder.pCode = (TextView) convertView.findViewById(R.id.stockPCodeTv);
            holder.pSize = (TextView) convertView.findViewById(R.id.stockPSizeTv);
            holder.stockLimit = (TextView) convertView.findViewById(R.id.stockLimitTv);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.pName.setText(stocks.get(position).getpName());
        holder.brandName.setText(stocks.get(position).getBrandName());
        holder.pCode.setText(stocks.get(position).getpCode());
        holder.pSize.setText(stocks.get(position).getpSize());
        holder.stockLimit.setText(stocks.get(position).getStockLimit());

        return convertView;
    }
}
