package seb.guild.gui;

import seb.guild.model.character.Portrait;
import seb.guild.model.character.enums.Sexes;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static seb.guild.model.character.enums.Sexes.FEMALE;
import static seb.guild.model.character.enums.Sexes.MALE;

public class PortraitImages {
    private static PortraitImages instance;
    private List<Portrait> unusedPortraitList;
    private List<Portrait> usedPortraitList;
    // TODO make sure portraits are only used once
    // TODO associate  race to portraits


    public static PortraitImages getInstance() {
        if (instance == null) {
            instance = new PortraitImages();
        }
        return instance;
    }

    public static BufferedImage getImageFor(Sexes sex) {
        if (instance == null) {
            instance = new PortraitImages();
        }
        Optional portraitOptional = instance.unusedPortraitList.stream().filter(p -> p.getSex() == sex).findAny();
        if (portraitOptional.isPresent()){
            instance.unusedPortraitList.remove(portraitOptional.get());
            instance.usedPortraitList.add((Portrait) portraitOptional.get());
            return ((Portrait) portraitOptional.get()).getPortrait();
        }
        else{
            throw new RuntimeException("No more portaits");
        }
    }

    private PortraitImages() {
        unusedPortraitList = new ArrayList<>();

        try {
            unusedPortraitList.add(new Portrait(MALE, ImageIO.read(this.getClass().getClassLoader().getResource("portraits/tn_268_2_jpg.jpg"))));
            unusedPortraitList.add(new Portrait(FEMALE, ImageIO.read(this.getClass().getClassLoader().getResource("portraits/tn_268_3_jpg.jpg"))));
            unusedPortraitList.add(new Portrait(MALE, ImageIO.read(this.getClass().getClassLoader().getResource("portraits/tn_268_5_jpg.jpg"))));
            unusedPortraitList.add(new Portrait(FEMALE, ImageIO.read(this.getClass().getClassLoader().getResource("portraits/tn_268_6_jpg.jpg"))));
            unusedPortraitList.add(new Portrait(MALE, ImageIO.read(this.getClass().getClassLoader().getResource("portraits/tn_268_7_jpg.jpg"))));
            unusedPortraitList.add(new Portrait(MALE, ImageIO.read(this.getClass().getClassLoader().getResource("portraits/tn_268_8_jpg.jpg"))));
            unusedPortraitList.add(new Portrait(MALE, ImageIO.read(this.getClass().getClassLoader().getResource("portraits/tn_268_9_jpg.jpg"))));
            unusedPortraitList.add(new Portrait(MALE, ImageIO.read(this.getClass().getClassLoader().getResource("portraits/tn_268_10_jpg.jpg"))));
            unusedPortraitList.add(new Portrait(MALE, ImageIO.read(this.getClass().getClassLoader().getResource("portraits/tn_268_11_jpg.jpg"))));
            unusedPortraitList.add(new Portrait(MALE, ImageIO.read(this.getClass().getClassLoader().getResource("portraits/tn_268_12_jpg.jpg"))));
            unusedPortraitList.add(new Portrait(MALE, ImageIO.read(this.getClass().getClassLoader().getResource("portraits/tn_268_13_jpg.jpg"))));
            unusedPortraitList.add(new Portrait(FEMALE, ImageIO.read(this.getClass().getClassLoader().getResource("portraits/tn_PCP_rogues3_n43.jpg"))));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
