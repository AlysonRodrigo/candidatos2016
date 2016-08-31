package br.com.lucianomedeiros.candidatos2016.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import br.com.lucianomedeiros.candidatos2016.databinding.ItemEstadoBinding;
import br.com.lucianomedeiros.candidatos2016.ui.model.Estado;
import br.com.lucianomedeiros.candidatos2016.util.Constantes;

/**
 * Created by Luciano on 31/08/2016.
 */
public class EstadosAdapter extends RecyclerView.Adapter<EstadosAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemEstadoBinding binding = ItemEstadoBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);
        ViewHolder vh = new ViewHolder(binding);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Estado estado = Constantes.ESTADOS[position];
        holder.binding.setEstado(estado);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return Constantes.ESTADOS.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ItemEstadoBinding binding;

        public ViewHolder(ItemEstadoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
