package com.meadowsapps.pkmn.ui.control;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Bounds;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Created by Dylan on 9/16/16.
 */
public class BaseStatBar extends GridPane implements Initializable {

    private int value;

    private Label label;

    private Rectangle bar;

    public BaseStatBar(Color fill) {
        initialize();
        bar.setFill(fill);
    }

    @Override
    public void initialize() {
        boolean showGrid = false;
        setGridLinesVisible(showGrid);
        setHgap(10);
        setVgap(10);

        Text text = new Text("000");
        Bounds bounds = text.getLayoutBounds();


        // Label
        {
            ColumnConstraints constraints = new ColumnConstraints();
            constraints.setMinWidth(10);
            constraints.setMaxWidth(bounds.getWidth());
            constraints.setPrefWidth(bounds.getWidth());
            getColumnConstraints().add(constraints);

            label = new Label();
            GridPane.setConstraints(label, 0, 0, 1, 1,
                    HPos.RIGHT, VPos.CENTER, Priority.NEVER, Priority.ALWAYS);
            getChildren().add(label);
        }

        // Bar
        {
            ColumnConstraints constraints = new ColumnConstraints();
            constraints.setMinWidth(10);
            constraints.setPrefWidth(100);
            getColumnConstraints().add(constraints);

            bar = new Rectangle(0, 0);
            final AnchorPane barPane = new AnchorPane();
            ChangeListener sizeListener = (observable, oldValue, newValue) -> {
                double width = barPane.getWidth();
                bar.setWidth((width / 255) * value);
                double height = barPane.getHeight();
                bar.setHeight(height);
            };
            barPane.widthProperty().addListener(sizeListener);
            barPane.heightProperty().addListener(sizeListener);
            barPane.getChildren().add(bar);
            GridPane.setConstraints(barPane, 1, 0, 1, 1,
                    HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
            getChildren().add(barPane);
//            add(barPane, 1, 0);
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        label.setText(Integer.toString(value));
        double width = ((AnchorPane) bar.getParent()).getWidth();
        bar.setWidth((width / 255) * value);
    }
}
