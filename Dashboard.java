package com.company;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JPanel {
    private JLabel score;
    private JLabel individuals;
    private JLabel alive_individuals;
    private Button startButton;
    private Button newGeneration;
    private Button stopButton;
    private World world;
    private GeneticPool genePool;

    public Dashboard(World world){
        this.world = world;
        setVisible(true);
        setBorder(new BasicBorders.MarginBorder());
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void init(GeneticPool genePool){
        this.genePool = genePool;
        score = new JLabel("Best doodle score : ");
        individuals = new JLabel("Number of individuals in the generation :");
        alive_individuals = new JLabel("Number of individuals alive in the generation");

        stopButton = new Button("END GENERATION");
        newGeneration = new Button("LAUNCH NEW GENERATION");

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                world.gameOver();
            }
        });

        newGeneration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                world.launchNextGeneration();
            }
        });

        add(score);
        add(individuals);
        add(alive_individuals);
        add(stopButton);
        add(newGeneration);
    }

    public void updateDashboard(){
        individuals.setText("Number of individuals in the generation : " + genePool.getIndividuals().size());
        alive_individuals.setText("Number of individuals alive in the generation : " + genePool.getAliveDoodles().size());
        score.setText("Best doodle score : " + String.valueOf(world.getBestDoodle().getScore()));
    }
}
