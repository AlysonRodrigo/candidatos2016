package br.com.lucianomedeiros.candidatos2016;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.mikepenz.fastadapter.adapters.FastItemAdapter;

import org.parceler.Parcels;

import java.util.List;

import br.com.lucianomedeiros.candidatos2016.databinding.ActivityMunicipioBinding;
import br.com.lucianomedeiros.candidatos2016.ui.fragments.CargoFragment;
import br.com.lucianomedeiros.candidatos2016.ui.item.MunicipioItem;
import br.com.lucianomedeiros.candidatos2016.util.Constantes;
import br.com.lucianomedeiros.candidatos2016.ws.ServiceGenerator;
import br.com.lucianomedeiros.candidatos2016.ws.TSEClient;
import br.com.lucianomedeiros.candidatos2016.ws.model.Cargo;
import br.com.lucianomedeiros.candidatos2016.ws.model.Municipio;
import br.com.lucianomedeiros.candidatos2016.ws.model.RetornoCargos;
import br.com.lucianomedeiros.candidatos2016.ws.model.RetornoMunicipios;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MunicipioActivity extends AppCompatActivity {

    private static final String TAG = "MunicipioActivity";

    ActivityMunicipioBinding binding;
    Municipio municipio;
    List<Cargo> cargos;
    TabPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_municipio);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        municipio = Parcels.unwrap(getIntent().getParcelableExtra(Constantes.KEY_MUNICIPIO));
        binding.setMunicipio(municipio);
        binding.refreshLayout.setEnabled(false);
        binding.tabLayout.setVisibility(View.GONE);

        if (savedInstanceState == null || !savedInstanceState.containsKey(Constantes.KEY_CARGOS))
            loadCargos();
        else
            restoreCargos(savedInstanceState);
    }

    private void restoreCargos(Bundle savedInstanceState) {
        cargos = Parcels.unwrap(savedInstanceState.getParcelable(Constantes.KEY_CARGOS));
        setupAdapter();
    }

    private void setupAdapter() {
        pagerAdapter = new TabPagerAdapter(getSupportFragmentManager());
        binding.viewPager.setAdapter(pagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        binding.tabLayout.setVisibility(View.VISIBLE);
    }

    private void loadCargos() {
        Call<RetornoCargos> call = ServiceGenerator.createService(TSEClient.class).listarCargos(municipio.getCodigo());
        binding.refreshLayout.setRefreshing(true);
        call.enqueue(new Callback<RetornoCargos>() {
            @Override
            public void onResponse(Call<RetornoCargos> call, Response<RetornoCargos> response) {
                if (binding == null) return;

                binding.refreshLayout.setRefreshing(false);
                if (response.isSuccessful()) {
                    cargos = response.body().getCargos();
                    setupAdapter();
                } else {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<RetornoCargos> call, Throwable t) {
                if (binding == null) return;

                binding.refreshLayout.setRefreshing(false);
                showError();
            }
        });
    }

    private void showError() {
        Snackbar.make(binding.viewPager, R.string.error_loading_municipios, Snackbar.LENGTH_LONG)
                .setAction(R.string.reload, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loadCargos();
                    }
                })
                .show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_estado, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (cargos != null) {
            outState.putParcelable(Constantes.KEY_CARGOS, Parcels.wrap(cargos));
        }
    }

    private class TabPagerAdapter extends FragmentStatePagerAdapter {

        public TabPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return CargoFragment.newInstance(cargos.get(position));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return cargos.get(position).getNome();
        }

        @Override
        public int getCount() {
            return cargos.size();
        }
    }
}
