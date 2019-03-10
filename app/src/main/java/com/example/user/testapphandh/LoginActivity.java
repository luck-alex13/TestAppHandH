package com.example.user.testapphandh;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

import com.example.user.testapphandh.databinding.ActivityLoginBinding;
import com.example.user.testapphandh.helpers.DialogManager;
import com.example.user.testapphandh.helpers.SnackbarHelper;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        setUpToolbar(binding.toolbar);

        setUpInterface();
    }

    private void setUpInterface(){
        LoginViewModel viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        viewModel.getWeatherInfo().observe(this, mess -> {
            SnackbarHelper.showSnack(binding.getRoot(), mess);
        });

        viewModel.getShowProgress().observe(this, show -> {
            if(show != null && show) DialogManager.Instance.showProgressDialog(LoginActivity.this, R.string.please_wait);
            else DialogManager.Instance.hide();
        });

        binding.content.emailSignInButton.setOnClickListener(v -> {
            viewModel.loginClicked(binding.content.email, binding.content.password);
        });

        binding.content.password.setOnTouchListener((v, event) -> {
            final int DRAWABLE_LEFT = 0;
            final int DRAWABLE_TOP = 1;
            final int DRAWABLE_RIGHT = 2;
            final int DRAWABLE_BOTTOM = 3;

            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (binding.content.password.getRight() -
                        binding.content.password.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    // your action here
                    DialogManager.Instance.showAlert(LoginActivity.this, R.string.pass_help_title, R.string.pass_help,
                            (dialog, which) -> {
                                dialog.dismiss();
                            });
                    return true;
                }
            }
            return false;
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
