package de.pflugmacher.testgame;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Utils {

    public static BufferedImage[] CutImage(BufferedImage image, int rows, int cols) {
        int chunks = rows * cols;

        int chunkWidth = image.getWidth() / cols;
        int chunkHeight = image.getHeight() / rows;
        int count = 0;
        BufferedImage[] animation = new BufferedImage[chunks];
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
            	animation[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());

                Graphics2D gr = animation[count++].createGraphics();
                gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);
                gr.dispose();
            }
        }
        return animation;
    }
}
