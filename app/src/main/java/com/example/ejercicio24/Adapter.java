package com.example.ejercicio24;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    ArrayList<Signaturess> signaturessList;


    public Adapter(ArrayList<Signaturess> signaturesses){
        this.signaturessList = signaturesses;
    }

    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent , int viewType){

        View view = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.list_layout,
                parent,
                false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, final int position) {

        holder.setData(signaturessList.get(position));
    }

    @Override
    public int getItemCount() {
        return signaturessList.size();
    }

    private static Bitmap getSignaturessImage(String encodedImage){
        byte[] bytes = android.util.Base64.decode(encodedImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        ImageView imageViewIcon;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);

        }

        void setData(Signaturess signaturess){
            textViewName.setText(signaturess.getDescripcion());
            imageViewIcon.setImageBitmap(getSignaturessImage(signaturess.getImagen()));
        }
    }
    
}
