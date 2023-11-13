package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import netscape.javascript.JSObject;

import java.io.File;
import java.io.IOException;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static Map<Integer,Carte> citire() {
        try {
            File file=new File("src/main/resources/carti.json");
            ObjectMapper mapper=new ObjectMapper();
            Map<Integer,Carte> carti = mapper.readValue(file, new TypeReference <Map<Integer,Carte>>(){});
            return carti;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static void afisare(Map<Integer, Carte> carti) {
        Iterator<Map.Entry<Integer, Carte>> iterator = carti.entrySet().iterator();

        while (iterator.hasNext()){
            Map.Entry<Integer, Carte> entry = iterator.next();
            var key = entry.getKey();
            var value = entry.getValue();
            System.out.println("Cheia : " + key + " , " + value);
        }
    }
    public static void scriereJson(Map<Integer,Carte> carti) {
        try {
            ObjectMapper mapper=new ObjectMapper();
            File file=new File("src/main/resources/carti.json");
            mapper.writeValue(file,carti);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Map<Integer,Carte> carti= citire();
        afisare(carti);
        System.out.println("_______________________________________________");

        carti.remove(1);
        afisare(carti);
        System.out.println("_______________________________________________");


        carti.putIfAbsent(99,new Carte("ion","aurel",2077));
        afisare(carti);
        System.out.println("_______________________________________________");

        scriereJson(carti);
        System.out.println("_______________________________________________");

        Set<Carte> yuvalNoahHarari = carti.values().stream()
                .filter(x -> x.autorul().equalsIgnoreCase("Yuval Noah Harari"))
                .collect(Collectors.toSet());
        yuvalNoahHarari.forEach(System.out::println);
        System.out.println("_______________________________________________");

        yuvalNoahHarari.stream()
                .sorted(Comparator.comparing(Carte::titlul))
                .forEach(System.out::println);

    }

}