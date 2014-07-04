package seb.guild.model.character.enums;

public class EnumUtils {
    public static Enum getRandomValue( Enum[] e){
        int size = e.length;
        int i = (int)(Math.random() * size);
        return e[i];
    }
}
