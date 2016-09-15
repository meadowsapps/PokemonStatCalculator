package com.meadowsapps.pkmn;

import com.meadowsapps.pkmn.data.DataTable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.InputStream;

/**
 * Created by Dylan on 9/15/16.
 */
public class ModelViewer extends StackPane {

    private ImageView modelView;

    public ModelViewer() {
        ImageView backgroundViewer = new ImageView();
        InputStream stream = PkmnUtils.getResourceAsStream("images/pokeball.gif");
        Image backgroundImage = new Image(stream);
        backgroundViewer.setImage(backgroundImage);
        getChildren().add(backgroundViewer);

        modelView = new ImageView();
        getChildren().add(modelView);
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
        fileName += (formName != null && !formName.equals("")) ? "-" + formName.toLowerCase() : "";
        fileName += ".gif";

        InputStream stream = PkmnUtils.getResourceAsStream("images/models/" + fileName);
        Image model = new Image(stream);
        modelView.setImage(model);
    }

}
