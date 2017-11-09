package net.a6te.lazycoder.andropos.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.a6te.lazycoder.andropos.R;
import net.a6te.lazycoder.andropos.databinding.FragmentUpdatingStatusBinding;
import net.a6te.lazycoder.andropos.interfaces.UpdatePercentage;

import static net.a6te.lazycoder.andropos.fragments.DueListFragment.TAG;

public class UpdatingStatusFragment extends Fragment{
    private FragmentUpdatingStatusBinding binding;
    private int updateComplete = 0;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_updating_status,container,false);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(messageReceiver
                ,new IntentFilter("completationMessage"));

        return binding.getRoot();
    }
    BroadcastReceiver messageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int percent = intent.getIntExtra("percentage",0);

            updateComplete += percent;
            binding.progress.setText(updateComplete+" %");

        }
    };


}
