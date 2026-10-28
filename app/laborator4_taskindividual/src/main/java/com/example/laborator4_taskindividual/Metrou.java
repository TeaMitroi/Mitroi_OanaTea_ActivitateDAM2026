package com.example.laborator4_taskindividual;

import android.widget.EditText;

import java.io.Serializable;

public class Metrou implements Serializable {
    private int id;
    private String modelLocomotiva;
    private int numarVagoane;
    private int numarStatii;
    private String magistrala;
    private String tipTraseu;
    private float gradUzura;
    private  boolean  accesibilDizabilitati;
    private boolean aerConditionat;

    public Metrou(int id, String modelLocomotiva, int numarVagoane, int numarStatii, String magistrala, String tipTraseu, float gradUzura, boolean accesibilDizabilitati, boolean aerConditionat) {
        this.id = id;
        this.modelLocomotiva = modelLocomotiva;
        this.numarVagoane = numarVagoane;
        this.numarStatii = numarStatii;
        this.magistrala = magistrala;
        this.tipTraseu = tipTraseu;
        this.gradUzura = gradUzura;
        this.accesibilDizabilitati = accesibilDizabilitati;
        this.aerConditionat = aerConditionat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelLocomotiva() {
        return modelLocomotiva;
    }

    public void setModelLocomotiva(String modelLocomotiva) {
        this.modelLocomotiva = modelLocomotiva;
    }

    public int getNumarVagoane() {
        return numarVagoane;
    }

    public void setNumarVagoane(int numarVagoane) {
        this.numarVagoane = numarVagoane;
    }

    public int getNumarStatii() {
        return numarStatii;
    }

    public void setNumarStatii(int numarStatii) {
        this.numarStatii = numarStatii;
    }

    public String getMagistrala() {
        return magistrala;
    }

    public void setMagistrala(String magistrala) {
        this.magistrala = magistrala;
    }

    public String getTipTraseu() {
        return tipTraseu;
    }

    public void setTipTraseu(String tipTraseu) {
        this.tipTraseu = tipTraseu;
    }

    public float getGradUzura() {
        return gradUzura;
    }

    public void setGradUzura(float gradUzura) {
        this.gradUzura = gradUzura;
    }

    public boolean isAccesibilDizabilitati() {
        return accesibilDizabilitati;
    }

    public void setAccesibilDizabilitati(boolean accesibilDizabilitati) {
        this.accesibilDizabilitati = accesibilDizabilitati;
    }

    public boolean isAerConditionat() {
        return aerConditionat;
    }

    public void setAerConditionat(boolean aerConditionat) {
        this.aerConditionat = aerConditionat;
    }

    @Override
    public String toString() {
        return "Metrou{" +
                "id=" + id +
                ", modelLocomotiva='" + modelLocomotiva + '\'' +
                ", numarVagoane=" + numarVagoane +
                ", numarStatii=" + numarStatii +
                ", magistrala='" + magistrala + '\'' +
                ", tipTraseu='" + tipTraseu + '\'' +
                ", gradUzura=" + gradUzura + " /5" +
                ", accesibilDizabilitati=" + (accesibilDizabilitati ? "Da" : "Nu") +
                ", aerConditionat=" + aerConditionat +
                '}';
    }
}
