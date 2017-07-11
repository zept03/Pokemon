package pokedex.com.sept.lozada.pokedex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MoveActivity extends AppCompatActivity {

    String JsonURLMove = "http://pokeapi.co/api/v2/move/";
    private ArrayList moves;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);

        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view1);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        moves = new ArrayList<>();

        sendRequest();

        Log.d("Inside on create", "true");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_pokemon:
                Toast.makeText(this, "Pokemon selected", Toast.LENGTH_SHORT)
                        .show();
                Intent intent4 = new Intent(MoveActivity.this, MainActivity.class);
                MoveActivity.this.startActivity(intent4);
                break;
            case R.id.menu_items:
                Toast.makeText(this, "Items selected", Toast.LENGTH_SHORT)
                        .show();
                Intent intent = new Intent(MoveActivity.this, ItemActivity.class);
                MoveActivity.this.startActivity(intent);
                break;
            case R.id.menu_moves:
                Toast.makeText(this, "Moves selected", Toast.LENGTH_SHORT)
                        .show();
                Intent intent1 = new Intent(MoveActivity.this, MoveActivity.class);
                MoveActivity.this.startActivity(intent1);
                break;
            case R.id.menu_generations:
                Toast.makeText(this, "Generation selected", Toast.LENGTH_SHORT)
                        .show();
                Intent intent2 = new Intent(MoveActivity.this, GenerationActivity.class);
                MoveActivity.this.startActivity(intent2);
                break;
            case R.id.menu_pokedex:
                Toast.makeText(this, "Pokedex selected", Toast.LENGTH_SHORT)
                        .show();
                Intent intent3 = new Intent(MoveActivity.this, PokedexActivity.class);
                MoveActivity.this.startActivity(intent3);
                break;
            default:
                break;
        }

        return true;
    }

    private void sendRequest() {
        Controller.getInstance(this).add(obreq);
        Log.d("Inside sendRequest", "true");
    }

    JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, JsonURLMove, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Log.d("Inside on response", "true");
                        JSONArray obj = response.getJSONArray("results");

                        for (int init = 0; init < obj.length(); init++) {
                            JSONObject tempObj = obj.getJSONObject(init);
//                            Move mMove = new Move();
//                            mMove.setmName(tempObj.getString("name"));
                            moves.add(tempObj.getString("name"));
//                            Toast.makeText(MoveActivity.this, tempObj.getString("name"), Toast.LENGTH_SHORT).show();
                        }

                        RecyclerView.Adapter adapter = new DataAdapter(moves);
                        recyclerView.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            }
    );
}