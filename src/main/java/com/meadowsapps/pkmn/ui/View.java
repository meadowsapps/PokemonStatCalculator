package com.meadowsapps.pkmn.ui;

import com.meadowsapps.pkmn.data.Pokemon;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.HashMap;

/**
 * Created by Dylan on 9/17/16.
 */
public abstract class View extends Component implements ChangeListener {

    private Pokemon pokemon;

    private HashMap<Object, String> propertyMap;

    public View() {
        propertyMap = new HashMap<>();
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        if (observable instanceof ReadOnlyProperty) {
            Object editor = ((ReadOnlyProperty) observable).getBean();
            if (propertyMap.containsKey(editor)) {
                String property = propertyMap.get(editor);

            }
        }
    }

    public void registerProperty(String property, Object editor) {
        propertyMap.put(editor, property);
    }

    public void deregisterProperty(String property) {
        Object editor = null;
        for (Object key : propertyMap.keySet()) {
            if (propertyMap.get(key).equals(property)) {
                editor = key;
                break;
            }
        }
        propertyMap.remove(editor);
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
