package cz.inventi.java;


public class Orech implements Validita {

    String vlastnostOrechu;

    @Override
    public void preverVlastnost(String vlastnost, String identifikator) {
        // TODO Auto-generated method stub
        if (vlastnost.equals("Z") || vlastnost.equals("N")) {}
        else {throw new java.lang.Error("Invalidna vlastnost Orecha na riadku: " + identifikator);}
    }

    public Orech(String vlastnost) {
        vlastnostOrechu = vlastnost;
    }

    public String getVlastnost() {
        return vlastnostOrechu;
    }

}

