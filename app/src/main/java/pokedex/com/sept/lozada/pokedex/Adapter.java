package pokedex.com.sept.lozada.pokedex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapter extends BaseAdapter {

    private List<Pokemon> pokeList;
    private Context context;
    private LayoutInflater mInflater;

    public Adapter(Context context, List<Pokemon> pokeList) {

        this.context = context;
        this.pokeList = pokeList;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return pokeList.size();
    }

    @Override
    public Object getItem(int position) {
        return pokeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.item_pokemon, null);

        TextView textView = (TextView) convertView.findViewById(R.id.textView_pokemonName);
        textView.setText(pokeList.get(position).getPokeName());

        ImageView imgView = (ImageView) convertView.findViewById(R.id.imageView_pokemon);
        ImageRequest.requestImage(context, imgView, pokeList.get(position).getImgURL());

        return convertView;
    }
}



