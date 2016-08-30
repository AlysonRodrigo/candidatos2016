package br.com.lucianomedeiros.candidatos2016.ws.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 08140905402 on 30/08/2016.
 */
public class RetornoMunicipios {

    private Estado estado;
    private List<Municipio> municipios = new ArrayList<>();

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }
}
