package seb.guild.model.game;

import seb.guild.model.character.BaseCharacter;
import seb.guild.model.character.builder.CharacterBuilder;
import seb.guild.model.guild.GuildResources;
import seb.guild.model.guild.Roster;

public class Game {
    private Roster roster;
    private GuildResources resources;

    public Game() {
        roster = new Roster();
        resources = new GuildResources();
        CharacterBuilder builder = new CharacterBuilder();
        for(int i=0;i<3;i++){
            BaseCharacter c = builder.createRandomCharacter();
            roster.addCharacter(c);
        }
    }

    public Roster getRoster() {
        return roster;
    }

    public GuildResources getResources() {
        return resources;
    }
}
