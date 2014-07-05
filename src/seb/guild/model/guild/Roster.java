package seb.guild.model.guild;

import seb.guild.model.character.BaseCharacter;

import java.util.LinkedList;
import java.util.List;

public class Roster {
    List<BaseCharacter> characters;

    public Roster() {
        characters = new LinkedList<>();
    }

    public void addCharacter(BaseCharacter character){
        characters.add(character);
    }

    public List<BaseCharacter> getCharacters() {
        return characters;
    }
}
