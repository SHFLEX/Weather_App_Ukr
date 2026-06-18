package com.oldd6.gui;

import com.oldd6.users.Hydrologist;
import com.oldd6.users.Meteorologist;
import com.oldd6.users.Synoptic;
import com.oldd6.users.TechSupport;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteUser extends JFrame implements ActionListener {
    JLabel l, l2, l3;
    JTextField tf, tf2;
    JButton b;

    DeleteUser(){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(1000, 600);
        this.getContentPane().setBackground(Color.WHITE);
        this.setLayout(new GridLayout(0,1));
        this.setIconImage(new ImageIcon("WEATHER.png").getImage());

        l = new JLabel("Видалення облікового запису");
        l.setFont(new Font("Verdana", Font.BOLD, 48));
        l.setForeground(Color.BLACK);
        l.setHorizontalAlignment(JLabel.CENTER);
        l.setVerticalAlignment(JLabel.TOP);
        this.add(l);

        l2 = new JLabel("Введіть id:");
        l2.setFont(new Font("Verdana", Font.BOLD, 48));
        l2.setHorizontalAlignment(JLabel.LEFT);
        this.add(l2);

        tf = new JTextField();
        tf.setFont(new Font("Verdana", Font.BOLD, 48));
        this.add(tf);

        l3 = new JLabel("Введіть посаду:");
        l3.setFont(new Font("Verdana", Font.BOLD, 48));
        this.add(l3);

        tf2 = new JTextField();
        tf2.setFont(new Font("Verdana", Font.BOLD, 48));
        this.add(tf2);

        b = new JButton("Видалити обліковий запис");
        b.setFont(new Font("Verdana", Font.BOLD, 48));
        b.setBackground(Color.BLACK);
        b.setForeground(Color.WHITE);
        b.setHorizontalAlignment(JButton.CENTER);
        b.addActionListener(this);
        this.add(b);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==b){
            if(tf2.getText().equalsIgnoreCase("синоптик")){
                Session s = Main.sf.openSession();
                Transaction tr = s.beginTransaction();
                Synoptic syn = s.get(Synoptic.class, tf.getText());
                if(syn!=null){
                    s.delete(syn);
                    success();
                }else failed();
                tr.commit();
                s.close();
            }else if(tf2.getText().equalsIgnoreCase("гідролог")){
                Session s = Main.sf.openSession();
                Transaction tr = s.beginTransaction();
                Hydrologist hy = s.get(Hydrologist.class, tf.getText());
                if(hy!=null){
                    s.delete(hy);
                    success();
                }else failed();
                tr.commit();
                s.close();
            }else if(tf2.getText().equalsIgnoreCase("метеоролог")){
                Session s = Main.sf.openSession();
                Transaction tr = s.beginTransaction();
                Meteorologist met = s.get(Meteorologist.class, tf.getText());
                if(met!=null){
                    s.delete(met);
                    success();
                }else failed();
                tr.commit();
                s.close();
            }else if(tf2.getText().equalsIgnoreCase("технічна підтримка")||
                tf2.getText().equalsIgnoreCase("тех підтримка")){
                Session s = Main.sf.openSession();
                Transaction tr = s.beginTransaction();
                TechSupport tech = s.get(TechSupport.class, tf.getText());
                if(tech!=null){
                    s.delete(tech);
                    success();
                }else failed();
                tr.commit();
                s.close();
            }
        }
    }

    public void success(){
        JLabel deleted = new JLabel("Запис видалено!");
        deleted.setFont(new Font("Verdana", Font.BOLD, 48));
        JOptionPane.showMessageDialog(null, deleted, "Успішно",
                JOptionPane.INFORMATION_MESSAGE, new ImageIcon("WEATHER.png"));
    }
    public void failed(){
        JLabel deleted = new JLabel("Помилка! Запис не існує!");
        deleted.setFont(new Font("Verdana", Font.BOLD, 48));
        JOptionPane.showMessageDialog(null, deleted, "Помилка",
                JOptionPane.INFORMATION_MESSAGE, new ImageIcon("WEATHER.png"));
    }
}
