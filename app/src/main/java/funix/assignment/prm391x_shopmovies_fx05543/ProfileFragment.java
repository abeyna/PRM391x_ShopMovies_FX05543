package funix.assignment.prm391x_shopmovies_fx05543;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.facebook.login.widget.ProfilePictureView;

public class ProfileFragment extends Fragment {
    private ProfilePictureView mProfilePictureView;
    private Button mBtnLogout;
    private TextView mTvFirstName, mTvLastName, mTvEmail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        mProfilePictureView = (ProfilePictureView) view.findViewById(R.id.fragment_profile_picture);
        mBtnLogout = (Button) view.findViewById(R.id.fragment_profile_btn_logout);
        mTvEmail = (TextView) view.findViewById(R.id.fragment_profile_tv_email);
        mTvFirstName = (TextView) view.findViewById(R.id.fragment_profile_tv_first_name);
        mTvLastName = (TextView) view.findViewById(R.id.fragment_profile_tv_last_name);

        Context context = getContext().getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("user-data", Context.MODE_PRIVATE);
        mTvFirstName.setText(sharedPreferences.getString("first-name","(unknown)"));
        mTvLastName.setText(sharedPreferences.getString("last-name","(unknown)"));
        mTvEmail.setText(sharedPreferences.getString("email","(unknown)"));

        return view;
    }
}
