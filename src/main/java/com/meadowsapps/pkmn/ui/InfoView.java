package com.meadowsapps.pkmn.ui;

import com.meadowsapps.pkmn.data.DataTable;
import com.meadowsapps.pkmn.data.Pokemon;
import com.meadowsapps.pkmn.ui.control.ModelViewer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;

/**
 * Created by Dylan on 9/15/16.
 */
public class InfoView extends Component implements ChangeListener {

    private ModelViewer modelViewer;

    private ComboBox pokemonEditor;

    private ComboBox natureEditor;

    private Spinner levelEditor;

    private ComboBox formEditor;

    public InfoView() {
        pokemonEditor.getSelectionModel().select(0);
    }

    @Override
    public Node initComponents() {
        GridPane layoutPanel = new GridPane();
        layoutPanel.setGridLinesVisible(false);
        layoutPanel.setPadding(new Insets(10, 10, 10, 10));
        String style = "-fx-border-color: lightgrey;";
        style += " -fx-border-radius: 4px;";
        layoutPanel.setStyle(style);
        layoutPanel.setHgap(10);
        layoutPanel.setVgap(10);

        {
            // Model Viewer
            modelViewer = new ModelViewer();
            layoutPanel.add(modelViewer, 0, 0, 1, 5);
        }

        {
            // Pokemon Editor
            Label pokemonLbl = new Label("Pokemon:");
            pokemonLbl.setAlignment(Pos.CENTER_RIGHT);
            pokemonLbl.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            layoutPanel.add(pokemonLbl, 1, 0);

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
            layoutPanel.add(pokemonEditor, 2, 0);
        }

        {
            // Nature Editor
            Label natureLbl = new Label("Nature:");
            natureLbl.setMaxWidth(Double.MAX_VALUE);
            natureLbl.setAlignment(Pos.CENTER_RIGHT);
            layoutPanel.add(natureLbl, 1, 1);

            natureEditor = new ComboBox();
            natureEditor.getItems().addAll(DataTable.getNatureTable().getNatures());
            natureEditor.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            layoutPanel.add(natureEditor, 2, 1);
        }

        {
            // Level Editor
            Label levelLbl = new Label("Level:");
            levelLbl.setMaxWidth(Double.MAX_VALUE);
            levelLbl.setAlignment(Pos.CENTER_RIGHT);
            layoutPanel.add(levelLbl, 1, 2);

            levelEditor = new Spinner<Integer>(1, 100, 50);
            levelEditor.setEditable(true);
            levelEditor.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            layoutPanel.add(levelEditor, 2, 2);
        }

        Separator separator = new Separator(Orientation.HORIZONTAL);
        separator.setMaxWidth(Double.MAX_VALUE);
        separator.setPadding(new Insets(0, 10, 0, 10));
        layoutPanel.add(separator, 1, 3, 2, 1);

        {
            // Form Editor
            Label formLbl = new Label("Form:");
            formLbl.setAlignment(Pos.CENTER_RIGHT);
            formLbl.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            layoutPanel.add(formLbl, 1, 4);

            formEditor = new ComboBox();
            String[] forms = new String[0];
            formEditor.getItems().setAll(forms);
            formEditor.valueProperty().addListener(this);
            formEditor.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            layoutPanel.add(formEditor, 2, 4);
        }
        return layoutPanel;
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        int pkmnIndex = pokemonEditor.getSelectionModel().getSelectedIndex() + 1;
        int formIndex = formEditor.getSelectionModel().getSelectedIndex();
        formIndex = (formIndex == -1) ? 0 : formIndex;
        modelViewer.setModel(pkmnIndex, formIndex);
    }

    public void bind(Pokemon pokemon) {
        pokemon.getName().bindBidirectional(pokemonEditor.valueProperty());
        pokemon.getNature().bindBidirectional(natureEditor.valueProperty());
        pokemon.getLevel().bindBidirectional(levelEditor.getValueFactory().valueProperty());
        pokemon.getForm().bindBidirectional(formEditor.valueProperty());
    }

    public void unbind(Pokemon pokemon) {
        pokemon.getName().unbindBidirectional(pokemonEditor.valueProperty());
        pokemon.getNature().unbindBidirectional(natureEditor.valueProperty());
        pokemon.getLevel().unbindBidirectional(levelEditor.getValueFactory().valueProperty());
        pokemon.getForm().unbindBidirectional(formEditor.valueProperty());
    }

    private void setModel(String pokemon, String form) {
        int dexNumber = DataTable.getPokemonTable().getDexNumber(pokemon);
        int formIndex = 0;
        String[] forms = DataTable.getFormTable().getForms(dexNumber);
        for (String f : forms) {
            if (f.equals(form)) {
                break;
            }
            formIndex++;
        }
        formIndex = (formIndex == forms.length) ? 0 : formIndex;
        modelViewer.setModel(dexNumber, formIndex);
    }

}
