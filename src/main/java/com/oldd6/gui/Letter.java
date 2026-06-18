package com.oldd6.gui;

import com.oldd6.users.CurrentUser;
import jakarta.activation.FileDataSource;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.email.EmailPopulatingBuilder;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Letter extends JFrame implements ActionListener {
    Email email;
    Mailer m;
    File f;
    JLabel l, l2, l3, l4;
    JTextField tf, tf2;
    JButton b, b2;
    JTextArea msg;
    JPanel p;


    Letter(){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(1400, 1000);
        this.getContentPane().setBackground(Color.WHITE);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setIconImage(new ImageIcon("WEATHER.png").getImage());

        l = new JLabel("Написати листа");
        l.setAlignmentX(Component.CENTER_ALIGNMENT);
        l.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.GRAY, Color.BLACK));
        l.setFont(new Font("Georgia", Font.BOLD, 64));
        l.setForeground(Color.BLACK);
        this.add(l);

        l2 = new JLabel("Введіть адресу пошти отримувача:");
        l2.setAlignmentX(Component.CENTER_ALIGNMENT);
        l2.setFont(new Font("Georgia", Font.BOLD, 48));
        this.add(l2);

        tf = new JTextField();
        tf.setFont(new Font("Georgia", Font.BOLD, 48));
        tf.setMargin(new Insets(15,15,15,15));
        this.add(tf);

        l3 = new JLabel("Введіть тему повідомлення:");
        l3.setAlignmentX(Component.CENTER_ALIGNMENT);
        l3.setFont(new Font("Georgia", Font.BOLD, 48));
        this.add(l3);

        tf2 = new JTextField();
        tf2.setFont(new Font("Georgia", Font.BOLD, 48));
        tf2.setMargin(new Insets(15,15,15,15));
        this.add(tf2);

        l4 = new JLabel("Введіть повідомлення:");
        l4.setAlignmentX(Component.CENTER_ALIGNMENT);
        l4.setFont(new Font("Georgia", Font.BOLD, 48));
        this.add(l4);

        msg = new JTextArea(25,50);
        msg.setLineWrap(true);
        msg.setFont(new Font("Georgia", Font.BOLD, 48));
        this.add(new JScrollPane(msg));

        p = new JPanel(new FlowLayout(FlowLayout.CENTER));

        b = new JButton("Додати файл");
        b.setFont(new Font("Georgia", Font.BOLD, 48));
        b.setBackground(Color.BLACK);
        b.setForeground(Color.WHITE);
        b.addActionListener(this);
        p.add(b);

        b2 = new JButton("Відправити листа");
        b2.setFont(new Font("Georgia", Font.BOLD, 48));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        p.add(b2);

        this.add(p);
        
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==b){
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                f = chooser.getSelectedFile();
            }msg.setText("Файл: "+f.getName()+" додано");
        }else{
            EmailPopulatingBuilder eb = EmailBuilder.startingBlank()
                    .from(CurrentUser.flm, "email@gmail.com")
                    .to("Отримувач", tf.getText())
                    .withSubject(tf2.getText())
                    .withPlainText(msg.getText()+
                        "\nНомер телефону користувача: "+CurrentUser.phoneN+
                        "\n"+CurrentUser.role);
            if(f!=null) eb.withAttachment(f.getName(), new FileDataSource(f));
            email = eb.buildEmail();
            m = MailerBuilder
                    .withSMTPServer("smtp.gmail.com", 587, "email@gmail.com", "account_password")
                    .buildMailer();
            m.sendMail(email);
        }
    }
}