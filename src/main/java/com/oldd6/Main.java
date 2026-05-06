package com.oldd6;

import com.formdev.flatlaf.FlatSystemProperties;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Main extends JFrame implements ActionListener{
    JLabel l;
    JButton b, b2, b3;
    static SessionFactory sf;

    Main(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.getContentPane().setBackground(Color.BLACK);
        this.setLayout(new GridLayout(0,1));
        this.setIconImage(new ImageIcon("WEATHER.png").getImage());
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                sf.close();
            }
        });

        l = new JLabel("ПОГОДА");
        l.setFont(new Font("Roboto", Font.BOLD, 48));
        l.setForeground(Color.LIGHT_GRAY);
        l.setHorizontalAlignment(JLabel.CENTER);
        l.setVerticalAlignment(JLabel.TOP);
        this.add(l);

        b = new JButton("Внести дані про погоду");
        b.addActionListener(this);
        b.setFont(new Font("Roboto", Font.BOLD, 48));
        b.setForeground(Color.BLACK);
        b.setBackground(Color.GRAY);
        this.add(b);

        b2 = new JButton("Переглянути погодну статистику");
        b2.addActionListener(this);
        b2.setFont(new Font("Roboto", Font.BOLD, 45));
        b2.setForeground(Color.BLACK);
        b2.setBackground(Color.GRAY);
        this.add(b2);

        b3 = new JButton("ВИДАЛИТИ ДАНІ");
        b3.addActionListener(this);
        b3.setFont(new Font("Roboto", Font.BOLD, 48));
        b3.setForeground(Color.BLACK);
        b3.setBackground(Color.GRAY);
        this.add(b3);

        this.setVisible(true);
    }

    public static void main(String[] args){
        sf = new Configuration()
                .configure()
                .addAnnotatedClass(Weather.class)
                .buildSessionFactory();
        System.setProperty(FlatSystemProperties.UI_SCALE, "140%");
        FlatMacLightLaf.setup();
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }


    public void actionPerformed(ActionEvent e){
        if(e.getSource()==b) new Fill();
        else if(e.getSource()==b2) new Show();
        else new Delete();
    }
}
