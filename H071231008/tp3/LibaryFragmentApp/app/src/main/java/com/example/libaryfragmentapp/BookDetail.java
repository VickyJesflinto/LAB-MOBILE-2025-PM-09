package com.example.libaryfragmentapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.android.material.chip.Chip;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BookDetail extends AppCompatActivity {
    private ImageView bookCoverImage;
    private TextView bookTitle, bookAuthor, bookRatingText, bookYear, bookBlurb, bookReview;
    private Chip bookGenreChip;
    String currentBookTitle;
    private RatingBar ratingBar;
    private FloatingActionButton fabLike;
    private boolean isLiked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bookCoverImage = findViewById(R.id.book_cover_image);
        bookTitle = findViewById(R.id.book_title);
        bookAuthor = findViewById(R.id.book_author);
        bookRatingText = findViewById(R.id.book_rating_text);
        bookYear = findViewById(R.id.book_year);
        bookBlurb = findViewById(R.id.book_blurb);
        bookReview = findViewById(R.id.book_review);
        bookGenreChip = findViewById(R.id.book_genre_chip);
        ratingBar = findViewById(R.id.book_rating_bar);
        fabLike = findViewById(R.id.fab_like);

        Bundle extras = getIntent().getExtras();
        populateBookDetails(extras);

        setupReviewSubmission();
        setupLikeButton();
    }

    private void populateBookDetails(Bundle extras) {
        String title = extras.getString("title", "");
        String author = extras.getString("author", "");
        String blurb = extras.getString("blurb", "");
        String genre = extras.getString("genre", "");
        String review = extras.getString("review", "");
        String imageUri = extras.getString("imageUri", "");
        int year = extras.getInt("year", 0);
        isLiked = extras.getBoolean("liked", false);
        int rating = extras.getInt("rating", 0);

        bookTitle.setText(title);
        bookAuthor.setText(author);
        bookRatingText.setText(rating + "/5");
        bookYear.setText(String.valueOf(year));
        currentBookTitle = extras.getString("title", "");
        bookBlurb.setText(blurb);
        bookReview.setText(review);
        bookGenreChip.setText(genre);
        ratingBar.setRating(rating);

        updateLikeButtonIcon();

        Glide.with(this)
                    .load(imageUri)
                    .placeholder(R.drawable.placeholder_image)
                    .into(bookCoverImage);

    }

    private void setupLikeButton() {
        fabLike.setOnClickListener(v -> {
            isLiked = !isLiked;
            updateLikeButtonIcon();
            for (Book book : DataBook.bookList) {
                if (book.title.equals(currentBookTitle)) {
                    book.isLiked = isLiked;
                    break;
                }
            }
        });
    }

    private void updateLikeButtonIcon() {
        if (isLiked) {
            fabLike.setImageResource(R.drawable.ic_favorite);
        } else {
            fabLike.setImageResource(R.drawable.ic_favorite_border);
        }
    }

    private void setupReviewSubmission() {
        RatingBar userRatingBar = findViewById(R.id.user_rating_bar);
        EditText userReviewInput = findViewById(R.id.user_review_input);
        Button submitReviewButton = findViewById(R.id.submit_review_button);

        submitReviewButton.setOnClickListener(v -> {
            int userRating = (int) userRatingBar.getRating();
            String userReview = userReviewInput.getText().toString();

            if (userRating == 0 || userReview.isEmpty()) {
                Toast.makeText(this, "Please provide a rating and review", Toast.LENGTH_SHORT).show();
                return;
            }

            for (Book book : DataBook.bookList) {
                if (book.title.equals(currentBookTitle)) {
                    book.rating = userRating;
                    book.review = userReview;
                    break;
                }
            }

            ratingBar.setRating(userRating);
            bookReview.setText(userReview);
//            clear
            userRatingBar.setRating(0);
            userReviewInput.setText("");

            Toast.makeText(this, "Review submitted successfully", Toast.LENGTH_SHORT).show();
        });
    }
}