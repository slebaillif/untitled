package test;

import org.junit.Test;
import seb.guild.model.character.BaseCharacter;
import seb.guild.model.character.builder.CharacterBuilder;
import seb.guild.model.dungeon.DungeonArea;
import seb.guild.model.dungeon.DungeonBuilder;

public class testCreation {
    @Test
    public void testSmtg(){
       BaseCharacter c = new CharacterBuilder().createRandomCharacter();
       assert(c.getSex()   != null);
       assert(c.getName() != null);
        System.out.println(c.toString());
    }
     @Test
    public void testDungeon() throws Exception {
        DungeonBuilder builder = new DungeonBuilder();
        DungeonArea area = builder.getLostTemple();
        System.out.println(area.toString());

    }
}
