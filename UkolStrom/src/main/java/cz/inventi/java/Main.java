package cz.inventi.java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("---------Start aplikacice---------");

        testing(vygenerujStrom());

        System.out.println("---------Koniec aplikacice--------");
    }

    public static void testing(HashMap<String, Object> strom) {

        int pocetVrcholov = 0;
        int pocetHran = 0;
        int pocetZelenychListov = 0;
        int pocetHnedychListov = 0;
        int pocetZrelychOrechov = 0;
        int pocetNezrelychOrechov = 0;

        for (String key : strom.keySet())
        {
            //System.out.println(key + " " + strom.get(key));
            if (strom.get(key) instanceof Vetva)
            {
                //System.out.println(((Vetva) strom.get(key)).getPotomkov());
                pocetHran = pocetHran + ((Vetva) strom.get(key)).getPocetPotomkov();

            } else if (strom.get(key) instanceof Leaf)

            {
                //System.out.println(((Leaf) strom.get(key)).getVlastnost());
                if(((Leaf) strom.get(key)).getVlastnost().equals("Z"))
                {
                    pocetZelenychListov++;

                } else if(((Leaf) strom.get(key)).getVlastnost().equals("H"))
                {
                    pocetHnedychListov++;
                }
            } else if (strom.get(key) instanceof Orech)
            {
                //System.out.println(((Orech) strom.get(key)).getVlastnost());
                if(((Orech) strom.get(key)).getVlastnost().equals("Z"))
                {
                    pocetZrelychOrechov++;
                } else if(((Orech) strom.get(key)).getVlastnost().equals("N"))
                {
                    pocetNezrelychOrechov++;
                }
            }
        }

        pocetVrcholov = strom.size();

        if (pocetVrcholov - 1 == pocetHran) {

            System.out.println("S:ANO");
            //System.out.println("Pocet vrcholov grafu: " + pocetVrcholov);
            //System.out.println("Pocet hran grafu: " + pocetHran);
            System.out.println("L;Z;" + pocetZelenychListov);
            System.out.println("L;H;" + pocetHnedychListov);
            System.out.println("O;Z;" + pocetZrelychOrechov);
            System.out.println("O;N;" + pocetNezrelychOrechov);

        } else if (pocetVrcholov <= pocetHran) {throw new java.lang.Error("Subor nieje validny, pocet objektov je mensi ako pocet ich potomkov");}
        System.out.println("S:NE");

    }

    public static HashMap<String, Object> vygenerujStrom() {

        BufferedReader br = null;
        String identifikator;
        int pocetIdentifikatorov = 0;
        String line;
        String[] lineSplitted;
        //int pocetVrcholov = 0;
        int pocetHran = 0;

        HashMap<String, Object> strom = new HashMap<>();

        try {
            br = new BufferedReader(new FileReader("input.txt"));
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        try {
            for (pocetIdentifikatorov = 1;(line = br.readLine()) != null;pocetIdentifikatorov++) {
                lineSplitted = line.split(";");
                try {
                    int cisloIdentifikatora = Integer.parseInt(lineSplitted[0]);
                    if (cisloIdentifikatora != pocetIdentifikatorov) {throw new java.lang.Error("Chyba na riadku: " + pocetIdentifikatorov + ", Nevalidna postupnost identifikatorov");}

                } catch (NumberFormatException f) {
                    throw new java.lang.Error("Chyba na riadku: " + pocetIdentifikatorov + ", identifikator musi byt cislo");
                }

                identifikator = lineSplitted[0];
                String objekt = lineSplitted[1];
                //pocetVrcholov++;

                if (objekt.equals("V")) {

                    List<String> potomkovia = new ArrayList<>();


                    for (int i = 2; i < lineSplitted.length; i++) {

                        try {
                            Integer.parseInt(lineSplitted[i]);

                        }
                        catch (NumberFormatException e) {
                            throw new java.lang.Error("Chyba na riadku: " + identifikator + ", potomok musi byt cislo.");
                        }

                        potomkovia.add(lineSplitted[i]);
                    } strom.put(identifikator, new Vetva(potomkovia));
                    pocetHran = pocetHran + potomkovia.size();
                } else if (objekt.equals("L")) {

                    String vlastnost = lineSplitted[2];

                    new Leaf(vlastnost).preverVlastnost(vlastnost, identifikator);
                    strom.put(identifikator, new Leaf(vlastnost));

                } else if (objekt.equals("O")) {

                    String vlastnost = lineSplitted[2];
                    new Orech(vlastnost).preverVlastnost(vlastnost, identifikator);
                    strom.put(identifikator, new Orech(vlastnost));
                } else throw new java.lang.Error("Chyba na riadku: " + identifikator + ", Nevalidny typ objektu");

            }
            //System.out.println(pocetIdentifikatorov);
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return strom;

    }
}

