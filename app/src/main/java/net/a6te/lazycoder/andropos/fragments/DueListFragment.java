package net.a6te.lazycoder.andropos.fragments;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import net.a6te.lazycoder.andropos.R;
import net.a6te.lazycoder.andropos.adapters.DueLvAdapter;
import net.a6te.lazycoder.andropos.database.CustomerDue;
import net.a6te.lazycoder.andropos.databinding.FragmentDueListBinding;
import net.a6te.lazycoder.andropos.interfaces.DueLvInterface;
import net.a6te.lazycoder.andropos.modelClass.DueModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DueListFragment extends Fragment {

    public static final String TAG = "androPos";
    private DueLvInterface dueLvInterface;
    private FragmentDueListBinding binder;
    private DueLvAdapter adapter;
    private ArrayList<DueModel> dues;
    private Context context;
    private CustomerDue customerDue;
    public DueListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binder = DataBindingUtil.inflate(inflater,R.layout.fragment_due_list, container, false);
        customerDue = new CustomerDue(getActivity());
        dues = customerDue.getDueList();
        adapter = new DueLvAdapter(getActivity(),dues);
        dueLvInterface = (DueLvInterface) context;

        return binder.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (customerDue.haveDue()){
            binder.dueLv.setVisibility(View.VISIBLE);
            binder.warning.setVisibility(View.GONE);

            binder.dueLv.setAdapter(adapter);
            binder.dueLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    dueLvInterface.dueDetails(dues.get(position).getDueId());
                }
            });
        }else {
            binder.dueLv.setVisibility(View.GONE);
            binder.warning.setVisibility(View.VISIBLE);
        }


    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
