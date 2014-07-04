package seb.guild.gui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TrapImages {
    private final BufferedImage dartHorizontalFromLeft;
    private final BufferedImage redBlood;

    public TrapImages() {
        try {
            dartHorizontalFromLeft = ImageIO.read(this.getClass().getClassLoader().getResource("effect/dart2.png"));
            redBlood = ImageIO.read(this.getClass().getClassLoader().getResource("dc-misc/blood_red.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedImage getDartHorizontalFromLeft() {
        return dartHorizontalFromLeft;
    }

    public BufferedImage getRedBlood() {
        return redBlood;

    }
}
