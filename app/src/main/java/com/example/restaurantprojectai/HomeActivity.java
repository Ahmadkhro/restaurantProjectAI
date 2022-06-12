package com.example.restaurantprojectai;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
public class HomeActivity extends AppCompatActivity {

    private List<Food> items = new ArrayList<>();
    private RecyclerView recycler;
    private static  final String BASE_URL = "get_items.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recycler = findViewById(R.id.recycler_food_list);


        recycler.setLayoutManager(new LinearLayoutManager(this));
        loadItems();
    }

    private void loadItems() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, MainActivity.IP+BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        try {

                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i<array.length(); i++){

                                JSONObject object = array.getJSONObject(i);

                                String name = object.getString("name");
                                String image = object.getString("image");
                                String price = object.getString("price");



                                Food food = new Food(name,image,price);
                                items.add(food);
                            }

                        }catch (Exception e){

                        }

                        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(HomeActivity.this,
                                items);
                        recycler.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Toast.makeText(HomeActivity.this, error.toString(),Toast.LENGTH_LONG).show();

            }
        });

        Volley.newRequestQueue(HomeActivity.this).add(stringRequest);

    }
}