package cz.inventi.java;


public class Leaf implements Validita {

    String vlastnostListu;

    @Override
    public void preverVlastnost(String vlastnost, String identifikator) {
        // TODO Auto-generated method stub
        if (vlastnost.equals("Z") || vlastnost.equals("H")) {}
        else {throw new java.lang.Error("Invalidna vlastnost Listu na riadku: " + identifikator);}
    }

    public Leaf(String vlastnost) {
        vlastnostListu = vlastnost;
    }

    public String getVlastnost() {
        return vlastnostListu;
    }

}

