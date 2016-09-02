package br.com.lucianomedeiros.candidatos2016.ui.adapters;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.parceler.Parcels;

import br.com.lucianomedeiros.candidatos2016.EstadoActivity;
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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Estado estado = Constantes.ESTADOS[position];
        holder.binding.setEstado(estado);
        holder.binding.sigla.setTextColor(Color.WHITE);
        holder.binding.descricao.setTextColor(Color.WHITE);
        holder.binding.frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EstadoActivity.class);
                intent.putExtra(EstadoActivity.ESTADO, holder.getAdapterPosition());
                view.getContext().startActivity(intent);
            }
        });

        final Palette.PaletteAsyncListener listener = new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch swatch = palette.getLightVibrantSwatch();
                if (swatch != null) {
                    holder.binding.frame.setBackgroundColor(swatch.getRgb());
                    holder.binding.sigla.setTextColor(swatch.getTitleTextColor());
                    holder.binding.descricao.setTextColor(swatch.getBodyTextColor());
                }
            }
        };
        Picasso.with(holder.binding.frame.getContext())
                .load(estado.getUrl())
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        Palette.from(bitmap).generate(listener);
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

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
