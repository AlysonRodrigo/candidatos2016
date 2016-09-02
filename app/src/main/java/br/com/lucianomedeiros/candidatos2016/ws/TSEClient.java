package br.com.lucianomedeiros.candidatos2016.ws;

import br.com.lucianomedeiros.candidatos2016.ws.model.RetornoCargos;
import br.com.lucianomedeiros.candidatos2016.ws.model.RetornoMunicipios;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by 08140905402 on 30/08/2016.
 */
public interface TSEClient {

    @GET("eleicao/buscar/{UF}/2/municipios")
    Call<RetornoMunicipios> listarMunicipios(@Path("UF") String uf);

    @GET("eleicao/listar/municipios/2/{id}/cargos")
    Call<RetornoCargos> listarCargos(@Path("id") String id);
}
