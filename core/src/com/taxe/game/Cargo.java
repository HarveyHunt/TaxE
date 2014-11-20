package com.taxe.game;
import java.awt.*;

/**
 * Created by Owen on 19/11/2014.
 */
public class Cargo {
    private int quantity;
    private Image image;
    private String type;

    public Cargo(int quantity, Image image, String type) {
        this.quantity = quantity;
        this.image = image;
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}



