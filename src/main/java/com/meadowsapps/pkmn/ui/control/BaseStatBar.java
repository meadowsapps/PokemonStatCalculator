package com.meadowsapps.pkmn.ui.control;

import com.meadowsapps.pkmn.ui.Component;
import javafx.geometry.Bounds;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Created by Dylan on 9/16/16.
 */
public class BaseStatBar extends Component {

    private Label label;
    private Rectangle bar;

    public BaseStatBar(Color fill) {
        setFill(fill);
    }

    @Override
    public Node initComponents() {
        GridPane layoutPanel = new GridPane();
        layoutPanel.setGridLinesVisible(true);
        layoutPanel.setHgap(10);
        layoutPanel.setVgap(10);

        Text text = new Text("000");
        Bounds bounds = text.getLayoutBounds();
        double width = bounds.getWidth() + 10 + 255;
        setMinWidth(width);
        setPrefWidth(width);
        setMaxWidth(width);


        // Label
        {
            label = new Label();
            label.setMinWidth(bounds.getWidth());
            label.setPrefWidth(bounds.getWidth());
            label.setMaxWidth(bounds.getWidth());
            GridPane.setConstraints(label, 0, 0, 1, 1,
                    HPos.RIGHT, VPos.CENTER, Priority.NEVER, Priority.NEVER);
            layoutPanel.getChildren().add(label);
        }

        // Bar
        {
            bar = new Rectangle(0, 20);
            GridPane.setConstraints(bar, 1, 0, 1, 1,
                    HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
            layoutPanel.getChildren().add(bar);
        }

        int value = (int) Math.random() * 255;
        setValue(value);

        return layoutPanel;
    }

    public int getValue() {
        return ((int) bar.getWidth());
    }

    public void setValue(int value) {
        label.setText(Integer.toString(value));
        bar.setWidth(value);
    }

    public Color getFill() {
        return (Color) bar.getFill();
    }

    public void setFill(Color value) {
        bar.setFill(value);
    }

}
