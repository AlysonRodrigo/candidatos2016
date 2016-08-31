package br.com.lucianomedeiros.candidatos2016.ui.util;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import br.com.lucianomedeiros.candidatos2016.R;

/**
 * Created by Luciano on 31/08/2016.
 */
public class ImageBinding {

    @BindingAdapter({"android:src"})
    public static void setImageUrl(ImageView imageView, String url) {
        Picasso.with(imageView.getContext())
                .load(url)
                .placeholder(R.drawable.ic_insert_photo)
                .into(imageView);
    }
}
