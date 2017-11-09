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
import net.a6te.lazycoder.andropos.modelClass.ProductListModel;

import java.util.ArrayList;

/**
 * Created by Programmer on 5/7/2017.
 */

public class ProductListAdapter extends ArrayAdapter<ProductListModel>{

    private Context context;
    private int serialNo = 1;
    private ArrayList<ProductListModel> products;

    public ProductListAdapter(@NonNull Context context,@NonNull ArrayList<ProductListModel> objects) {
        super(context, R.layout.custom_product_list_lv, objects);
        this.context = context;
        products = objects;
    }


    class ViewHolder{
        TextView serialNoTv;
        TextView pPriceTv;
        TextView pNameTv;
        TextView pBrandTv;
        TextView pSizeTv;
        TextView pAmountTv;
        TextView priceTotalTv;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.custom_product_list_lv,parent,false);

            holder.serialNoTv = (TextView) convertView.findViewById(R.id.listIdTv);
            holder.pPriceTv = (TextView) convertView.findViewById(R.id.pPriceTv);
            holder.pNameTv = (TextView) convertView.findViewById(R.id.pNameTv);
            holder.pBrandTv = (TextView) convertView.findViewById(R.id.pBrandTv);
            holder.pSizeTv = (TextView) convertView.findViewById(R.id.pSizeTv);
            holder.pAmountTv = (TextView) convertView.findViewById(R.id.pAmountTv);
            holder.priceTotalTv = (TextView) convertView.findViewById(R.id.pTotalPriceTv);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.serialNoTv.setText((serialNo++) +"");
        holder.pPriceTv.setText(products.get(position).getpPrice()+"Tk");
        holder.pNameTv.setText(products.get(position).getpName());
        holder.pBrandTv.setText(products.get(position).getpBrand());
        holder.pSizeTv.setText(products.get(position).getpSize());
        holder.pAmountTv.setText(products.get(position).getpSelectQuantity()+" "+products.get(position).getpUnit());
        holder.priceTotalTv.setText(products.get(position).getPriceTotal()+"Tk");


        return convertView;
    }
}
