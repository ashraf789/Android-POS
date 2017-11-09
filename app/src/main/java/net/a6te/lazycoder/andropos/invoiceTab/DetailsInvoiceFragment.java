package net.a6te.lazycoder.andropos.invoiceTab;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.a6te.lazycoder.andropos.R;
import net.a6te.lazycoder.andropos.adapters.InvoiceLVAdapter;
import net.a6te.lazycoder.andropos.databinding.FragmentInvoiceDetailsBinding;
import net.a6te.lazycoder.andropos.modelClass.InvoiceLvModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsInvoiceFragment extends Fragment {


    public DetailsInvoiceFragment() {
        // Required empty public constructor
    }


    private FragmentInvoiceDetailsBinding binder;
    private InvoiceLVAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binder = DataBindingUtil.inflate(inflater,R.layout.fragment_invoice_details, container, false);
        adapter = new InvoiceLVAdapter(getActivity(),new InvoiceLvModel().getAllSampleInvoiceData());
        return binder.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binder.detailsInLv.setAdapter(adapter);
    }
}
