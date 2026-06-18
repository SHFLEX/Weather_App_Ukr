package com.oldd6.gui;

import com.oldd6.users.*;
import org.hibernate.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SignIn extends JFrame implements ActionListener{
    JLabel l, l2, l3;
    JTextField tf, tf2;
    JButton b;
    boolean found = false;

    SignIn(){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500, 600);
        this.getContentPane().setBackground(Color.WHITE);
        this.setLayout(new GridLayout(0,1));
        this.setIconImage(new ImageIcon("WEATHER.png").getImage());

        l = new JLabel("Вхід");
        l.setFont(new Font("Open Sans", Font.BOLD, 48));
        l.setForeground(Color.BLACK);
        l.setHorizontalAlignment(JLabel.CENTER);
        l.setVerticalAlignment(JLabel.TOP);
        this.add(l);

        l2 = new JLabel("Введіть id:");
        l2.setFont(new Font("Open Sans", Font.BOLD, 48));
        l2.setHorizontalAlignment(JLabel.LEFT);
        this.add(l2);

        tf = new JTextField();
        tf.setFont(new Font("Open Sans", Font.BOLD, 48));
        this.add(tf);

        l3 = new JLabel("Введіть посаду:");
        l3.setFont(new Font("Open Sans", Font.BOLD, 48));
        this.add(l3);

        tf2 = new JTextField();
        tf2.setFont(new Font("Open Sans", Font.BOLD, 48));
        this.add(tf2);

        b = new JButton("Увійти");
        b.setFont(new Font("Open Sans", Font.BOLD, 48));
        b.setBackground(Color.BLACK);
        b.setForeground(Color.WHITE);
        b.setHorizontalAlignment(JButton.CENTER);
        b.addActionListener(this);
        this.add(b);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==b){
            found = false;
            if(tf2.getText().equalsIgnoreCase("синоптик")){
                try(Session s = Main.sf.openSession()) {
                    List<Synoptic> syns = s.createQuery("from Synoptic", Synoptic.class).list();
                    for (Synoptic syn : syns) {
                        if (syn.getId().equals(tf.getText())) {
                            found = true;
                            SignInUp.success();
                            setCurU(syn);
                            new Main();
                            this.dispose();
                        }
                    }
                    if(!found){
                        SignInUp.failed();
                        System.exit(0);
                    }
                }
            }else if(tf2.getText().equalsIgnoreCase("метеоролог")){
                try(Session s = Main.sf.openSession()) {
                    List<Meteorologist> mets = s.createQuery("from Meteorologist", Meteorologist.class).list();
                    for (Meteorologist met : mets) {
                        if (met.getId().equals(tf.getText())) {
                            found = true;
                            SignInUp.success();
                            setCurU(met);
                            new Main();
                            this.dispose();
                        }
                    }
                    if(!found){
                        SignInUp.failed();
                        System.exit(0);
                    }
                }
            }else if(tf2.getText().equalsIgnoreCase("гідролог")) {
                try(Session s = Main.sf.openSession()) {
                    List<Hydrologist> hyds = s.createQuery("from Hydrologist", Hydrologist.class).list();
                    for (Hydrologist hyd : hyds) {
                        if (hyd.getId().equals(tf.getText())) {
                            found = true;
                            SignInUp.success();
                            setCurU(hyd);
                            new Main();
                            this.dispose();
                        }
                    }
                    if(!found){
                        SignInUp.failed();
                        System.exit(0);
                    }
                }
            }else if(tf2.getText().equalsIgnoreCase("тех підтримка") ||
            tf2.getText().equalsIgnoreCase("технічна підтримка")){
                try(Session s = Main.sf.openSession()){
                    List<TechSupport> techs = s.createQuery("from TechSupport", TechSupport.class).list();
                    for (TechSupport tech : techs) {
                        if (tech.getId().equals(tf.getText())) {
                            found = true;
                            SignInUp.success();
                            setCurU(tech);
                            new Main();
                            this.dispose();
                        }
                    }
                    if(!found){
                        SignInUp.failed();
                        System.exit(0);
                    }
                }
            }else if(tf2.getText().equalsIgnoreCase("розробник") ||
            tf2.getText().equalsIgnoreCase("дев")) {
                if ("oldw6".equalsIgnoreCase(tf.getText())) {
                    found = true;
                    SignInUp.success();
                    setCurU();
                    new Main();
                    this.dispose();
                }
                if (!found) {
                    SignInUp.failed();
                    System.exit(0);
                }
            }else SignInUp.failed();
        }
    }

    public void setCurU(Synoptic user){
        CurrentUser.flm = user.getFml();
        CurrentUser.phoneN = user.getPhoneN();
        CurrentUser.role = tf2.getText();
    }
    public void setCurU(Meteorologist user){
        CurrentUser.flm = user.getFml();
        CurrentUser.phoneN = user.getPhoneN();
        CurrentUser.role = tf2.getText();
    }
    public void setCurU(Hydrologist user){
        CurrentUser.flm = user.getFml();
        CurrentUser.phoneN = user.getPhoneN();
        CurrentUser.role = tf2.getText();
    }
    public void setCurU(TechSupport user){
        CurrentUser.flm = user.getFml();
        CurrentUser.phoneN = user.getPhoneN();
        CurrentUser.role = tf2.getText();
    }
    public void setCurU(){
        CurrentUser.flm = "Дінжос Артем Романович";
        CurrentUser.phoneN = "+380681120632";
        CurrentUser.role = "Розробник";
    }
}
