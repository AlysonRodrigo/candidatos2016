package br.com.lucianomedeiros.candidatos2016;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.lucianomedeiros.candidatos2016.ws.ServiceGenerator;
import br.com.lucianomedeiros.candidatos2016.ws.TSEClient;
import br.com.lucianomedeiros.candidatos2016.ws.model.Municipio;
import br.com.lucianomedeiros.candidatos2016.ws.model.RetornoMunicipios;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView textView = (TextView) findViewById(R.id.text);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            TSEClient client = ServiceGenerator.createService(TSEClient.class);
            Call<RetornoMunicipios> call = client.listarMunicipios("PE");
            call.enqueue(new Callback<RetornoMunicipios>() {
                @Override
                public void onResponse(Call<RetornoMunicipios> call, Response<RetornoMunicipios> response) {
                    if (response.isSuccessful()) {
                        List<Municipio> municipios = response.body().getMunicipios();
                        StringBuilder sb = new StringBuilder();
                        for (Municipio municipio : municipios) {
                            sb.append(", ").append(municipio.getNome());
                        }
                        textView.setText(sb.toString());
                    } else {
                        Snackbar.make(view, "Retorno com problemas", Snackbar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<RetornoMunicipios> call, Throwable t) {
                    Snackbar.make(view, "Erro ao consultar municípios", Snackbar.LENGTH_LONG).show();
                    t.printStackTrace();
                }
            });
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
