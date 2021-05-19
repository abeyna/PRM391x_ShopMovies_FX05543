package funix.assignment.prm391x_shopmovies_fx05543;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.SignInButton;

public class LoginActivity extends AppCompatActivity {
    private LoginButton mFbBtn;
    private SignInButton mGgBtn;
    private CallbackManager mCallbackManager;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFbBtn = findViewById(R.id.activity_login_fb_login_btn);
        mGgBtn = findViewById(R.id.activity_login_gg_login_btn);
        mCallbackManager = CallbackManager.Factory.create();
    }
}