package com.meadowsapps.pkmn.ui;

import com.meadowsapps.pkmn.data.DataTable;
import com.meadowsapps.pkmn.data.Nature;
import com.meadowsapps.pkmn.data.Stat;
import com.meadowsapps.pkmn.ui.control.*;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * Created by Dylan on 9/16/16.
 */
public class StatCalculatorView implements Initializable {

//    private InfoView infoView;
//    private StatView statView;
//
//    @Override
//    public Node initComponents() {
//        BorderPane layoutPanel = new BorderPane();
//
//        infoView = new InfoView();
//        infoView.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
//        layoutPanel.setTop(infoView);
//
//        statView = new StatView();
//        statView.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
//        layoutPanel.setCenter(statView);
//
//        return layoutPanel;
//    }
//
//    @Override
//    protected void propertyChanged(String property) {
//    }
//
//    @Override
//    protected void bind(Pokemon pokemon) {
//        infoView.setPokemon(pokemon);
//        statView.setPokemon(pokemon);
//    }
//
//    @Override
//    protected void unbind(Pokemon pokemon) {
//    }

    @FXML
    private GridPane infoPane;

    @FXML
    private ComboBox pokemonField;

    @FXML
    private ComboBox natureField;

    @FXML
    private Spinner levelField;

    @FXML
    private ComboBox formField;

    @FXML
    private GridPane baseStatPane;

    @FXML
    private Label baseStatTotalLbl;

    @FXML
    private GridPane resultsPane;

    @FXML
    private GridPane evPane;

    @FXML
    private GridPane ivPane;

    @FXML
    private Label evTotalLbl;

    private ModelViewer modelViewer;

    private BaseStatBar[] baseStats;

    private EvEditor[] evEditors;

    private IvEditor[] ivEditors;

    private StatLabel[] resultLbls;

    private SliderGroup evGroup;

    @FXML
    @Override
    public void initialize() {
        boolean showGrid = false;
        infoPane.setGridLinesVisible(showGrid);
        baseStatPane.setGridLinesVisible(showGrid);
        evPane.setGridLinesVisible(showGrid);
        ivPane.setGridLinesVisible(showGrid);

        // InfoView
        {
            pokemonField.getItems().addAll(DataTable.getPokemonTable().getPokemon());
            pokemonField.valueProperty().addListener((observable, oldValue, newValue) -> onPokemonChanged());

            natureField.getItems().addAll(DataTable.getNatureTable().getNatures());
            natureField.getSelectionModel().selectFirst();
            natureField.valueProperty().addListener((observable, oldValue, newValue) -> onNatureChanged());

            levelField.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 50));
            levelField.valueProperty().addListener((observable, oldValue, newValue) -> onLevelChanged());

            formField.getItems().setAll(new String[0]);
            formField.valueProperty().addListener((observable, oldValue, newValue) -> onFormChanged());
            formField.setDisable(true);

            modelViewer = new ModelViewer();
            infoPane.add(modelViewer, 0, 0, 2, 4);
            infoPane.setMargin(modelViewer, new Insets(10, 10, 10, 10));
        }

        baseStats = new BaseStatBar[6];
        evEditors = new EvEditor[6];
        ivEditors = new IvEditor[6];
        resultLbls = new StatLabel[6];
        evGroup = new SliderGroup(510);

        for (Stat stat : Stat.values()) {
            // BaseStatView
            {
                BaseStatBar bar = new BaseStatBar(stat.getFill());
                bar.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                GridPane.setConstraints(bar, 1, stat.ordinal(), 1, 1,
                        HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
                baseStatPane.getChildren().add(bar);
                baseStats[stat.ordinal()] = bar;
            }

            // StatView
            {
                // EV Editor
                {
                    final EvEditor editor = new EvEditor(stat);
                    editor.valueProperty().addListener((observable, oldValue, newValue) -> onEvChanged(editor));
                    editor.setSliderGroup(evGroup);
                    GridPane.setConstraints(editor, 1, stat.ordinal(), 1, 1,
                            HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
                    evPane.getChildren().addAll(editor);
                    evEditors[stat.ordinal()] = editor;
                }

                // IV Editor
                {
                    final IvEditor editor = new IvEditor(stat);
                    editor.valueProperty().addListener((observable, oldValue, newValue) -> onIvChanged(editor));
                    GridPane.setConstraints(editor, 0, stat.ordinal(), 1, 1,
                            HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
                    editor.setMaxWidth(Double.MAX_VALUE);
                    ivPane.getChildren().add(editor);
                    ivEditors[stat.ordinal()] = editor;
                }

                // Stat Label
                {
                    StatLabel label = new StatLabel(stat);
                    GridPane.setConstraints(label, 1, stat.ordinal(), 1, 1,
                            HPos.LEFT, VPos.CENTER, Priority.NEVER, Priority.ALWAYS);
                    resultsPane.getChildren().add(label);
                    resultLbls[stat.ordinal()] = label;
                }
            }
        }
    }

    private void onPokemonChanged() {
        updateForms();
        updateModel();
        updateBaseStats();
        updateStats();
    }

    private void onNatureChanged() {
        updateStats();
    }

    private void onLevelChanged() {
        updateStats();
    }

    private void onFormChanged() {
        updateModel();
        updateBaseStats();
        updateStats();
    }

    private void onEvChanged(EvEditor editor) {
        int value = evGroup.getTotal() - evGroup.getSum();
        evTotalLbl.setText(Integer.toString(value));
        updateStat(editor.getStat());
    }

    private void onIvChanged(IvEditor editor) {
        updateStat(editor.getStat());
    }

    private void updateModel() {
        int pkmnIndex = pokemonField.getSelectionModel().getSelectedIndex() + 1;
        int formIndex = formField.getSelectionModel().getSelectedIndex();
        formIndex = (formIndex == -1) ? 0 : formIndex;
        modelViewer.setModel(pkmnIndex, formIndex);
    }

    private void updateForms() {
        int index = pokemonField.getSelectionModel().getSelectedIndex() + 1;
        String[] forms = DataTable.getFormTable().getForms(index);
        formField.getItems().setAll(forms);
        if (forms.length > 0) {
            formField.getSelectionModel().select(0);
        }
        formField.setDisable(forms.length == 0);
    }

    private void updateBaseStats() {
        int pkmnIndex = pokemonField.getSelectionModel().getSelectedIndex() + 1;
        String form = (String) formField.getSelectionModel().getSelectedItem();
        Integer[] baseStats = DataTable.getBaseStatTable().getBaseStats(pkmnIndex, form);

        int sum = 0;
        for (Stat stat : Stat.values()) {
            this.baseStats[stat.ordinal()].setValue(baseStats[stat.ordinal()]);
            sum += baseStats[stat.ordinal()];
        }
        baseStatTotalLbl.setText(Integer.toString(sum));
    }

    private void updateStats() {
        for (Stat stat : Stat.values()) {
            updateStat(stat);
        }
    }

    private void updateStat(Stat stat) {
        if (pokemonField.getSelectionModel().getSelectedIndex() != -1) {
            int baseStat = baseStats[stat.ordinal()].getValue();
            int iv = ivEditors[stat.ordinal()].getValue();
            int ev = evEditors[stat.ordinal()].getValue();
            int level = (Integer) levelField.getValueFactory().getValue();

            int value = 0;
            if (stat == Stat.HP) {
                value = (int) Math.floor(((2 * baseStat + iv + Math.floor(ev / 4)) * level) / 100) + level + 10;
            } else {
                Nature nature = Nature.valueOf((String) natureField.getSelectionModel().getSelectedItem());
                double modifier = DataTable.getNatureTable().getModifier(nature, stat);
                value = (int) Math.floor((Math.floor(((2 * baseStat + iv + Math.floor(ev / 4)) * level) / 100) + 5) * modifier);
            }
            resultLbls[stat.ordinal()].setValue(value);
        }
    }

}
