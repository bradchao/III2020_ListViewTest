package tw.brad.apps.listviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

public class DetailActivity extends AppCompatActivity {
    private ImageView img;
    private TextView org, place;
    private RequestQueue queue;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        queue = Volley.newRequestQueue(this);

        Intent intent = getIntent();

        img = findViewById(R.id.detail_img);
        org = findViewById(R.id.detail_org);
        place = findViewById(R.id.detail_place);

        org.setText(intent.getStringExtra("org"));
        place.setText(intent.getStringExtra("place"));
        url = intent.getStringExtra("img");
        fetchImage();
    }

    private void fetchImage(){
        ImageRequest request = new ImageRequest(
                url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        img.setImageBitmap(response);
                    }
                },
                0,0,
                Bitmap.Config.ARGB_8888,
                null
        );
        queue.add(request);
    }
}