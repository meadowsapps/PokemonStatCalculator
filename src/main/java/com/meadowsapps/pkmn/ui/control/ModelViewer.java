package com.meadowsapps.pkmn.ui.control;

import com.meadowsapps.pkmn.util.PkmnUtils;
import com.meadowsapps.pkmn.data.DataTable;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.InputStream;

/**
 * Created by Dylan on 9/15/16.
 */
public class ModelViewer extends StackPane implements Initializable {

    private ImageView modelView;

    private ImageView backgroundView;

    public ModelViewer() {
        initialize();
    }

    @Override
    public void initialize() {
        getStyleClass().addAll("view");
        setPadding(new Insets(5, 5, 5, 5));

        {
            backgroundView = new ImageView();
            InputStream stream = PkmnUtils.getResourceAsStream("images/pokeball.gif");
            Image backgroundImage = new Image(stream);
            backgroundView.setImage(backgroundImage);
            getChildren().add(backgroundView);
            setPrefSize(backgroundImage.getWidth(), backgroundImage.getHeight());
        }

        {
            modelView = new ImageView();
            modelView.setSmooth(true);
            getChildren().add(modelView);
        }
    }

    public void setModel(int pkmnIndex, int formIndex) {
        String pkmnName = DataTable.getPokemonTable().getPokemon(pkmnIndex);
        pkmnName = pkmnName.replaceAll(" ", "_");

        String formName = null;
        String[] forms = DataTable.getFormTable().getForms(pkmnIndex);
        if (forms.length > 0) {
            formName = forms[formIndex];
            formName = formName.replaceAll(" ", "_");
        }

        String fileName = pkmnName.toLowerCase();
        fileName += (formName != null && !formName.equals("") && !formName.equals("Normal")) ? "-" + formName.toLowerCase() : "";
        fileName += ".gif";

        InputStream stream = PkmnUtils.getResourceAsStream("images/models/" + fileName);
        Image model = new Image(stream);
        double bgWidth = backgroundView.getImage().getWidth();
        double width = (model.getWidth() > bgWidth) ? bgWidth : 0.0;
        modelView.setPreserveRatio(width != 0.0);
        modelView.setFitWidth(width);
        modelView.setImage(model);
    }

}
