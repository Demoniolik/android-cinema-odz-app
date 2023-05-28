package com.ssu.odz.cinema.adapter;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.example.fireeats.R;
import com.google.firebase.example.fireeats.databinding.ItemCinemaBinding;
import com.ssu.odz.cinema.model.Cinema;
import com.ssu.odz.cinema.util.CinemaUtil;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

public class CinemaAdapter extends FirestoreAdapter<CinemaAdapter.ViewHolder> {

    public interface OnRestaurantSelectedListener {

        void onRestaurantSelected(DocumentSnapshot restaurant);

    }

    private OnRestaurantSelectedListener mListener;

    public CinemaAdapter(Query query, OnRestaurantSelectedListener listener) {
        super(query);
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(ItemCinemaBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(getSnapshot(position), mListener);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ItemCinemaBinding binding;

        public ViewHolder(ItemCinemaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public ViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(final DocumentSnapshot snapshot,
                         final OnRestaurantSelectedListener listener) {

            Cinema cinema = snapshot.toObject(Cinema.class);
            Resources resources = itemView.getResources();

            // Load image
            Glide.with(binding.restaurantItemImage.getContext())
                    .load(cinema.getPhoto())
                    .into(binding.restaurantItemImage);

            binding.restaurantItemName.setText(cinema.getName());
            binding.restaurantItemRating.setRating((float) cinema.getAvgRating());
            binding.restaurantItemCity.setText(cinema.getCity());
            binding.restaurantItemCategory.setText(cinema.getCategory());
            binding.restaurantItemNumRatings.setText(resources.getString(R.string.fmt_num_ratings,
                    cinema.getNumRatings()));
            binding.restaurantItemPrice.setText(CinemaUtil.getPriceString(cinema));

            // Click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onRestaurantSelected(snapshot);
                    }
                }
            });
        }

    }
}
