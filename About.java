package university.management.system.pkg2;

import javax.swing.*;
import java.awt.*;

public class About extends JFrame {

    About() {
    setSize(700, 500);  // Set the size of the frame
    setLocation(400, 150);  // Set the frame's position
    getContentPane().setBackground(Color.WHITE);  // Set background color

    // Image for the about section
    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/about.jpg"));
    Image i2 = i1.getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT);
    ImageIcon i3 = new ImageIcon(i2);
    JLabel image = new JLabel(i3);
    image.setBounds(400, 10, 300, 200);  // Position the image
    add(image);

    // Heading for the University Management System
    JLabel heading = new JLabel("<html>University<br/>Management System</html>");
    heading.setBounds(70, 20, 350, 80);  // Adjusted position and size
    heading.setFont(new Font("Tahoma", Font.BOLD, 30));
    add(heading);

    // Names of the developers (list style)
   JLabel name = new JLabel("<html>"
        + "<p><b>Developed By: Pebbles</b></p>"  // Heading for "Developed By"
        + "<ul>"
        + "<li>Meenakshi Dangi Thakur</li>"
        + "<li>M. Iynul Mufliha</li>"
        + "<li>Shaista Aafreen</li>"
        + "</ul>"
        + "</html>");

name.setBounds(70, 120, 550, 150);  // Adjusted position and width for better spacing
name.setFont(new Font("Tahoma", Font.BOLD, 18));  // Slightly smaller font for better fit
add(name);

    // Roll number label
    JLabel rollno = new JLabel("JLU ID: JLU08191 , JLU07770, JLU08170");
    rollno.setBounds(70, 250, 550, 40);  // Adjusted vertical position
    rollno.setFont(new Font("Tahoma", Font.PLAIN, 22));  // Slightly smaller font size
    add(rollno);

    // Contact information
    JLabel contact = new JLabel("Contact: pebbeles61112@gmail.com");
    contact.setBounds(70, 290, 550, 40);  // Adjusted vertical position
    contact.setFont(new Font("Tahoma", Font.PLAIN, 20));
    add(contact);

    setLayout(null);  // Use null layout for custom positioning

    setVisible(true);  // Make the frame visible
}

    public static void main(String[] args) {
        new About();
    }
}