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
import net.a6te.lazycoder.andropos.modelClass.InvoiceLvModel;

import java.util.ArrayList;

/**
 * Created by Programmer on 5/9/2017.
 */

public class InvoiceLVAdapter extends ArrayAdapter<InvoiceLvModel>{

    private Context context;
    private ArrayList<InvoiceLvModel> invoiceList;

    public InvoiceLVAdapter(@NonNull Context context, @NonNull ArrayList<InvoiceLvModel> objects) {
        super(context, R.layout.custom_invoice_details_lv, objects);
        this.context = context;
        invoiceList = objects;
    }


    class ViewHolder{
        TextView invoiceSerialTv;
        TextView invoiceProductTv;
        TextView invoiceProductAmountTv;
        TextView invoiceIdTv;
        TextView invoiceProductPriceTv;
        TextView invoiceProductSellPriceTv;
        TextView invoiceProfitTv;
        TextView invoiceDateTv;
        TextView invoiceTotalProfitTv;
        TextView invoiceCustomerTv;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_invoice_details_lv,parent,false);

            holder.invoiceSerialTv = (TextView) convertView.findViewById(R.id.invoiceSerialTv);
            holder.invoiceProductTv = (TextView) convertView.findViewById(R.id.invoiceProductTv);
            holder.invoiceProductAmountTv = (TextView) convertView.findViewById(R.id.invoiceProductAmountTv);
            holder.invoiceIdTv = (TextView) convertView.findViewById(R.id.invoiceIdTv);
            holder.invoiceProductPriceTv = (TextView) convertView.findViewById(R.id.invoiceProductPriceTv);
            holder.invoiceProductSellPriceTv = (TextView) convertView.findViewById(R.id.invoiceProductSellPriceTv);
            holder.invoiceProfitTv = (TextView) convertView.findViewById(R.id.invoiceProfitTv);
            holder.invoiceDateTv = (TextView) convertView.findViewById(R.id.invoiceDateTv);
            holder.invoiceTotalProfitTv = (TextView) convertView.findViewById(R.id.invoiceTotalProfitTv);
            holder.invoiceCustomerTv = (TextView) convertView.findViewById(R.id.invoiceCustomerTv);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.invoiceSerialTv.setText(invoiceList.get(position).getInvoiceSerial());
        holder.invoiceProductTv.setText(invoiceList.get(position).getInvoiceProduct());
        holder.invoiceProductAmountTv.setText(invoiceList.get(position).getInvoiceProductAmount());
        holder.invoiceIdTv.setText(invoiceList.get(position).getInvoiceId());
        holder.invoiceProductPriceTv.setText(invoiceList.get(position).getInvoiceProductPrice());
        holder.invoiceProductSellPriceTv.setText(invoiceList.get(position).getInvoiceProductSellPrice());
        holder.invoiceProfitTv.setText(invoiceList.get(position).getInvoiceProfit());
        holder.invoiceDateTv.setText(invoiceList.get(position).getInvoiceDate());
        holder.invoiceTotalProfitTv.setText(invoiceList.get(position).getInvoiceTotalProfit());
        holder.invoiceCustomerTv.setText(invoiceList.get(position).getInvoiceCustomer());

        return convertView;

    }
}
