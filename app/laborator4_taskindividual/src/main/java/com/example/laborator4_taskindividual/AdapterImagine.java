package com.example.laborator4_taskindividual;

import android.content.Context;
import android.view.LayoutInflater;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterImagine extends BaseAdapter {

    private List<ImaginiMetrou> imagini= null;
    private Context ctx;
    private int resursaLayout;

    public AdapterImagine(List<ImaginiMetrou> imagini, Context ctx, int resursaLayout) {
        this.imagini = imagini;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
    }

    @Override
    public int getCount() {
        return imagini.size();
    }

    @Override
    public Object getItem(int i) {
        return imagini.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(resursaLayout, parent, false);

        ImageView imagine= v.findViewById(R.id.imagine_IV);
        TextView descriere= v.findViewById(R.id.descriere_TV);


        ImaginiMetrou imagineCrt = (ImaginiMetrou) getItem(i);

        Bitmap bitmap = imagineCrt.getImagine();
        imagine.setImageBitmap(bitmap);
        descriere.setText(imagineCrt.getTextAfisat());

        return v;
    }
}
