package com.meadowsapps.pkmn.ui;

import com.meadowsapps.pkmn.data.Stat;
import com.meadowsapps.pkmn.ui.control.BaseStatBar;
import com.meadowsapps.pkmn.ui.control.EvEditor;
import com.meadowsapps.pkmn.ui.control.SliderGroup;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Created by Dylan on 9/15/16.
 */
public class StatView extends Component implements ChangeListener {

    private BaseStatBar[] baseStats;
    private Label baseStatTotalValue;
    private EvEditor[] evs;
    private Spinner<Integer>[] ivs;
    private RemainingEvsLabel remainingEvs;
    private SliderGroup group;

    @Override
    public Node initComponents() {
        baseStats = new BaseStatBar[6];
        evs = new EvEditor[6];
        ivs = new Spinner[6];
        group = new SliderGroup(510);

        GridPane layoutPanel = new GridPane();
        layoutPanel.setGridLinesVisible(false);
        layoutPanel.setPadding(new Insets(10, 10, 10, 10));
        String style = "-fx-border-color: lightgrey;";
        style += " -fx-border-radius: 4px;";
        layoutPanel.setStyle(style);
        layoutPanel.setHgap(10);
        layoutPanel.setVgap(10);

        // Header Labels
        {
            Font headerFont = new Font(15);
            {
                // Base Stat Label
                Label baseStatLbl = new Label("Base Stats");
                baseStatLbl.setFont(headerFont);
                layoutPanel.add(baseStatLbl, 1, 0);
            }

            {
                // EVs Label
                Label evsLbl = new Label("EVs");
                evsLbl.setFont(headerFont);
                layoutPanel.add(evsLbl, 3, 0);
            }

            {
                // IVs Label
                Label ivsLbl = new Label("IVs");
                ivsLbl.setFont(headerFont);
                layoutPanel.add(ivsLbl, 5, 0);
            }
        }


        for (Stat stat : Stat.values()) {
            int index = stat.ordinal();
            // Stat Label
            {
                Label label = new Label(stat.name() + ":");
                label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                label.setAlignment(Pos.CENTER_RIGHT);
                GridPane.setConstraints(label, 0, index + 1, 1, 1,
                        HPos.RIGHT, VPos.CENTER, Priority.NEVER, Priority.NEVER);
                layoutPanel.getChildren().add(label);
            }

            // Base Stat Bar
            {
                BaseStatBar baseStat = new BaseStatBar(stat.getFill());
                baseStat.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                GridPane.setConstraints(baseStat, 1, index + 1, 1, 1,
                        HPos.LEFT, VPos.CENTER, Priority.NEVER, Priority.NEVER);
                layoutPanel.getChildren().add(baseStat);
                baseStats[index] = baseStat;
            }

            // EV Editor
            {
                EvEditor ev = new EvEditor();
                ev.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                ev.addChangeListener(this);
                ev.setSliderGroup(group);
                GridPane.setConstraints(ev, 3, index + 1, 1, 1,
                        HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
                layoutPanel.getChildren().add(ev);
                evs[index] = ev;
            }

            // IV Editor
            {
                Spinner<Integer> iv = new Spinner<>(0, 31, 31);
                iv.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                GridPane.setConstraints(iv, 5, index + 1, 1, 1,
                        HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
                layoutPanel.getChildren().add(iv);
                ivs[index] = iv;
            }
        }

        Separator separator1 = new Separator(Orientation.VERTICAL);
        GridPane.setConstraints(separator1, 2, 1, 1, 6,
                HPos.CENTER, VPos.CENTER, Priority.NEVER, Priority.NEVER);
        layoutPanel.getChildren().add(separator1);

        Separator separator2 = new Separator(Orientation.VERTICAL);
        GridPane.setConstraints(separator2, 4, 1, 1, 6,
                HPos.CENTER, VPos.CENTER, Priority.NEVER, Priority.NEVER);
        layoutPanel.getChildren().add(separator2);

        // Base Stat Total
        {
            Label totalLbl = new Label("Total:");
            Font font = totalLbl.getFont();
            font = Font.font(font.getName(), FontWeight.BOLD, font.getSize() + 2);
            totalLbl.setFont(font);
            GridPane.setConstraints(totalLbl, 0, 7, 1, 1,
                    HPos.RIGHT, VPos.CENTER, Priority.NEVER, Priority.NEVER);
            layoutPanel.getChildren().add(totalLbl);

            baseStatTotalValue = new Label(Integer.toString(0));
            baseStatTotalValue.setFont(font);
            baseStatTotalValue.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            GridPane.setConstraints(baseStatTotalValue, 1, 7, 1, 1,
                    HPos.LEFT, VPos.CENTER, Priority.NEVER, Priority.NEVER);
            layoutPanel.getChildren().add(baseStatTotalValue);
        }


        remainingEvs = new RemainingEvsLabel();
        remainingEvs.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        layoutPanel.add(remainingEvs, 3, 7);
        return layoutPanel;
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        int total = 510;
        for (EvEditor evEditor : evs) {
            total -= evEditor.getValue();
        }
        remainingEvs.setValue(total);

        if (observable instanceof ReadOnlyProperty) {
            Slider bean = (Slider) ((ReadOnlyProperty) observable).getBean();
            for (Stat s : Stat.values()) {
                GridPane pane = (GridPane) evs[s.ordinal()].getChildren().get(0);
                if (pane.getChildren().contains(bean)) {
                    baseStats[s.ordinal()].setValue(((int) bean.getValue()));
                }
            }
        }
    }

    class RemainingEvsLabel extends Component {

        private Label valueLbl;

        @Override
        public Node initComponents() {
            GridPane layoutPanel = new GridPane();
            layoutPanel.setHgap(10);
            layoutPanel.setVgap(10);

            {
                Label remainingLbl = new Label("Remaining:");
                remainingLbl.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                remainingLbl.setAlignment(Pos.CENTER_RIGHT);
                layoutPanel.add(remainingLbl, 0, 0);
            }

            {
                valueLbl = new Label(Integer.toString(510));
                valueLbl.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                valueLbl.setAlignment(Pos.CENTER_LEFT);
                layoutPanel.add(valueLbl, 1, 0);
            }

            return layoutPanel;
        }

        public void setValue(int value) {
            valueLbl.setText(Integer.toString(value));
        }

    }

}
