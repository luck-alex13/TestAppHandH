package com.example.user.testapphandh;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.user.testapphandh.databinding.ActivityLoginBinding;
import com.example.user.testapphandh.network.BaseRequest;
import com.example.user.testapphandh.network.Const;
import com.example.user.testapphandh.network.WeatherClient;

import io.reactivex.functions.Consumer;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        setUpToolbar(binding.toolbar);

        LoginViewModel viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        binding.content.emailSignInButton.setOnClickListener(v -> {
            viewModel.loginClicked(binding.content.email, binding.content.password);

            WeatherClient.getInstance().getWeatherInCity(Const.SPB_CITY_ID)
                    .subscribe(new Consumer<BaseRequest>() {
                        @Override
                        public void accept(BaseRequest baseRequest) throws Exception {

                        }
                    });
        });
    }

    private void setUpToolbar(Toolbar toolbar) {
        toolbar.setTitle(R.string.title_activity_login);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.create: {
                // do smth
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
