package com.meadowsapps.pkmn.ui;

import com.meadowsapps.pkmn.data.Stat;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * Created by Dylan on 9/15/16.
 */
public class StatView extends AnchorPane {

    private Slider[] evEditors = new Slider[6];

    public StatView() {
        initComponents();
    }

    private void initComponents() {
        GridPane layoutPanel = new GridPane();
        layoutPanel.setGridLinesVisible(false);
        layoutPanel.setPadding(new Insets(10, 10, 10, 10));
        String style = "-fx-border-color: lightgrey;";
        style += " -fx-border-radius: 4px;";
        layoutPanel.setStyle(style);
        layoutPanel.setHgap(10);
        layoutPanel.setVgap(10);

        for (Stat stat : Stat.values()) {
            EvEditor evEditor = new EvEditor(stat);
            evEditor.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            layoutPanel.add(evEditor, 0, stat.ordinal());
        }

        getChildren().add(layoutPanel);
    }

}
