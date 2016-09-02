package br.com.lucianomedeiros.candidatos2016;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.adapters.FastItemAdapter;

import org.parceler.Parcels;

import java.util.List;

import br.com.lucianomedeiros.candidatos2016.databinding.ActivityEstadoBinding;
import br.com.lucianomedeiros.candidatos2016.ui.item.MunicipioItem;
import br.com.lucianomedeiros.candidatos2016.ui.model.Estado;
import br.com.lucianomedeiros.candidatos2016.util.Constantes;
import br.com.lucianomedeiros.candidatos2016.ws.ServiceGenerator;
import br.com.lucianomedeiros.candidatos2016.ws.TSEClient;
import br.com.lucianomedeiros.candidatos2016.ws.model.Municipio;
import br.com.lucianomedeiros.candidatos2016.ws.model.RetornoMunicipios;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EstadoActivity extends AppCompatActivity {

    private static final String TAG = "EstadoActivity";

    ActivityEstadoBinding binding;
    Estado estado;
    List<Municipio> municipios;
    FastItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_estado);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        estado = Constantes.ESTADOS[getIntent().getIntExtra(Constantes.KEY_ESTADO, 0)];
        binding.setEstado(estado);
        binding.refreshLayout.setEnabled(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new FastItemAdapter();
        adapter.withOnClickListener(new FastAdapter.OnClickListener() {
            @Override
            public boolean onClick(View v, IAdapter adapter, IItem item, int position) {
                Intent intent = new Intent(EstadoActivity.this, MunicipioActivity.class);
                intent.putExtra(Constantes.KEY_MUNICIPIO, Parcels.wrap(municipios.get(position)));
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    ActivityOptionsCompat options = ActivityOptionsCompat.
//                            makeSceneTransitionAnimation(EstadoActivity.class, holder.binding.bandeira, "bandeira");
//                    activity.startActivity(intent, options.toBundle());
//                } else {
                    startActivity(intent);
//                }
                return true;
            }
        });

        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerView.setAdapter(adapter);

        if (savedInstanceState == null || !savedInstanceState.containsKey(Constantes.KEY_MUNICIPIOS))
            loadMunicipios();
        else
            restoreMunicipios(savedInstanceState);
    }

    private void restoreMunicipios(Bundle savedInstanceState) {
        municipios = Parcels.unwrap(savedInstanceState.getParcelable(Constantes.KEY_MUNICIPIOS));
        setupAdapter();
    }

    private void setupAdapter() {
        adapter.add(MunicipioItem.convert(municipios));
    }

    private void loadMunicipios() {
        Call<RetornoMunicipios> call = ServiceGenerator.createService(TSEClient.class).listarMunicipios(estado.getSigla());
        binding.refreshLayout.setRefreshing(true);
        call.enqueue(new Callback<RetornoMunicipios>() {
            @Override
            public void onResponse(Call<RetornoMunicipios> call, Response<RetornoMunicipios> response) {
                if (binding == null) return;

                binding.refreshLayout.setRefreshing(false);
                if (response.isSuccessful()) {
                    municipios = response.body().getMunicipios();
                    setupAdapter();
                } else {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<RetornoMunicipios> call, Throwable t) {
                if (binding == null) return;

                binding.refreshLayout.setRefreshing(false);
                showError();
            }
        });
    }

    private void showError() {
        Snackbar.make(binding.recyclerView, R.string.error_loading_municipios, Snackbar.LENGTH_LONG)
                .setAction(R.string.reload, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loadMunicipios();
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
        if (municipios != null) {
            outState.putParcelable(Constantes.KEY_MUNICIPIOS, Parcels.wrap(municipios));
        }
    }
}
