package com.oldd6.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignInUp extends JFrame implements ActionListener {
    JLabel l; JButton b, b2, b3;

    SignInUp(){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(1000,600);
        this.getContentPane().setBackground(Color.BLACK);
        this.setLayout(new GridLayout(0,1));
        this.setFont(new Font("Roboto", Font.BOLD, 72));
        this.setIconImage(new ImageIcon("WEATHER.png").getImage());

        l = new JLabel("Авторизація");
        l.setFont(new Font("Comic Sans MS", Font.BOLD, 72));
        l.setForeground(Color.GRAY);
        l.setHorizontalAlignment(JLabel.CENTER);
        l.setVerticalAlignment(JLabel.TOP);
        this.add(l);

        b = new JButton("Увійти");
        b.setBackground(Color.GRAY);
        b.setForeground(Color.BLACK);
        b.setFont(new Font("Roboto", Font.BOLD, 72));
        b.addActionListener(this);
        this.add(b);

        b2 = new JButton("Зареєструватись");
        b2.setBackground(Color.GRAY);
        b2.setForeground(Color.BLACK);
        b2.setFont(new Font("Roboto", Font.BOLD, 72));
        b2.addActionListener(this);
        this.add(b2);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==b){
            new SignIn();
            this.dispose();
        }else if(e.getSource()==b2){
            new SignUp();
            this.dispose();
        }
    }

    public static void success(){
        JLabel logged = new JLabel("Ви увійшли в систему!");
        logged.setFont(new Font("Open Sans", Font.BOLD, 48));
        JOptionPane.showMessageDialog(null, logged, "Успішно",
                JOptionPane.INFORMATION_MESSAGE, new ImageIcon("WEATHER.png"));
    }
    public static void failed(){
        JLabel logged = new JLabel("Ви не в системі! Будь-ласка, зареєструйтесь!");
        logged.setFont(new Font("Open Sans", Font.BOLD, 48));
        JOptionPane.showMessageDialog(null, logged, "Відмовлено в доступі",
                JOptionPane.INFORMATION_MESSAGE, new ImageIcon("WEATHER.png"));
    }
}
