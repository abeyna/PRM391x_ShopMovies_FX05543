package funix.assignment.prm391x_shopmovies_fx05543;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MoviesFragment extends Fragment {
    private static final String URL = "https://dinhnguyenngoc.github.io/happycoding/json/movies_2017.json";
    private MovieAdapter mAdapter;
    private ArrayList<Movie> mMoviesList;

    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
       
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(R.string.movies_text);

        View rootView = inflater.inflate(R.layout.fragment_movies, container, false);

        RecyclerView mRecyclerView = rootView.findViewById(R.id.fragment_movies_rcv);
        mMoviesList = new ArrayList<>();
        fetchMoviesItem();

        // Adding the RecyclerView adapter
        mAdapter = new MovieAdapter(getActivity(), getContext(), mMoviesList,
                AccessToken.getCurrentAccessToken() != null);

        // Set layout to gridLayout with 3 columns
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);

        return rootView;
    }

    /**
     * Fetching movies item from JSON
     */
    private void fetchMoviesItem() {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                URL, null, new Response.Listener<JSONArray>() {
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    // creating a new json object and
                    // getting each object from json array.
                    try {
                        JSONObject responseObj = response.getJSONObject(i);

                        String imgUrl = responseObj.getString("image");
                        String title = responseObj.getString("title");
                        String price = responseObj.getString("price");
                        Movie movie = new Movie(imgUrl, title, price);
                        Log.d("FetchMoviesItem", "onResponse: " + movie.toString());
                        mMoviesList.add(movie);
                        mAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonArrayRequest);
    }
}
