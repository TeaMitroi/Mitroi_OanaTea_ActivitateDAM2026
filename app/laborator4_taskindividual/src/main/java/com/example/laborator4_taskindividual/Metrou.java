package com.example.laborator4_taskindividual;

import android.os.Parcel;
import android.os.Parcelable;

public class Metrou implements Parcelable {
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

    protected Metrou(Parcel in) {
        id = in.readInt();
        modelLocomotiva = in.readString();
        numarVagoane = in.readInt();
        numarStatii = in.readInt();
        magistrala = in.readString();
        tipTraseu = in.readString();
        gradUzura = in.readFloat();
        accesibilDizabilitati = in.readByte() != 0;
        aerConditionat = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(modelLocomotiva);
        dest.writeInt(numarVagoane);
        dest.writeInt(numarStatii);
        dest.writeString(magistrala);
        dest.writeString(tipTraseu);
        dest.writeFloat(gradUzura);
        dest.writeByte((byte) (accesibilDizabilitati ? 1 : 0));
        dest.writeByte((byte) (aerConditionat ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Metrou> CREATOR = new Creator<Metrou>() {
        @Override
        public Metrou createFromParcel(Parcel in) {
            return new Metrou(in);
        }

        @Override
        public Metrou[] newArray(int size) {
            return new Metrou[size];
        }
    };

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
        return "Metrou Id: " + id +
                "\n\t Model Locomotiva: " + modelLocomotiva +
                "\n\t Numar Vagoane: " + numarVagoane +
                "\n\t Numar Statii: " + numarStatii +
                "\n\t Magistrala: " + magistrala +
                "\n\t Tip Traseu: " + tipTraseu +
                "\n\t Grad de Uzura: " + gradUzura + " /5" +
                "\n\t Accesibil persoanelor cu dizabilitati: " + (accesibilDizabilitati ? "Da" : "Nu") +
                "\n\t Aer Conditionat: " + (aerConditionat ? "Da" : "Nu");
    }
}
