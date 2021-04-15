package com.example.rastreadordecasoscovid_19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorEspecial extends ArrayAdapter<ModeloPais> {
private  Context context;
private List<ModeloPais> listaModeloPais;
    private List<ModeloPais> listaModeloPaisFiltrado;
    public AdaptadorEspecial( Context context, List<ModeloPais> listaModeloPais) {
        super(context, R.layout.listaespecial, listaModeloPais);
        this.context = context;
        this.listaModeloPais = listaModeloPais;
        this.listaModeloPaisFiltrado=listaModeloPais;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listaespecial,null,true);
        TextView NombrePais = view.findViewById(R.id.tvNombrePais);
        ImageView imageView = view.findViewById(R.id.imageFlag);

        NombrePais.setText(listaModeloPaisFiltrado.get(position).getNommbrePais());
        Glide.with(context).load(listaModeloPaisFiltrado.get(position).getBandera()).into(imageView);

        return view;
    }

    @Override
    public int getCount() {
        return listaModeloPaisFiltrado.size();
    }

    @Nullable
    @Override
    public ModeloPais getItem(int position) {
        return listaModeloPaisFiltrado.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                if(constraint == null || constraint.length() == 0){
                    filterResults.count = listaModeloPais.size();
                    filterResults.values = listaModeloPais;

                }else{
                    List<ModeloPais> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for(ModeloPais itemsModel:listaModeloPais){
                        if(itemsModel.getNommbrePais().toLowerCase().contains(searchStr)){
                            resultsModel.add(itemsModel);

                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }


                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                listaModeloPaisFiltrado = (List<ModeloPais>) results.values;
                PaisesAfectados.ListaModeloPais = (List<ModeloPais>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }
}
