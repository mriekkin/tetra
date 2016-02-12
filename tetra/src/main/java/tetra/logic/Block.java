package tetra.logic;

import java.awt.Color;

public class Block {

    private final int color;
    private final Color colorAwt;

    public Block(int color) {
        this.color = color;
        this.colorAwt = new Color(color);
    }

    public int getColor() {
        return color;
    }

    public Color getColorAwt() {
        return colorAwt;
    }

}
