package funix.assignment.prm391x_shopmovies_fx05543;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MoviesFragment extends Fragment {
    private RecyclerView rcvMovies;
    private MovieAdapter movieAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);

        rcvMovies = (RecyclerView) view.findViewById(R.id.fragment_movies_rcv);
        movieAdapter = new MovieAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvMovies.setLayoutManager(linearLayoutManager);
        movieAdapter.setData(getListUser());
        rcvMovies.setAdapter(movieAdapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        rcvMovies.addItemDecoration(itemDecoration);

        return view;
    }

    private List<String> getListUser() {
        List<String> moviesList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            moviesList.add("Movie " + i);
        }

        return moviesList;
    }
}
