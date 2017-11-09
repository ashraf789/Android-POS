package net.a6te.lazycoder.andropos.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.a6te.lazycoder.andropos.R;
import net.a6te.lazycoder.andropos.database.Customer;
import net.a6te.lazycoder.andropos.database.CustomerDue;
import net.a6te.lazycoder.andropos.database.SellsInfo;
import net.a6te.lazycoder.andropos.database.Stock;
import net.a6te.lazycoder.andropos.databinding.FragmentDueDetailsBinding;
import net.a6te.lazycoder.andropos.modelClass.DueDetailsModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static net.a6te.lazycoder.andropos.fragments.DueListFragment.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class DueDetailsFragment extends Fragment {


    private String dueDbId;
    private FragmentDueDetailsBinding binding;
    private CustomerDue customerDue;
    private DueDetailsModel dueDetails;
    private SellsInfo sellsInfo;

    private Customer customerDatabase;
    private String date = "170605";
    private Calendar calendar;
    private View v;

    public DueDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_due_details, container, false);
        dueDbId = getArguments().getString("dueId");
        customerDue = new CustomerDue(getActivity());
        customerDatabase = new Customer(getActivity());
        sellsInfo = new SellsInfo(getActivity());
        dueDetails = customerDue.getDueDetails(dueDbId);

        Log.d(TAG, "onCreateView: "+dueDetails.getSellCode());
        calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy");//example date 06-11-17
        date = dateFormat.format(calendar.getTime());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {
            updateDetails();
        }catch (Exception e){
            Log.d(TAG, "onViewCreated: "+e);
        }

        binding.saveBtn.setOnClickListener(listener);
    }



    public void updateDetails(){
        binding.nameTv.setText(dueDetails.getName());
        binding.phoneTv.setText(dueDetails.getPhone());
        binding.emailTv.setText(dueDetails.getEmail());

        binding.invoiceNoTv.setText(dueDetails.getSellCode());
        binding.totalBIllTv.setText(dueDetails.getTotalAmount());
        binding.payAmountTv.setText(dueDetails.getPaid());
        binding.dateTv.setText(dueDetails.getDate());

        binding.presentDueTv.setText(dueDetails.getDue());
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            v = view;
            int previousPaid = getInteger(binding.newPaidTv.getText().toString());
            int newPaid = getInteger(binding.payAmountEt.getText().toString());
            int previousDue = getInteger(binding.presentDueTv.getText().toString());

            binding.newPayDateTv.setText(date);
            binding.newPaidTv.setText(String.valueOf(previousPaid+newPaid));


            if (previousDue == 0){
                binding.presentDueTv.setText("0");
                showMessage("No Due Left");

            }else{
                binding.presentDueTv.setText(String.valueOf(previousDue - newPaid));

                int totalPaid = getInteger(dueDetails.getPaid())+getInteger(binding.newPaidTv.getText().toString());

                updateDueDatabase((previousDue - newPaid),String.valueOf(totalPaid));
                updateSellDatabase((previousDue - newPaid),String.valueOf(totalPaid),dueDetails.getSellCode());
                updateCustomerDatabase(previousDue - newPaid);

            }

        }
    };

    private void updateCustomerDatabase(int dueAmount) {
        //customer info update
        boolean status = customerDatabase.updateCustomerDueAmount(dueDetails.getCustomerCode(), String.valueOf(dueAmount));
    }

    private void updateSellDatabase(int dueAmount,String paidAmount,String sellCode) {

        boolean status;
        if (dueAmount == 0){
            status = sellsInfo.updateSellDetails(sellCode,paidAmount,"0");
        }else {
            status = sellsInfo.updateSellDetails(sellCode,paidAmount,"1");
        }

        if (status){
            Log.d(TAG, "updateDueDatabase: success");
        }else {
            Log.d(TAG, "updateDueDatabase: faield");

        }
    }

    private void updateDueDatabase(int dueAmount, String paidAmount) {

        if (dueAmount == 0){
            customerDue.deleteDue(dueDbId);
        }else {
            customerDue.updateDueDetails(String.valueOf(dueAmount),paidAmount,dueDbId);
        }
    }

    public void showMessage(String str){
        Snackbar.make(v, str,Snackbar.LENGTH_SHORT).show();
    }

    public int getInteger(String str){
        return Integer.parseInt(str);
    }
}
