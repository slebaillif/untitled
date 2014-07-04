package seb.guild.model.character;


import seb.guild.model.character.enums.*;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BaseCharacter {
    private String name;
    private Sexes sex;
    private Map<CharacterAttribute, Integer> attributes = new HashMap<CharacterAttribute, Integer>(7);
    private Map<DerivedAttribute, Integer> derivedAttributes = new HashMap<DerivedAttribute, Integer>(6);
    private Map<DerivedAttribute, Integer> currentDerivedAttributes = new HashMap<DerivedAttribute, Integer>(6);
    private Vocations vocation;
    private Set<Skills> skills = new HashSet<Skills>(5);
    private BufferedImage portrait;

    public void setSex(Sexes sex) {
        this.sex = sex;
    }

    public Sexes getSex() {
        return sex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAttributeValue(CharacterAttribute a) {
        return attributes.get(a) == null ? 0 : attributes.get(a);
    }

    public void setAttributeValue(CharacterAttribute a, int newValue) {
        attributes.put(a, newValue);
    }

    public String getName() {
        return name;
    }

    public void setVocation(Vocations vocation) {
        this.vocation = vocation;
    }

    public Set<Skills> getSkills() {
        return skills;
    }

    public Vocations getVocation() {
        return vocation;
    }

    public void setSkills(Set<Skills> skills) {
        this.skills = skills;
    }

    public BufferedImage getPortrait() {
        return portrait;
    }

    public void setPortrait(BufferedImage portrait) {
        this.portrait = portrait;
    }

    @Override
    public String toString() {
        return "BaseCharacter{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", attributes=" + attributes +
                ", derivedAttributes=" + derivedAttributes +
                ", currentDerivedAttributes=" + currentDerivedAttributes +
                ", vocation=" + vocation +
                ", skills=" + skills +
                '}';
    }
}
