package funix.assignment.prm391x_shopmovies_fx05543;

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

        Bundle getUserData = getArguments();
        if(getUserData != null) {
            mTvFirstName.setText(getUserData.getString("first-name"));
            mTvLastName.setText(getUserData.getString("last-name"));
            mTvEmail.setText(getUserData.getString("e-mail"));
        }

        return view;
    }
}
