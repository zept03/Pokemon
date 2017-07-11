package pokedex.com.sept.lozada.pokedex;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class Controller {

    private static RequestQueue requestQueue;

    private Controller() {

    }

    public static RequestQueue getInstance(Context context) {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }
        return requestQueue;
    }
}
