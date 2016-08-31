package br.com.lucianomedeiros.candidatos2016.ui.model;

/**
 * Created by Luciano on 31/08/2016.
 */
public class Estado {

    private String sigla;
    private String descricao;

    public Estado(String sigla, String descricao) {
        this.sigla = sigla;
        this.descricao = descricao;
    }

    public String getSigla() {
        return sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUrl() {
        return "http://divulgacandcontas.tse.jus.br/divulga/images/"+sigla+".png";
    }
}
