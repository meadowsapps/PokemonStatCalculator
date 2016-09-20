package com.meadowsapps.pkmn.ui;

import com.meadowsapps.pkmn.ui.control.ModelViewer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * Created by dmeadows on 9/19/16.
 */
public class InfoViewController extends FxmlComponent<GridPane> {

    @FXML
    private ComboBox pokemonEditor;

    @FXML
    private ComboBox natureEditor;

    @FXML
    private Spinner levelEditor;

    @FXML
    private ComboBox formEditor;

    private ModelViewer viewer;

    public InfoViewController() {
        super("InfoView.fxml");
    }

    @Override
    public void initialize() {
        viewer = new ModelViewer();
        GridPane.setConstraints(viewer, 0, 0, 1, 5,
                HPos.CENTER, VPos.CENTER, Priority.SOMETIMES, Priority.SOMETIMES);
    }

    @FXML
    protected void onPokemonChange(ActionEvent e) {
        System.out.println("Pokemon Changed");
    }
}
