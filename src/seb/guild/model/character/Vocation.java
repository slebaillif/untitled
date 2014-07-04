package seb.guild.model.character;


import seb.guild.model.character.enums.Skills;
import seb.guild.model.character.enums.Vocations;

import java.util.Set;

public class Vocation {
    private Vocations vocation;
    private Set<Skills> favoredSkills;

    public Vocation(Vocations vocation, Set<Skills> favoredSkills) {
        this.vocation = vocation;
        this.favoredSkills = favoredSkills;
    }

    public String getName() {
        return vocation.name();
    }

    public Set<Skills> getFavoredSkills() {
        return favoredSkills;
    }
}
