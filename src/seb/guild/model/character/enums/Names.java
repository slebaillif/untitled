package seb.guild.model.character.enums;

import java.util.*;

public class Names {
    Map<Sexes, List<String>> names;

    public Names() {
        names = new HashMap<Sexes, List<String>>(2);
        List<String> m = new ArrayList<String>(5);
        List<String> f = new ArrayList<String>(5);
        m.add("Peter");
        m.add("Paul");
        m.add("Jack");
        m.add("Robert");
        m.add("James");
        f.add("Helena");
        f.add("Mary");
        f.add("Birgitte");
        f.add("Cynthia");
        f.add("Rachel");
        names.put(Sexes.MALE, m);
        names.put(Sexes.FEMALE, f);
    }

    public String getRandomName(Sexes sex){
        List<String> n = names.get(sex);
        return random(n);
    }

    private String random(List<String> n){
       int i = (int)(Math.random() * (n.size() - 1));
       return n.get(i);
    }
}
