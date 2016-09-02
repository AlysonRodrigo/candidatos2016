package br.com.lucianomedeiros.candidatos2016.ui.adapters;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.lucianomedeiros.candidatos2016.EstadoActivity;
import br.com.lucianomedeiros.candidatos2016.databinding.ItemEstadoBinding;
import br.com.lucianomedeiros.candidatos2016.ui.model.Estado;
import br.com.lucianomedeiros.candidatos2016.util.Constantes;

/**
 * Created by Luciano on 31/08/2016.
 */
public class EstadosAdapter extends RecyclerView.Adapter<EstadosAdapter.ViewHolder> {

    private Activity activity;

    public EstadosAdapter(Activity activity) {
        this.activity = activity;
    }

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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Estado estado = Constantes.ESTADOS[position];
        holder.binding.setEstado(estado);
        holder.binding.frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EstadoActivity.class);
                intent.putExtra(Constantes.KEY_ESTADO, holder.getAdapterPosition());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(activity, holder.binding.bandeira, "bandeira");
                    activity.startActivity(intent, options.toBundle());
                } else {
                    activity.startActivity(intent);
                }
            }
        });
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
