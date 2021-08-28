package funix.assignment.prm391x_shopmovies_fx05543;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 *  Main Activity store 2 fragment Movies and Profile.
 */
public class MainActivity extends AppCompatActivity {
    private static MainActivity mInstance;
    private BottomNavigationView bottomNav;

    /**
     * Handles Navigation options selected event
     */
    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        // Showing movies fragment
                        case R.id.menu_movies:
                            selectedFragment = new MoviesFragment();
                            break;

                        // Showing profile fragment
                        case R.id.menu_profile:
                            Intent intent = getIntent();
                            Bundle b = intent.getExtras();

                            selectedFragment = new ProfileFragment();
                            selectedFragment.setArguments(b);
                            break;
                        case R.id.menu_logout:
                            logoutGoogle();
                            logoutFacebook();
                            break;
                    }

                    // Change to selected fragment between Movies and Profile, and log-out to Login Activity if user choose LogOut.
                    if (selectedFragment != null) {
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment, selectedFragment)
                                .commit();
                    }

                    return true;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInstance = this;

        bottomNav = findViewById(R.id.activity_main_bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        // Movie fragment loaded by default
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment,
                        new MoviesFragment())
                .commit();

    }

    /**
     * Facebook log out event
     */
    private void logoutFacebook() {

        // Check if user have logged in or not
        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(),
                "/me/permissions/",
                null,
                HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {

                LoginManager.getInstance().logOut();

            }
        }).executeAsync();
    }

    /**
     * Google log out event
     */
    private void logoutGoogle() {
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Check if google have logged or not
        if (GoogleSignIn.getClient(this, gso) != null) {

            GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
            mGoogleSignInClient.signOut()
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            moveToLoginActivity();
                        }
                    });
        }
    }


    /**
     * After logged out, moves back to log in activity
     */
    private void moveToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}