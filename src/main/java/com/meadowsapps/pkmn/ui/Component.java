package com.meadowsapps.pkmn.ui;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * Created by Dylan on 9/16/16.
 */
public abstract class Component extends AnchorPane implements ComponentI {

    public Component() {
        Node node = initComponents();
        getChildren().add(node);
    }

}
