package tw.brad.apps.listviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(this);

        listView = findViewById(R.id.listView);
        initListView();
        getGiftData();
    }

    private SimpleAdapter adapter;
    private LinkedList<HashMap<String,String>> data;
    private String[] from = {"title","feature"};
    private int[] to = {R.id.item_title, R.id.item_feature};

    private void initListView(){
        data = new LinkedList<>();
        adapter = new SimpleAdapter(this, data, R.layout.item, from, to);
        listView.setAdapter(adapter);
        //getGiftData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                gotoDetail(i);
            }
        });

    }

    private void gotoDetail(int i){
        HashMap<String,String> row = data.get(i);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("place", row.get("SalePlace"));
        intent.putExtra("org", row.get("ProduceOrg"));
        intent.putExtra("img", row.get("img"));
        startActivity(intent);
    }

    private void getGiftData(){
        StringRequest request = new StringRequest(
                Request.Method.GET,
                "https://data.coa.gov.tw/Service/OpenData/ODwsv/ODwsvAgriculturalProduce.aspx",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseData(response);
                    }
                },
                null);
        queue.add(request);
    }

    private void parseData(String json){
        try {
            JSONArray root = new JSONArray(json);
            for (int i=0; i<root.length(); i++){
                JSONObject row = root.getJSONObject(i);
                HashMap<String, String> gift = new HashMap<>();
                gift.put(from[0], row.getString("Name"));
                gift.put(from[1], row.getString("Feature"));
                gift.put("SalePlace",row.getString("SalePlace"));
                gift.put("ProduceOrg", row.getString("ProduceOrg"));
                gift.put("img",row.getString("Column1") );
                data.add(gift);
            }
            adapter.notifyDataSetChanged();


        }catch (Exception e){
            //
            Log.v("bradlog", e.toString());
        }
    }


}