package com.example.libaryfragmentapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AddFragment extends Fragment {
    private ImageView bookImage;
    Spinner spinnerGenre;
    private EditText editTitle, editAuthor, editGenre, editYear, editBlurb;
    private Button btnSelectImage, btnAddBook;
    private Uri selectedImageUri = null;

    private final ActivityResultLauncher<Intent> imagePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == getActivity().RESULT_OK && result.getData() != null) {
                    selectedImageUri = result.getData().getData();
                    Glide.with(this).load(selectedImageUri).into(bookImage);
                }
            });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        bookImage = view.findViewById(R.id.book_image);
        editTitle = view.findViewById(R.id.edit_title);
        editAuthor = view.findViewById(R.id.edit_author);
        spinnerGenre = view.findViewById(R.id.spinner_genre);
        editYear = view.findViewById(R.id.edit_year);
        editBlurb = view.findViewById(R.id.edit_blurb);
        btnSelectImage = view.findViewById(R.id.btn_select_image);
        btnAddBook = view.findViewById(R.id.btn_add_book);

        populateGenreSpinner();

        btnSelectImage.setOnClickListener(v -> openImagePicker());
        btnAddBook.setOnClickListener(v -> saveBook());

        return view;
    }


    private void populateGenreSpinner() {
        Set<String> genres = new HashSet<>();
        for (Book book : DataBook.bookList) {
            genres.add(book.getGenre());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                new ArrayList<>(genres)
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGenre.setAdapter(adapter);
    }
    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        imagePickerLauncher.launch(intent);
    }

    private void saveBook() {
        if (editTitle.getText().toString().isEmpty() ||
                editAuthor.getText().toString().isEmpty() ||
                editYear.getText().toString().isEmpty() ||
                editBlurb.getText().toString().isEmpty() ||
                selectedImageUri == null) {

            Toast.makeText(getContext(), "Please fill all fields and select an image", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            Book newBook = new Book(
                    editTitle.getText().toString(),
                    editAuthor.getText().toString(),
                    editBlurb.getText().toString(),
                    spinnerGenre.getSelectedItem().toString(),
                    "",
                    selectedImageUri.toString(),
                    Integer.parseInt(editYear.getText().toString()),
                    false,
                    0
            );

            DataBook.bookList.add(newBook);

            clearForm();
            Toast.makeText(getContext(), "Book added successfully", Toast.LENGTH_SHORT).show();

        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Invalid year format", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearForm() {
        editTitle.setText("");
        editAuthor.setText("");
        spinnerGenre.setSelection(0);
        editYear.setText("");
        editBlurb.setText("");
        selectedImageUri = null;
        bookImage.setImageResource(0);
    }
}