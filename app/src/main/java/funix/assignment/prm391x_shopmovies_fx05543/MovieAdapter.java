package funix.assignment.prm391x_shopmovies_fx05543;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<String> mMoviesList;

    public void setData(List<String> list) {
        this.mMoviesList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        String movieName = mMoviesList.get(position);
        holder.textViewMovie.setText(movieName);
    }

    @Override
    public int getItemCount() {
        if (mMoviesList != null)
            return mMoviesList.size();
        return 0;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewMovie;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMovie = itemView.findViewById(R.id.movie_item);
        }
    }
}
