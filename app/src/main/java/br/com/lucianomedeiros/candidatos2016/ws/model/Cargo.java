package br.com.lucianomedeiros.candidatos2016.ws.model;

import org.parceler.Parcel;

/**
 * Created by 08140905402 on 02/09/2016.
 */
@Parcel(Parcel.Serialization.BEAN)
public class Cargo {

    private int codigo;
    private String sigla;
    private String nome;
    private int contagem;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getContagem() {
        return contagem;
    }

    public void setContagem(int contagem) {
        this.contagem = contagem;
    }
}
