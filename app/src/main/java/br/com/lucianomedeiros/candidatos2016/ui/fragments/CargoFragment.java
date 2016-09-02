package br.com.lucianomedeiros.candidatos2016.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.parceler.Parcels;

import br.com.lucianomedeiros.candidatos2016.R;
import br.com.lucianomedeiros.candidatos2016.ws.model.Cargo;

public class CargoFragment extends Fragment {
    private static final String TAG = "CargoFragment";
    private static final String ARG_CARGO = "cargo";

    // TODO: Rename and change types of parameters
    private Cargo cargo;

    public CargoFragment() {
        // Required empty public constructor
    }

    public static CargoFragment newInstance(Cargo cargo) {
        CargoFragment fragment = new CargoFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_CARGO, Parcels.wrap(cargo));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cargo = Parcels.unwrap(getArguments().getParcelable(ARG_CARGO));
            Log.d(TAG, "onCreate: " + cargo.getNome());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prefeito, container, false);
    }
}
