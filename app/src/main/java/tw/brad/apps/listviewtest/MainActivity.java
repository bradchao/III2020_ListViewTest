package tw.brad.apps.listviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        initListView();
    }

    private SimpleAdapter adapter;
    private LinkedList<HashMap<String,String>> data;

    private void initListView(){
        data = new LinkedList<>();
        adapter = new SimpleAdapter(this, data, R.layout.item, from, to);
        listView.setAdapter(adapter);
    }

}