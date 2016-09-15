package com.meadowsapps.pkmn;

import com.meadowsapps.pkmn.data.DataTable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Spinner;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

/**
 * Created by Dylan on 9/15/16.
 */
public class InfoView extends AnchorPane implements ChangeListener {

    private ModelViewer modelViewer;

    private ComboBox pokemonEditor;

    private ComboBox natureEditor;

    private Spinner<Integer> levelEditor;

    private ComboBox formEditor;

    public InfoView() {
        initComponents();
        pokemonEditor.getSelectionModel().select(0);
        natureEditor.getSelectionModel().select(0);
    }

    private void initComponents() {
        GridPane layoutPanel = new GridPane();
        layoutPanel.setGridLinesVisible(false);
        layoutPanel.setPadding(new Insets(10, 10, 10, 10));
        layoutPanel.setHgap(10);
        layoutPanel.setVgap(10);

        getChildren().add(layoutPanel);

        {
            // Header
            Label header = new Label("Info");
            header.setMaxWidth(Double.MAX_VALUE);
            header.setAlignment(Pos.CENTER);
            header.setPadding(new Insets(0, 0, 10, 0));
            Font font = new Font(20);
            header.setFont(font);
            layoutPanel.add(header, 0, 0, 3, 1);
        }

        {
            // Model Viewer
            modelViewer = new ModelViewer();
            layoutPanel.add(modelViewer, 0, 1, 1, 5);
        }

        {
            // Pokemon Editor
            Label pokemonLbl = new Label("Pokemon:");
            pokemonLbl.setAlignment(Pos.CENTER_RIGHT);
            pokemonLbl.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            layoutPanel.add(pokemonLbl, 1, 1);

            pokemonEditor = new ComboBox();
            String[] pokemon = DataTable.getPokemonTable().getPokemon();
            pokemonEditor.getItems().addAll(pokemon);
            pokemonEditor.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    int index = pokemonEditor.getSelectionModel().getSelectedIndex() + 1;
                    String[] forms = DataTable.getFormTable().getForms(index);
                    formEditor.getItems().setAll(forms);
                    if (forms.length > 0) {
                        formEditor.getSelectionModel().select(0);
                    }
                    formEditor.setDisable(forms.length == 0);
                }
            });
            pokemonEditor.valueProperty().addListener(this);
            pokemonEditor.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            layoutPanel.add(pokemonEditor, 2, 1);
        }

        {
            // Nature Editor
            Label natureLbl = new Label("Nature:");
            natureLbl.setMaxWidth(Double.MAX_VALUE);
            natureLbl.setAlignment(Pos.CENTER_RIGHT);
            layoutPanel.add(natureLbl, 1, 2);

            natureEditor = new ComboBox();
            natureEditor.getItems().addAll(DataTable.getNatureTable().getNatures());
            natureEditor.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            layoutPanel.add(natureEditor, 2, 2);
        }

        {
            // Level Editor
            Label levelLbl = new Label("Level:");
            levelLbl.setMaxWidth(Double.MAX_VALUE);
            levelLbl.setAlignment(Pos.CENTER_RIGHT);
            layoutPanel.add(levelLbl, 1, 3);

            levelEditor = new Spinner<Integer>(1, 100, 50);
            levelEditor.setEditable(true);
            levelEditor.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            layoutPanel.add(levelEditor, 2, 3);
        }

        Separator separator = new Separator(Orientation.HORIZONTAL);
        separator.setMaxWidth(Double.MAX_VALUE);
        separator.setPadding(new Insets(0, 10, 0, 10));
        layoutPanel.add(separator, 1, 4, 2, 1);

        {
            // Form Editor
            Label formLbl = new Label("Form:");
            formLbl.setAlignment(Pos.CENTER_RIGHT);
            formLbl.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            layoutPanel.add(formLbl, 1, 5);

            formEditor = new ComboBox();
            String[] forms = new String[0];
            formEditor.getItems().setAll(forms);
            formEditor.valueProperty().addListener(this);
            formEditor.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            layoutPanel.add(formEditor, 2, 5);
        }
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        int pkmnIndex = pokemonEditor.getSelectionModel().getSelectedIndex() + 1;
        int formIndex = formEditor.getSelectionModel().getSelectedIndex();
        formIndex = (formIndex == -1) ? 0 : formIndex;
        modelViewer.setModel(pkmnIndex, formIndex);
    }
}
