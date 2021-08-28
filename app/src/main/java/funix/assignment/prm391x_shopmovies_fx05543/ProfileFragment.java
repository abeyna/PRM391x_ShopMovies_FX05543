package funix.assignment.prm391x_shopmovies_fx05543;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.squareup.picasso.Picasso;

/**
 *  Profile Fragment.
 */
public class ProfileFragment extends Fragment {
    /** Root view.*/
    private View mRootView;

    /** User's avatar.*/
    private ImageView mProfileImg;

    /** User's information.*/
    private TextView mUserNameTxt, mEmailTxt, mIdTxt;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(R.string.profile_text);
        mRootView = inflater.inflate(R.layout.fragment_profile, container, false);
        mProfileImg = mRootView.findViewById(R.id.profile_image);
        mUserNameTxt = mRootView.findViewById(R.id.profile_name_txt);
        mEmailTxt = mRootView.findViewById(R.id.profile_email_txt);
        mIdTxt = mRootView.findViewById(R.id.profile_id_txt);

        // Retrieves Google profile data
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            Picasso.get().load(personPhoto).into(mProfileImg);
            mUserNameTxt.setText(personName);
            mEmailTxt.setText("Email: " + personEmail);
            mIdTxt.setText("User Id: " + personId);

            Log.d("GOOGLE", "onCreateView: email: " + personEmail);
        } else {
            Context context = getContext().getApplicationContext();
            SharedPreferences sharedPreferences = context.getSharedPreferences("user-data", Context.MODE_PRIVATE);

            String personId = sharedPreferences.getString("id","");
            String personName = sharedPreferences.getString("name","");
            String personEmail = sharedPreferences.getString("email","");

            mIdTxt.setText("User ID: " + personId);
            Picasso.get().load("https://graph.facebook.com/" + personId + "/picture?width=400&height=400").into(mProfileImg);
            mUserNameTxt.setText(personName);
            mEmailTxt.setText("Email: " + personEmail);
        }
        return mRootView;
    }
}
