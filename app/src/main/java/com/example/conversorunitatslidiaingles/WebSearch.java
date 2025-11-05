package com.example.conversorunitatslidiaingles;

import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class WebSearch extends AppCompatActivity {

    TextInputEditText searchBox;
    Button bttSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_web_search);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        searchBox = findViewById(R.id.textInputLayoutSearch);
        bttSearch = findViewById(R.id.bttSearch);

        bttSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchTerms = searchBox.getText().toString();
                if(!searchTerms.equals("")) {
                    searchNet(searchTerms);
                }
            }
        }
        );

    }

    private void searchNet(String text) {
        try {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, text);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            searchNetCompat(text);
        }
    }

    private void searchNetCompat(String text) {
        try {
            Uri uri = Uri.parse("http://www.google.com/#q=" + text);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

}