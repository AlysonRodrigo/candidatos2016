package br.com.lucianomedeiros.candidatos2016;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import br.com.lucianomedeiros.candidatos2016.databinding.ActivityEstadoBinding;
import br.com.lucianomedeiros.candidatos2016.ui.adapters.MunicipiosAdapter;
import br.com.lucianomedeiros.candidatos2016.ui.model.Estado;
import br.com.lucianomedeiros.candidatos2016.util.Constantes;
import br.com.lucianomedeiros.candidatos2016.ws.ServiceGenerator;
import br.com.lucianomedeiros.candidatos2016.ws.TSEClient;
import br.com.lucianomedeiros.candidatos2016.ws.model.RetornoMunicipios;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EstadoActivity extends AppCompatActivity {

    public static final String ESTADO = "ESTADO";

    ActivityEstadoBinding binding;
    Estado estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_estado);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        estado = Constantes.ESTADOS[getIntent().getIntExtra(ESTADO, 0)];
        binding.setEstado(estado);
        binding.refreshLayout.setEnabled(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setHasFixedSize(true);
        binding.executePendingBindings();

        loadMunicipios();
    }

    private void loadMunicipios() {
        Call<RetornoMunicipios> call = ServiceGenerator.createService(TSEClient.class).listarMunicipios(estado.getSigla());
        binding.refreshLayout.setRefreshing(true);
        call.enqueue(new Callback<RetornoMunicipios>() {
            @Override
            public void onResponse(Call<RetornoMunicipios> call, Response<RetornoMunicipios> response) {
                binding.refreshLayout.setRefreshing(false);
                if (response.isSuccessful()) {
                    binding.recyclerView.setAdapter(new MunicipiosAdapter(response.body().getMunicipios()));
                }
            }

            @Override
            public void onFailure(Call<RetornoMunicipios> call, Throwable t) {
                binding.refreshLayout.setRefreshing(false);
            }
        });
    }
}
