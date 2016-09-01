package br.com.lucianomedeiros.candidatos2016;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;

import br.com.lucianomedeiros.candidatos2016.databinding.ActivityEstadoBinding;
import br.com.lucianomedeiros.candidatos2016.ui.model.Estado;

public class EstadoActivity extends AppCompatActivity {

    ActivityEstadoBinding binding;
    Estado estado = new Estado("PE", "Pernambuco");

    TabPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_estado);
        setSupportActionBar(binding.toolbar);
        setTitle(estado.getDescricao());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new TabPagerAdapter(getSupportFragmentManager());
        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }

    private class TabPagerAdapter extends FragmentStatePagerAdapter {

        public TabPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:
                default:
                    return new Fragment();
                case 1:
                    return new Fragment();
                case 2:
                    return new Fragment();
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                default:
                    return getString(R.string.prefeito);
                case 1:
                    return getString(R.string.vice);
                case 2:
                    return getString(R.string.vereador);
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
