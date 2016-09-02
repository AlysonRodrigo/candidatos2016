package br.com.lucianomedeiros.candidatos2016.ws.model;

import java.util.List;

/**
 * Created by 08140905402 on 02/09/2016.
 */
public class RetornoCargos {

    private UnidadeEleitoral unidadeEleitoralDTO;
    private List<Cargo> cargos;

    public UnidadeEleitoral getUnidadeEleitoralDTO() {
        return unidadeEleitoralDTO;
    }

    public void setUnidadeEleitoralDTO(UnidadeEleitoral unidadeEleitoralDTO) {
        this.unidadeEleitoralDTO = unidadeEleitoralDTO;
    }

    public List<Cargo> getCargos() {
        return cargos;
    }

    public void setCargos(List<Cargo> cargos) {
        this.cargos = cargos;
    }
}
