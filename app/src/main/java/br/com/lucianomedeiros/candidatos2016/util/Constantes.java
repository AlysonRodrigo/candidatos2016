package br.com.lucianomedeiros.candidatos2016.util;

import br.com.lucianomedeiros.candidatos2016.ui.model.Estado;

/**
 * Created by Luciano on 31/08/2016.
 */
public class Constantes {

    public static final Estado[] ESTADOS = {
            new Estado("AC", "Acre"),
            new Estado("AL", "Alagoas"),
            new Estado("AP", "Amapá"),
            new Estado("AM", "Amazonas"),
            new Estado("BA", "Bahia"),
            new Estado("CE", "Ceará"),
            new Estado("DF", "Distrito Federal"),
            new Estado("ES", "Espírito Santo"),
            new Estado("GO", "Goiás"),
            new Estado("MA", "Maranhão"),
            new Estado("MT", "Mato Grosso"),
            new Estado("MS", "Mato Grosso do Sul"),
            new Estado("MG", "Minas Gerais"),
            new Estado("PA", "Pará"),
            new Estado("PB", "Paraíba"),
            new Estado("PR", "Paraná"),
            new Estado("PE", "Pernambuco"),
            new Estado("PI", "Piauí"),
            new Estado("RJ", "Rio de Janeiro"),
            new Estado("RN", "Rio Grande do Norte"),
            new Estado("RS", "Rio Grande do Sul"),
            new Estado("RO", "Rondônia"),
            new Estado("RR", "Roraima"),
            new Estado("SC", "Santa Catarina"),
            new Estado("SP", "São Paulo"),
            new Estado("SE", "Sergipe"),
            new Estado("TO", "Tocantins")
    };

    public static final String KEY_ESTADO = "estado";
    public static final String KEY_MUNICIPIOS = "municipios";
    public static final String KEY_MUNICIPIO = "municipio";
    public static final String KEY_CARGOS = "cargos";
}
