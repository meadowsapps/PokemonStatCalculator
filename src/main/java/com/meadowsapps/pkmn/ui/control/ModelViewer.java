package com.meadowsapps.pkmn.ui.control;

import com.meadowsapps.pkmn.PkmnUtils;
import com.meadowsapps.pkmn.data.DataTable;
import com.meadowsapps.pkmn.ui.FxmlComponent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.InputStream;

/**
 * Created by Dylan on 9/15/16.
 */
public class ModelViewer extends FxmlComponent<GridPane> {

    @FXML
    private ImageView modelViewer;

    @FXML
    private ImageView backgroundImage;

    @Override
    public void initialize() {
        InputStream stream = PkmnUtils.getResourceAsStream("images/pokeball.gif");
        Image backgroundImage = new Image(stream);
        this.backgroundImage.setImage(backgroundImage);
        double width = backgroundImage.getWidth();
        double height = backgroundImage.getHeight();
        setPrefSize(width, height);
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
        double bgWidth = backgroundImage.getImage().getWidth();
        double width = (model.getWidth() > bgWidth) ? bgWidth : 0.0;
        modelViewer.setPreserveRatio(width != 0.0);
        modelViewer.setFitWidth(width);
        modelViewer.setImage(model);
    }

}
