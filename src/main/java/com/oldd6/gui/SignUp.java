package com.oldd6.gui;

import com.oldd6.users.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp extends JFrame implements ActionListener {
    JLabel l, l2, l3, l4, l5, l6, l7;
    JTextField tf, tf2, tf3, tf4, tf5, tf6;
    JButton b;

    SignUp(){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500, 840);
        this.getContentPane().setBackground(Color.WHITE);
        this.setLayout(new GridLayout(0,1));
        this.setIconImage(new ImageIcon("WEATHER.png").getImage());

        l = new JLabel("Реєстрація");
        l.setFont(new Font("Nunito", Font.BOLD, 36));
        l.setForeground(Color.DARK_GRAY);
        l.setHorizontalAlignment(JLabel.CENTER);
        l.setVerticalAlignment(JLabel.TOP);
        this.add(l);

        l2 = new JLabel("Введіть id:");
        l2.setFont(new Font("Nunito", Font.BOLD, 36));
        l2.setHorizontalAlignment(JLabel.LEFT);
        this.add(l2);

        tf = new JTextField("");
        tf.setFont(new Font("Nunito", Font.BOLD, 36));
        this.add(tf);

        l3 = new JLabel("Введіть ПІБ:");
        l3.setFont(new Font("Nunito", Font.BOLD, 36));
        this.add(l3);

        tf2 = new JTextField("");
        tf2.setFont(new Font("Nunito", Font.BOLD, 36));
        this.add(tf2);

        l4 = new JLabel("Введіть вік:");
        l4.setFont(new Font("Nunito", Font.BOLD, 36));
        this.add(l4);

        tf3 = new JTextField("");
        tf3.setFont(new Font("Nunito", Font.BOLD, 36));
        this.add(tf3);

        l5 = new JLabel("Введіть пошту:");
        l5.setFont(new Font("Nunito", Font.BOLD, 36));
        this.add(l5);

        tf4 = new JTextField("");
        tf4.setFont(new Font("Nunito", Font.BOLD, 36));
        this.add(tf4);

        l6 = new JLabel("Введіть номер телефону:");
        l6.setFont(new Font("Nunito", Font.BOLD, 36));
        this.add(l6);

        tf5 = new JTextField("");
        tf5.setFont(new Font("Nunito", Font.BOLD, 36));
        this.add(tf5);

        l7 = new JLabel("Введіть посаду:");
        l7.setFont(new Font("Nunito", Font.BOLD, 36));
        this.add(l7);

        tf6 = new JTextField("");
        tf6.setFont(new Font("Nunito", Font.BOLD, 36));
        this.add(tf6);

        b = new JButton("Зареєструватися");
        b.setFont(new Font("Nunito", Font.BOLD, 36));
        b.setBackground(Color.DARK_GRAY);
        b.setForeground(Color.WHITE);
        b.setHorizontalAlignment(JButton.CENTER);
        b.addActionListener(this);
        this.add(b);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==b){
            if(tf.getText().length() != 5) System.exit(0);
            if(tf6.getText().equalsIgnoreCase("синоптик")){
                Synoptic syn = new Synoptic(tf.getText(), tf2.getText(),
                Integer.parseInt(tf3.getText()), tf4.getText(), tf5.getText());
                this.setCurU();
                register(syn);
                SignInUp.success();
                new Main();
                this.dispose();
            }else if(tf6.getText().equalsIgnoreCase("метеоролог")){
                Meteorologist met = new Meteorologist(tf.getText(), tf2.getText(),
                Integer.parseInt(tf3.getText()), tf4.getText(), tf5.getText());
                this.setCurU();
                register(met);
                SignInUp.success();
                new Main();
                this.dispose();
            }else if(tf6.getText().equalsIgnoreCase("гідролог")) {
                Hydrologist hyd = new Hydrologist(tf.getText(), tf2.getText(),
                Integer.parseInt(tf3.getText()), tf4.getText(), tf5.getText());
                this.setCurU();
                register(hyd);
                SignInUp.success();
                new Main();
                this.dispose();
            }else if(tf6.getText().equalsIgnoreCase("тех підтримка") ||
            tf6.getText().equalsIgnoreCase("технічна підтримка")){
                TechSupport tech = new TechSupport(tf.getText(), tf2.getText(),
                Integer.parseInt(tf3.getText()), tf4.getText(), tf5.getText());
                this.setCurU();
                register(tech);
                SignInUp.success();
                new Main();
                this.dispose();
            }else {
                SignInUp.failed();
                System.exit(0);
            }
        }
    }

    public void register(Synoptic syn){
        Session s = Main.sf.openSession();
        Transaction tr = s.beginTransaction();
        s.merge(syn);
        tr.commit();
        s.close();
    }
    public void register(Meteorologist met){
        Session s = Main.sf.openSession();
        Transaction tr = s.beginTransaction();
        s.merge(met);
        tr.commit();
        s.close();
    }
    public void register(Hydrologist hyd){
        Session s = Main.sf.openSession();
        Transaction tr = s.beginTransaction();
        s.merge(hyd);
        tr.commit();
        s.close();
    }
    public void register(TechSupport tech){
        Session s = Main.sf.openSession();
        Transaction tr = s.beginTransaction();
        s.merge(tech);
        tr.commit();
        s.close();
    }
    public void setCurU(){
        CurrentUser.flm = tf2.getText();
        CurrentUser.phoneN = tf5.getText();
        CurrentUser.role = tf6.getText();
    }
}
