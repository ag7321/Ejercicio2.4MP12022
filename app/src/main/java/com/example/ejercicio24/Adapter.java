package com.example.ejercicio24;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.SignaturessViewHolder> {
    private Context mCtx;
    private List<Signaturess> signaturessesList;
    
    public Adapter(Context mCtx, List<Signaturess> signaturesses){
        this.mCtx = mCtx;
        this.signaturessesList = signaturesses;
    }
    
    @Override
    public SignaturessViewHolder onCreateViewHolder(ViewGroup parent , int viewType){
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout, null);
        return  new SignaturessViewHolder(view);
    }


    @Override
    public void onBindViewHolder(SignaturessViewHolder holder, int position){
        Signaturess signaturess = signaturessesList.get(position);
        holder.textDescripcion.setText(signaturess.getDescripcion());

        holder.imageView.setImageBitmap(getSignaturessImage(signaturess.getImagen()));

        
    }

    public void agregarSignaturess(Signaturess signaturess){
       signaturessesList.add(signaturess);
       this.notifyDataSetChanged();
    }

    private static Bitmap getSignaturessImage(String encodedImage){

        byte[] bytes = android.util.Base64.decode(encodedImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }


    @Override
    public int getItemCount(){return signaturessesList.size();}
    
    class SignaturessViewHolder extends RecyclerView.ViewHolder{
        TextView textDescripcion;
        ImageView imageView;
        public SignaturessViewHolder(View intemView){
            super(intemView);

            textDescripcion = intemView.findViewById(R.id.textDescripcion);
            imageView = intemView.findViewById(R.id.imagen);

        }
    }

    
}
