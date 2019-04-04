package cz.inventi.java;

import java.util.List;

public class Vetva {

    List<String> potomkoviaVetvy;
    public int pocetPotomkov;

    public Vetva(List<String> potomkovia) {
        potomkoviaVetvy = potomkovia;
        pocetPotomkov = potomkoviaVetvy.size();
    }

    public List<String> getPotomkov() {
        return potomkoviaVetvy;
    }

    public int getPocetPotomkov() { return potomkoviaVetvy.size();}
}
