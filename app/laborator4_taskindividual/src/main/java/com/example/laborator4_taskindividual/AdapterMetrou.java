package com.example.laborator4_taskindividual;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterMetrou extends BaseAdapter {

    private List<Metrou> metrouri = null;
    private Context ctx;
    private int resursaLayout;

    public AdapterMetrou(List<Metrou> metrouri, Context ctx, int resursaLayout) {
        this.metrouri = metrouri;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
    }

    @Override
    public int getCount() {
        return metrouri.size();
    }

    @Override
    public Object getItem(int i) {
        return metrouri.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(resursaLayout, parent, false);
        TextView titlu= v.findViewById(R.id.titlu_TV);
        TextView detaliiM= v.findViewById(R.id.detalii_TV);
        TextView tipTraseu= v.findViewById(R.id.tipTraseu_TV);
        TextView gradUz= v.findViewById(R.id.gradUzura_TV);
        TextView facilit = v.findViewById(R.id.facilitati_TV);

        Metrou m = (Metrou) getItem(i);
        titlu.setText("Metrou #"+m.getId()+"-"+m.getModelLocomotiva());
        detaliiM.setText("Magistrala "+m.getMagistrala()+
                "\n- "+m.getNumarVagoane()+" vagoane"+
                "\n- "+m.getNumarStatii()+" statii");
        tipTraseu.setText("Traseu: "+m.getTipTraseu());
        gradUz.setText("Gradul de uzura:  "+m.getGradUzura()+"/5");
        String dizabilitati = m.isAccesibilDizabilitati()? "Accesibil Dizabilitati" : "Nu este accesibil dizabilitati";
        String aerCond = m.isAerConditionat()? "Are aer conditionat" : "Nu are aer conditionat";
        facilit.setText(dizabilitati+"\n"+aerCond);

        return v;
    }
}
