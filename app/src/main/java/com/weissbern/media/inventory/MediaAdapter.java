package com.weissbern.media.inventory;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * class MediaAdapter
 */
public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.MediaViewHolder> {

    private Context mCtx;
    private List<Media> mediaList;

    /**
     * Class constructor.
     */
    public MediaAdapter(Context mCtx, List<Media> mediaList) {
        this.mCtx = mCtx;
        this.mediaList = mediaList;
    }

    @NonNull
    @Override
    public MediaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(viewType, parent, false);
        return new MediaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MediaViewHolder holder, int position) {
        Media mediaItem = mediaList.get(position);
        holder.textViewTitle.setText(mediaItem.getTitle());
        holder.textViewTypes.setText(mediaItem.getTypes());
        holder.textViewDesc.setText(mediaItem.getNotes());
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(mediaItem.getImage()));
    }

    @Override
    public int getItemCount() {
        return mediaList.size();
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.media_item;
    }

    /**
     * class MediaViewHolder
     */
    class MediaViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewTitle, textViewTypes, textViewDesc;

        /**
         * Class constructor.
         */
        public MediaViewHolder(View itemView) {
            super(itemView);
            //TODO add/remove media item fields as needed.
            imageView = itemView.findViewById(R.id.media_icon);
            textViewTitle = itemView.findViewById(R.id.media_title);
            textViewTypes = itemView.findViewById(R.id.media_types);
            textViewDesc = itemView.findViewById(R.id.media_description);
        }

    }

}
