package br.com.lucianomedeiros.candidatos2016.ui.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.lucianomedeiros.candidatos2016.R;
import br.com.lucianomedeiros.candidatos2016.databinding.FragmentEstadosBinding;
import br.com.lucianomedeiros.candidatos2016.ui.adapters.EstadosAdapter;


public class EstadosFragment extends Fragment {

    public EstadosFragment() {
        // Required empty public constructor
    }

    public static EstadosFragment newInstance() {
        EstadosFragment fragment = new EstadosFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentEstadosBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_estados, container, false);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.recyclerView.setAdapter(new EstadosAdapter());

        return binding.getRoot();
    }

}
