package com.meadowsapps.pkmn.ui;

import com.meadowsapps.pkmn.data.Stat;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * Created by Dylan on 9/15/16.
 */
public class StatView extends AnchorPane {

    private EvEditor[] evEditors = new EvEditor[6];

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

        RemainingEvsLabel remainingLbl = new RemainingEvsLabel();
        remainingLbl.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        remainingLbl.setAlignment(Pos.CENTER_RIGHT);
        layoutPanel.add(remainingLbl, 0, 6);

        for (Stat stat : Stat.values()) {
            evEditors[stat.ordinal()] = new EvEditor(stat);
            evEditors[stat.ordinal()].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            evEditors[stat.ordinal()].addChangeListener(remainingLbl);
            layoutPanel.add(evEditors[stat.ordinal()], 0, stat.ordinal());
        }

        getChildren().add(layoutPanel);
    }

    class RemainingEvsLabel extends GridPane implements ChangeListener {

        private Label valueLbl;

        public RemainingEvsLabel() {
            setHgap(10);
            setVgap(10);

            {
                Label remainingLbl = new Label("Remaining:");
                remainingLbl.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                remainingLbl.setAlignment(Pos.CENTER_RIGHT);
                add(remainingLbl, 0, 0);
            }

            {
                valueLbl = new Label(Integer.toString(510));
                valueLbl.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                valueLbl.setAlignment(Pos.CENTER_LEFT);
                add(valueLbl, 1, 0);
            }
        }

        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            int total = 510;
            for (EvEditor evEditor : evEditors) {
                total -= evEditor.getValue();
            }
            valueLbl.setText(Integer.toString(total));
        }
    }

}
