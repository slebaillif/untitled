package seb.guild.model.character;

import seb.guild.model.character.enums.Sexes;

import java.awt.image.BufferedImage;

public class Portrait {
    Sexes sex;
    BufferedImage portrait;

    public Portrait(Sexes maleSex, BufferedImage portrait) {
        this.sex = maleSex;
        this.portrait = portrait;
    }

    public Sexes getSex() {
        return sex;
    }

    public BufferedImage getPortrait() {
        return portrait;
    }
}
