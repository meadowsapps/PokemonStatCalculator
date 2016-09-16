package com.meadowsapps.pkmn.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;

/**
 * Created by dmeadows on 9/16/16.
 */
public class StatGraph extends AnchorPane {

    private Canvas canvas;

    private int value;

    public StatGraph() {
        canvas = new Canvas(300, 300);
        getChildren().add(canvas);
    }

    private void paint() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());

        double scale = getWidth() / 255;
        double width = value * scale;
        double height = getHeight() - 10;
        gc.fillRect(0, 5, width, height);
    }

    public void setValue(int value) {
        this.value = value;
        paint();
    }
}
