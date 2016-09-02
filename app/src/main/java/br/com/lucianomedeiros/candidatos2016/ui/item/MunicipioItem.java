package br.com.lucianomedeiros.candidatos2016.ui.item;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.ArrayList;
import java.util.List;

import br.com.lucianomedeiros.candidatos2016.R;
import br.com.lucianomedeiros.candidatos2016.databinding.ItemMunicipioBinding;
import br.com.lucianomedeiros.candidatos2016.ws.model.Municipio;

/**
 * Created by 08140905402 on 02/09/2016.
 */
public class MunicipioItem extends AbstractItem<MunicipioItem, MunicipioItem.ViewHolder> {

    private Municipio municipio;

    public MunicipioItem(Municipio municipio) {
        this.municipio = municipio;
    }

    @Override
    public int getType() {
        return R.id.municipio_item_id;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_municipio;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    @Override
    public void bindView(ViewHolder holder, List payloads) {
        super.bindView(holder, payloads);

        holder.binding.setMunicipio(municipio);
        holder.binding.executePendingBindings();
    }

    public static List<MunicipioItem> convert(List<Municipio> municipios) {
        List<MunicipioItem> items = new ArrayList<>();
        for (Municipio municipio : municipios) {
            items.add(new MunicipioItem(municipio));
        }

        return items;
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        ItemMunicipioBinding binding;

        public ViewHolder(View view) {
            super(view);
            binding = ItemMunicipioBinding.bind(view);
        }
    }
}
