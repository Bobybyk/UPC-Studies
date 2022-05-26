package main.java.gui.game;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Box {
    public static BufferedImage WALL, PATH, GHOST, PLAYER, VOID;

    public static void setTextures() {
        try {
            WALL = ImageIO.read(new File(new Box().getClass().getResource("../../../../textures/WALL.png").getFile()));
            PATH = ImageIO.read(new File(new Box().getClass().getResource("../../../../textures/PATH.png").getFile()));
            GHOST = ImageIO.read(new File(new Box().getClass().getResource("../../../../textures/GHOST.png").getFile()));
            PLAYER = ImageIO.read(new File(new Box().getClass().getResource("../../../../textures/PLAYER.png").getFile()));
            VOID = ImageIO.read(new File(new Box().getClass().getResource("../../../../textures/VOID.png").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
