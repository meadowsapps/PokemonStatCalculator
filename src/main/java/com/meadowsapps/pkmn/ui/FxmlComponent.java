package com.meadowsapps.pkmn.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by dmeadows on 9/19/16.
 */
public abstract class FxmlComponent<T extends Node> extends AnchorPane implements Initializable {

    private static FXMLLoader loader = new FXMLLoader();

    public FxmlComponent() {
        String fxml = this.getClass().getSimpleName() + ".fxml";
        load(fxml);
        initialize();
    }

    public FxmlComponent(String fxml) {
        load(fxml);
        initialize();
    }

    private void load(String fxml) {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("fxml/" + fxml);
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public T getLayout() {
        T layout = null;
        for (Node child : getChildren()) {
            if (child.getId().equals("layout")) {
                layout = (T) child;
            }
        }
        return layout;
    }

}
