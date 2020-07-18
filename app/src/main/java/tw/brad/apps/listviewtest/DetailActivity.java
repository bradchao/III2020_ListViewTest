package tw.brad.apps.listviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private ImageView img;
    private TextView org, place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        img = findViewById(R.id.detail_img);
        org = findViewById(R.id.detail_org);
        place = findViewById(R.id.detail_place);

        org.setText(intent.getStringExtra("org"));
        place.setText(intent.getStringExtra("place"));

    }
}