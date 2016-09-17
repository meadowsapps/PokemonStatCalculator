package com.meadowsapps.pkmn.ui;

import com.meadowsapps.pkmn.data.Pokemon;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

/**
 * Created by Dylan on 9/16/16.
 */
public class StatCalculatorView extends Component {

    private Pokemon pokemon;
    private InfoView infoView;
    private StatView statView;

    @Override
    public Node initComponents() {
        BorderPane layoutPanel = new BorderPane();

        infoView = new InfoView();
        infoView.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        layoutPanel.setTop(infoView);

        statView = new StatView();
        statView.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        layoutPanel.setCenter(statView);

        return layoutPanel;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
        infoView.bind(pokemon);
    }
}
