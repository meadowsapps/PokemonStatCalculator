package com.meadowsapps.pkmn;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by dmeadows on 9/15/16.
 */
public class ImageTester {

    private static JLabel imageLabel;
    private static JComboBox pokemonCmb;
    private static JComboBox formCmb;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        {
            panel.setLayout(new BorderLayout());
            imageLabel = new JLabel();
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(imageLabel, BorderLayout.CENTER);

            JPanel controlPanel = new JPanel();
            {
                ActionListener listener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            int pkmnIndex = pokemonCmb.getSelectedIndex() + 1;
                            int formIndex = formCmb.getSelectedIndex();

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
                            System.out.println(fileName);

                            InputStream stream = ImageTester.class.getClassLoader().getResourceAsStream("images/models/" + fileName);
                            ImageIcon icon = new ImageIcon(ImageIO.read(stream));
                            imageLabel.setIcon(icon);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                };

                controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
                pokemonCmb = new JComboBox(DataTable.getPokemonTable().getPokemon());
                pokemonCmb.addActionListener(listener);
                pokemonCmb.addItemListener(e -> {
                    int index = pokemonCmb.getSelectedIndex() + 1;
                    String[] forms = DataTable.getFormTable().getForms(index);
                    ComboBoxModel model = new DefaultComboBoxModel(forms);
                    formCmb.setModel(model);
                });
                controlPanel.add(pokemonCmb);

                formCmb = new JComboBox(DataTable.getFormTable().getForms(1));
                formCmb.addActionListener(listener);
                controlPanel.add(formCmb);
            }

            panel.add(controlPanel, BorderLayout.SOUTH);
        }
        frame.add(panel);
        frame.setVisible(true);
        frame.setSize(200, 200);
    }
}
