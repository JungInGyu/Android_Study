package com.example.imagejson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<NoteBook> {
    private Context context;
    private ArrayList<NoteBook> noteBooks;

    public CustomAdapter(@NonNull Context context, ArrayList<NoteBook> noteBooks) {
        super(context, R.layout.item, noteBooks);
        this.context = context;
        this.noteBooks = noteBooks;
    }

    @Nullable
    @Override
    public NoteBook getItem(int position) {
        return noteBooks.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        NoteBook noteBook = getItem(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.item, null);
            holder.imageView = convertView.findViewById(R.id.image);
            holder.brand = convertView.findViewById(R.id.brand);
            holder.model = convertView.findViewById(R.id.model);
            holder.price = convertView.findViewById(R.id.price);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.brand.setText(noteBook.getBrand());
        holder.model.setText(noteBook.getModel());
        holder.price.setText(noteBook.getPrice());

        ImageThread thread = new ImageThread(context, noteBook.getImage());
        thread.start();
        try {
            thread.join();
            holder.imageView.setImageBitmap(thread.getBitmap());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return convertView;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView brand;
        TextView model;
        TextView price;
    }
}
