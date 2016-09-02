package br.com.lucianomedeiros.candidatos2016.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import br.com.lucianomedeiros.candidatos2016.databinding.ItemMunicipioBinding;
import br.com.lucianomedeiros.candidatos2016.ws.model.Municipio;

/**
 * Created by Luciano on 31/08/2016.
 */
public class MunicipiosAdapter extends RecyclerView.Adapter<MunicipiosAdapter.ViewHolder> {

    private List<Municipio> municipios;

    public MunicipiosAdapter(List<Municipio> municipios) {
        this.municipios = municipios;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemMunicipioBinding binding = ItemMunicipioBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);
        ViewHolder vh = new ViewHolder(binding);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.binding.setMunicipio(municipios.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return municipios.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ItemMunicipioBinding binding;

        public ViewHolder(ItemMunicipioBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
