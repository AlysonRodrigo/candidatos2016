package br.com.lucianomedeiros.candidatos2016.ui.util;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
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
                .into(imageView);
    }

    @BindingAdapter({"android:src", "placeholder"})
    public static void setImageUrl(ImageView imageView, String url, Drawable placeholder) {
        Picasso.with(imageView.getContext())
                .load(url)
                .placeholder(placeholder)
                .into(imageView);
    }

    @BindingAdapter({"android:src", "alpha"})
    public static void setImageUrl(ImageView imageView, String url, float alpha) {
        Picasso.with(imageView.getContext())
                .load(url)
                .transform(new AlphaTransformation(alpha))
                .into(imageView);
    }

    @BindingAdapter({"android:src", "alpha", "placeholder"})
    public static void setImageUrl(ImageView imageView, String url, float alpha, Drawable placeholder) {
        Picasso.with(imageView.getContext())
                .load(url)
                .placeholder(placeholder)
                .transform(new AlphaTransformation(alpha))
                .into(imageView);
    }
}
