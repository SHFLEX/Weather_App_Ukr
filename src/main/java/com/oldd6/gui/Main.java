package com.oldd6.gui;

import com.formdev.flatlaf.FlatSystemProperties;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.oldd6.users.Hydrologist;
import com.oldd6.users.Meteorologist;
import com.oldd6.users.Synoptic;
import com.oldd6.users.TechSupport;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URI;


public class Main extends JFrame implements ActionListener{
    JLabel l;
    JButton b, b2, b3, b4, b5, b6;
    static SessionFactory sf;

    Main(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 960);
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
        l.setFont(new Font("Roboto", Font.BOLD, 64));
        l.setForeground(Color.LIGHT_GRAY);
        l.setHorizontalAlignment(JLabel.CENTER);
        l.setVerticalAlignment(JLabel.TOP);
        this.add(l);

        b = new JButton("Внести/оновити дані про погоду");
        b.addActionListener(this);
        b.setFont(new Font("Roboto", Font.BOLD, 64));
        b.setForeground(Color.BLACK);
        b.setBackground(Color.GRAY);
        this.add(b);

        b2 = new JButton("Переглянути погодну статистику");
        b2.addActionListener(this);
        b2.setFont(new Font("Roboto", Font.BOLD, 64));
        b2.setForeground(Color.BLACK);
        b2.setBackground(Color.GRAY);
        this.add(b2);

        b3 = new JButton("Видалити погодні дані");
        b3.addActionListener(this);
        b3.setFont(new Font("Roboto", Font.BOLD, 64));
        b3.setForeground(Color.BLACK);
        b3.setBackground(Color.GRAY);
        this.add(b3);

        b4 = new JButton("Написати листа на пошту");
        b4.addActionListener(this);
        b4.setFont(new Font("Roboto", Font.BOLD, 64));
        b4.setForeground(Color.BLACK);
        b4.setBackground(Color.GRAY);
        this.add(b4);

        b5 = new JButton("Відкрити поштову скриньку");
        b5.addActionListener(this);
        b5.setFont(new Font("Roboto", Font.BOLD, 64));
        b5.setForeground(Color.BLACK);
        b5.setBackground(Color.GRAY);
        this.add(b5);

        b6 = new JButton("Видалити обліковий запис");
        b6.setBackground(Color.GRAY);
        b6.setForeground(Color.BLACK);
        b6.setFont(new Font("Roboto", Font.BOLD, 64));
        b6.addActionListener(this);
        this.add(b6);

        this.setVisible(true);
    }

    public static void main(String[] args){
        sf = new Configuration()
                .configure()
                .addAnnotatedClass(Weather.class)
                .addAnnotatedClass(Synoptic.class)
                .addAnnotatedClass(Meteorologist.class)
                .addAnnotatedClass(Hydrologist.class)
                .addAnnotatedClass(TechSupport.class)
                .buildSessionFactory();
        System.setProperty(FlatSystemProperties.UI_SCALE, "140%");
        FlatMacLightLaf.setup();
        SwingUtilities.invokeLater(() -> new SignInUp().setVisible(true));
    }


    public void actionPerformed(ActionEvent e){
        if(e.getSource()==b) new Fill();
        else if(e.getSource()==b2) new Show();
        else if(e.getSource()==b3) new Delete();
        else if(e.getSource()==b4) new Letter();
        else if(e.getSource()==b5){
            try {
                Desktop.getDesktop().browse(new URI("https://mail.google.com"));
            }catch(Exception ex){
                JLabel sav = new JLabel("Помилка");
                sav.setFont(new Font("Rubik", Font.BOLD, 28));
                sav.setForeground(Color.BLACK);
                JOptionPane.showMessageDialog(null, sav, "Збережено",
                        JOptionPane.INFORMATION_MESSAGE, new ImageIcon("WEATHER.png"));
            }
        }else{
            new DeleteUser();
        }
    }
}
