package seb.guild.model.character.builder;


import seb.guild.gui.PortraitImages;
import seb.guild.model.character.BaseCharacter;
import seb.guild.model.character.enums.*;

import java.util.Set;

public class CharacterBuilder {

    public BaseCharacter createRandomCharacter(){
        BaseCharacter character = new BaseCharacter();
        // choose sex
        character.setSex((Sexes) EnumUtils.getRandomValue(Sexes.values()));
        // generate name
        character.setName(new Names().getRandomName(character.getSex()));
        character.setPortrait(PortraitImages.getImageFor(character.getSex()));
        // generate attributes
        for (int i=0; i<3; i++ ){
            CharacterAttribute a = (CharacterAttribute) EnumUtils.getRandomValue(CharacterAttribute.values());
            Integer val= character.getAttributeValue(a);
            character.setAttributeValue(a, val+1);
        }
        // choose vocation
        character.setVocation((Vocations) EnumUtils.getRandomValue(Vocations.values()));
        // choose skills
        Set<Skills> favoredSkill = new VocationBuilder().getVocation(character.getVocation()).getFavoredSkills();
        character.setSkills(favoredSkill);
        for(int i =0 ;i<2;){
            Skills s = (Skills) EnumUtils.getRandomValue(Skills.values());
            if (!character.getSkills().contains(s))  {
                i = i +1;
                character.getSkills().add(s);
            }

        }
        // choose merit and flaw and passion
        // calc derived atributes

        return character;
    }
}
