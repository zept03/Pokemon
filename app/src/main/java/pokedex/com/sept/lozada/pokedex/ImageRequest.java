package pokedex.com.sept.lozada.pokedex;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;


public class ImageRequest {

    public static void requestImage(final Context context, final ImageView imageView, String pokemonURL) {

        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, pokemonURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject sprites = response.getJSONObject("sprites");
                            String url = sprites.getString("front_default");
                            Log.d("FOUND URL FOR IMAGE", url);
                            if (url != null) {
                                com.android.volley.toolbox.ImageRequest imgRequest = new com.android.volley.toolbox.ImageRequest(url,
                                        new Response.Listener<Bitmap>() {
                                            @Override
                                            public void onResponse(Bitmap arg0) {
                                                Log.d("FINISHED DOWNLOAD IMAGE", "");
                                                imageView.setImageBitmap(arg0);
                                            }
                                        }, 300, 200, Bitmap.Config.ARGB_8888,
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError arg0) {
                                                arg0.printStackTrace();
                                            }
                                        });
                                Controller.getInstance(context).add(imgRequest);
                            }
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
        Controller.getInstance(context).add(obreq);
    }
}
