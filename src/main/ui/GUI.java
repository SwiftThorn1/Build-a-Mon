package ui;

import model.Pokemon;
import model.PokemonList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Graphical user interface that lets user create custom Pokemon
// Code taken from TrafficLightGUI
public class GUI extends JFrame {
    private static final String JSON_STORE = "./data/pokemonlist.json";
    private static final ImageIcon IMAGE_ICON = new ImageIcon("./images/image.png");
    private static final int HEIGHT = 650;
    private static final int WIDTH =  650;

    private PokemonList list;

    private JPanel container;
    private JPanel mainPanel;
    private JPanel textPanel;

    private JTextField nameField;
    private JTextField type1Field;
    private JTextField type2Field;
    private JTextField hpField;
    private JTextField atkField;
    private JTextField defField;
    private JTextField spaField;
    private JTextField spdField;
    private JTextField speField;
    private JTextField removeField;

    private JScrollPane scrollPane;
    private JTextArea textArea;

    private JLabel nameLabel;
    private JLabel type1Label;
    private JLabel type2Label;
    private JLabel hpLabel;
    private JLabel atkLabel;
    private JLabel defLabel;
    private JLabel spaLabel;
    private JLabel spdLabel;
    private JLabel speLabel;

    // EFFECTS: makes JFrame for Pokemon editor
    public GUI() {
        super("Pokemon Editor");
        setSize(WIDTH, HEIGHT);
        list = new PokemonList();
        initializeMenu();

        setupTextArea();
        setupTextPanel();

        initializeAttributeLabels();
        initializeAttributeFields();

        setupMainPanel();

        addAttributeRows();

        initializeAddAndRemove();

        setupContainer();
        add(container);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // EFFECTS: creates main JPanel containing attribute fields and buttons
    private void setupMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 3));
    }

    // EFFECTS: creates JPanel containing image and text area
    private void setupTextPanel() {
        textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout());
        textPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        addImage();
        textPanel.add(Box.createVerticalStrut(20));
        textPanel.add(scrollPane, BorderLayout.SOUTH);
    }

    // EFFECTS: creates text area that displays Pokemon collection
    private void setupTextArea() {
        textArea = new JTextArea(4,20);
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea);
    }

    // EFFECTS: creates JFrame that contains everything
    private void setupContainer() {
        container = new JPanel();
        container.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        container.setLayout(new BorderLayout());
        container.add(textPanel, BorderLayout.NORTH);
        container.add(mainPanel, BorderLayout.SOUTH);
    }

    // EFFECTS: creates the add and remove buttons and adds them
    private void initializeAddAndRemove() {
        JButton addButton = new JButton("Add");
        addButton.setActionCommand("add");
        addButton.addActionListener(new AddListener());

        JButton removeButton = new JButton("Remove");
        removeButton.setActionCommand("remove");
        removeButton.addActionListener(new RemoveListener());

        removeField = new JTextField();

        mainPanel.add(Box.createVerticalStrut(1));
        mainPanel.add(Box.createVerticalStrut(1));
        mainPanel.add(addButton);
        mainPanel.add(Box.createVerticalStrut(1));
        mainPanel.add(Box.createVerticalStrut(1));
        mainPanel.add(Box.createVerticalStrut(1));
        mainPanel.add(Box.createVerticalStrut(1));
        mainPanel.add(removeField);
        mainPanel.add(removeButton);
    }

    // EFFECTS: adds image to top of text panel
    private void addImage() {
        JLabel image = new JLabel(IMAGE_ICON);
        textPanel.add(image, BorderLayout.NORTH);
    }

    // EFFECTS: creates menu for saving and loading
    private void initializeMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem load = new JMenuItem("Load");

        save.addActionListener(new SaveListener());
        load.addActionListener(new LoadListener());

        menu.add(save);
        menu.add(load);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    // EFFECTS: initializes fields that let user set attributes
    private void initializeAttributeFields() {
        nameField = new JTextField();
        type1Field = new JTextField();
        type2Field = new JTextField();
        hpField = new JTextField();
        atkField = new JTextField();
        defField = new JTextField();
        spaField = new JTextField();
        spdField = new JTextField();
        speField = new JTextField();
    }

    // EFFECTS: initializes labels with attributes
    private void initializeAttributeLabels() {
        nameLabel = new JLabel("Name");
        type1Label = new JLabel("Type 1");
        type2Label = new JLabel("Type 2");
        hpLabel = new JLabel("HP");
        atkLabel = new JLabel("Attack");
        defLabel = new JLabel("Defense");
        spaLabel = new JLabel("Sp. Attack");
        spdLabel = new JLabel("Sp. Defense");
        speLabel = new JLabel("Speed");
    }

    // EFFECTS: adds each row of attributes
    private void addAttributeRows() {
        addAttributeRow(nameLabel, nameField);
        addAttributeRow(type1Label, type1Field);
        addAttributeRow(type2Label, type2Field);
        addAttributeRow(hpLabel, hpField);
        addAttributeRow(atkLabel, atkField);
        addAttributeRow(defLabel, defField);
        addAttributeRow(spaLabel, spaField);
        addAttributeRow(spdLabel, spdField);
        addAttributeRow(speLabel, speField);
    }

    // EFFECTS: adds row for one attribute to the screen with correct spacing
    private void addAttributeRow(JLabel nameLabel, JTextField nameField) {
        mainPanel.add(nameLabel);
        mainPanel.add(Box.createHorizontalStrut(2));
        mainPanel.add(nameField);
    }

    // EFFECTS: updates Pokemon collection
    private void updateList() {
        textArea.setText(list.toString());
    }

    // ActionListener for addButton
    class AddListener implements ActionListener {
        // EFFECTS: adds Pokemon from attribute fields to list
        @Override
        public void actionPerformed(ActionEvent e) {
            Pokemon pokemon = buildPokemon();
            list.add(pokemon);
            updateList();
            clearAttributeFields();
        }

        // EFFECTS: clears all attribute fields
        private void clearAttributeFields() {
            nameField.setText("");
            type1Field.setText("");
            type2Field.setText("");
            hpField.setText("");
            atkField.setText("");
            defField.setText("");
            spaField.setText("");
            spdField.setText("");
            speField.setText("");
        }

        // REQUIRES: stat (hp - spe) attribute fields have properly formatted integer values
        // EFFECTS: constructs a Pokemon using values from attribute fields
        private Pokemon buildPokemon() {
            String name = nameField.getText();
            String type1 = type1Field.getText();
            String type2 = type2Field.getText();
            int hp = Integer.parseInt(hpField.getText());
            int atk = Integer.parseInt(atkField.getText());
            int def = Integer.parseInt(defField.getText());
            int spa = Integer.parseInt(spaField.getText());
            int spd = Integer.parseInt(spdField.getText());
            int spe = Integer.parseInt(speField.getText());
            return new Pokemon(name, type1, type2, hp, atk, def, spa, spd, spe);
        }
    }

    // ActionListener for removeButton
    class RemoveListener implements ActionListener {
        // EFFECTS: removes Pokemon with name in removeField
        @Override
        public void actionPerformed(ActionEvent e) {
            if (list.getSize() != 0) {
                list.remove(removeField.getText());
                if (list.getSize() == 0) {
                    textArea.setText("");
                } else {
                    updateList();
                }
                removeField.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "You must add a Pokemon first.");
            }
        }
    }

    // ActionListener for saveButton
    private class SaveListener implements ActionListener {
        // EFFECTS: saves Pokemon collection
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                JsonWriter writer = new JsonWriter(JSON_STORE);
                writer.open();
                writer.write(list);
                writer.close();
                JOptionPane.showMessageDialog(null,
                        "Successfully saved to " + JSON_STORE + "!");
            } catch (FileNotFoundException exception) {
                JOptionPane.showMessageDialog(null, "Could not save to " + JSON_STORE);
            }
        }
    }

    // ActionListener for loadButton
    private class LoadListener implements ActionListener {
        // REQUIRES: list is not empty
        // EFFECTS: loads Pokemon collection
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                JsonReader reader = new JsonReader(JSON_STORE);
                list = reader.read();
                updateList();
                JOptionPane.showMessageDialog(null,"Successfully loaded collection!");
            } catch (IOException exception) {
                JOptionPane.showMessageDialog(null,"Could not load from " + JSON_STORE);
            }
        }
    }
}
