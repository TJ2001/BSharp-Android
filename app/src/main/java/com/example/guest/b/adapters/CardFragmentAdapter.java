package com.example.guest.b.adapters;

/**
 * Created by Guest on 12/21/16.
 */
public class CardFragmentAdapter {

    public void bindCard(Card card) {
        mCardImageView = (ImageView) mView.findViewById(R.id.cardImageView);
        ImageView cardImageView = (ImageView) mView.findViewById(R.id.cardImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.nameTextView);
        TextView cityTextView = (TextView) mView.findViewById(R.id.cityTextView);
        TextView organizerTextView = (TextView) mView.findViewById(R.id.organizerTextView);

        Picasso.with(mContext)
                .load(card.getPhotoLink())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(cardImageView);

        nameTextView.setText(card.getName());
        cityTextView.setText("City: " + card.getCity());
        organizerTextView.setText("Organizer: " + card.getOrganizer());
    }

}