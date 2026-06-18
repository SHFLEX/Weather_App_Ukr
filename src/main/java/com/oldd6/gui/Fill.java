package com.oldd6.gui;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




public class Fill extends JFrame implements ActionListener {

    JLabel l, l2, l3, l4, l5, l6, l7, l8;
    JTextField tf, tf2, tf3, tf4, tf5, tf6, tf7;
    JButton sub;


    Fill() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(650, 800);
        this.getContentPane().setBackground(Color.GRAY);
        this.setIconImage(new ImageIcon("WEATHER.png").getImage());
        this.setLayout(new GridLayout(0, 1));

        l = new JLabel("Введіть дані:");
        l.setFont(new Font("Roboto", Font.BOLD, 48));
        l.setForeground(Color.BLACK);
        l.setHorizontalAlignment(JLabel.CENTER);
        l.setVerticalAlignment(JLabel.TOP);
        this.add(l);

        l2 = new JLabel("ДАТА (день.місяць.рік):");
        l2.setFont(new Font("Roboto", Font.BOLD, 36));
        l2.setForeground(Color.BLACK);
        this.add(l2);

        tf = new JTextField();
        tf.setFont(new Font("Roboto", Font.BOLD, 36));
        tf.setForeground(Color.BLACK);
        this.add(tf);

        l3 = new JLabel("ТЕМПЕРАТУРА (°C):");
        l3.setFont(new Font("Roboto", Font.BOLD, 36));
        l3.setForeground(Color.BLACK);
        this.add(l3);

        tf2 = new JTextField();
        tf2.setFont(new Font("Roboto", Font.BOLD, 36));
        tf2.setForeground(Color.BLACK);
        this.add(tf2);

        l4 = new JLabel("СТАН НЕБА:");
        l4.setFont(new Font("Roboto", Font.BOLD, 36));
        l4.setForeground(Color.BLACK);
        this.add(l4);

        tf3 = new JTextField();
        tf3.setFont(new Font("Roboto", Font.BOLD, 36));
        tf3.setForeground(Color.BLACK);
        this.add(tf3);

        l5 = new JLabel("АТМОСФЕРНИЙ ТИСК (мм. рт. ст.):");
        l5.setFont(new Font("Roboto", Font.BOLD, 36));
        l5.setForeground(Color.BLACK);
        this.add(l5);

        tf4 = new JTextField();
        tf4.setFont(new Font("Roboto", Font.BOLD, 36));
        tf4.setForeground(Color.BLACK);
        this.add(tf4);

        l6 = new JLabel("ВОЛОГІСТЬ (%):");
        l6.setFont(new Font("Roboto", Font.BOLD, 36));
        l6.setForeground(Color.BLACK);
        this.add(l6);

        tf5 = new JTextField();
        tf5.setFont(new Font("Roboto", Font.BOLD, 36));
        tf5.setForeground(Color.BLACK);
        this.add(tf5);

        l7 = new JLabel("ШВИДКІСТЬ ВІТРУ (М/С):");
        l7.setFont(new Font("Roboto", Font.BOLD, 36));
        l7.setForeground(Color.BLACK);
        this.add(l7);

        tf6 = new JTextField();
        tf6.setFont(new Font("Roboto", Font.BOLD, 36));
        tf6.setForeground(Color.BLACK);
        this.add(tf6);

        l8 = new JLabel("ОПАДИ:");
        l8.setFont(new Font("Roboto", Font.BOLD, 36));
        l8.setForeground(Color.BLACK);
        this.add(l8);

        tf7 = new JTextField();
        tf7.setFont(new Font("Roboto", Font.BOLD, 36));
        tf7.setForeground(Color.BLACK);
        this.add(tf7);

        sub = new JButton("ПІДТВЕРДИТИ<=");
        sub.setFont(new Font("Roboto", Font.BOLD, 36));
        sub.setForeground(Color.BLACK);
        sub.setBackground(Color.GRAY);
        sub.addActionListener(this);
        this.add(sub);


        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sub) {
            Weather wth = new Weather(tf.getText(), Integer.parseInt(tf2.getText()),
                tf3.getText(), Integer.parseInt(tf4.getText()), Integer.parseInt(tf5.getText()),
                Integer.parseInt(tf6.getText()), tf7.getText());

            Session s = Main.sf.openSession();
            Transaction tr = s.beginTransaction();
            s.merge(wth);
            tr.commit();
            s.close();

            tf.setText("");     tf2.setText("");    tf3.setText("");
            tf4.setText("");     tf5.setText("");    tf6.setText("");
            tf7.setText("");
        }
    }
}
