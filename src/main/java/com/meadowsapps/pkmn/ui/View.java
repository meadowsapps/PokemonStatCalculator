package com.meadowsapps.pkmn.ui;

import com.meadowsapps.pkmn.data.Pokemon;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.Vector;

/**
 * Created by Dylan on 9/17/16.
 */
public abstract class View extends Component implements ChangeListener {

    private Pokemon pokemon;

    private Vector<Property> properties;

    public View() {
        properties = new Vector<>();
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        if (observable instanceof Property) {
            Property property = (Property) observable;
            if (properties.contains(property)) {
                propertyChanged(property.getName());
            }
        }
    }

    public void registerProperty(Property property) {
        if (!properties.contains(property)) {
            property.addListener(this);
            properties.add(property);
        }
    }

    public void deregisterProperty(Property property) {
        if (properties.contains(property)) {
            property.removeListener(this);
            properties.remove(property);
        }
    }

    protected abstract void propertyChanged(String property);

    protected abstract void bind(Pokemon pokemon);

    protected abstract void unbind(Pokemon pokemon);

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        unbind(this.pokemon);
        this.pokemon = pokemon;
        bind(this.pokemon);
    }

}
