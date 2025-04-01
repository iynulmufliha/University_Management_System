package university.management.system.pkg2;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.sql.*;

public class AddTeacher extends JFrame implements ActionListener {

    JTextField tfname, tffname, tfaddress, tfphone, tfemail, tfclassX, tfclassXII, tfadhaar;
    JLabel labelempId;
    JDateChooser dcdob;
    JComboBox<String> cbqualification, cbdepartment;
    JButton submit, cancel;

    Random ran = new Random();
    long first4 = Math.abs((ran.nextLong() % 9000L) + 1000L);

    AddTeacher() {
        setSize(900, 850);
        setLocation(350, 50);
        setLayout(null);

        JLabel heading = new JLabel("New Teacher Details");
        heading.setBounds(310, 30, 500, 50);
        heading.setFont(new Font("Serif", Font.BOLD, 30));
        add(heading);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(50, 100, 100, 30);
        lblname.setFont(new Font("Serif", Font.BOLD, 20));
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(200, 100, 150, 30);
        add(tfname);

        JLabel lblfname = new JLabel("Father's Name");
        lblfname.setBounds(400, 100, 200, 30);
        lblfname.setFont(new Font("Serif", Font.BOLD, 20));
        add(lblfname);

        tffname = new JTextField();
        tffname.setBounds(600, 100, 150, 30);
        add(tffname);

        JLabel lblempId = new JLabel("Employee ID");
        lblempId.setBounds(50, 150, 200, 30);
        lblempId.setFont(new Font("Serif", Font.BOLD, 20));
        add(lblempId);

        labelempId = new JLabel("101" + first4);
        labelempId.setBounds(200, 150, 200, 30);
        labelempId.setFont(new Font("Serif", Font.BOLD, 20));
        add(labelempId);

        JLabel lbldob = new JLabel("Date of Birth");
        lbldob.setBounds(400, 150, 200, 30);
        lbldob.setFont(new Font("Serif", Font.BOLD, 20));
        add(lbldob);

        dcdob = new JDateChooser();
        dcdob.setBounds(600, 150, 150, 30);
        add(dcdob);

        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(50, 200, 200, 30);
        lbladdress.setFont(new Font("Serif", Font.BOLD, 20));
        add(lbladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(200, 200, 150, 30);
        add(tfaddress);

        JLabel lblphone = new JLabel("Phone Number");
        lblphone.setBounds(400, 200, 200, 30);
        lblphone.setFont(new Font("Serif", Font.BOLD, 20));
        add(lblphone);

        tfphone = new JTextField();
        tfphone.setBounds(600, 200, 150, 30);
        add(tfphone);

        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(50, 250, 200, 30);
        lblemail.setFont(new Font("Serif", Font.BOLD, 20));
        add(lblemail);

        tfemail = new JTextField();
        tfemail.setBounds(200, 250, 150, 30);
        add(tfemail);

        JLabel lblclassX = new JLabel("Class X (%)");
        lblclassX.setBounds(400, 250, 200, 30);
        lblclassX.setFont(new Font("Serif", Font.BOLD, 20));
        add(lblclassX);

        tfclassX = new JTextField();
        tfclassX.setBounds(600, 250, 150, 30);
        add(tfclassX);

        JLabel lblclassXII = new JLabel("Class XII (%)");
        lblclassXII.setBounds(50, 300, 200, 30);
        lblclassXII.setFont(new Font("Serif", Font.BOLD, 20));
        add(lblclassXII);

        tfclassXII = new JTextField();
        tfclassXII.setBounds(200, 300, 150, 30);
        add(tfclassXII);

        JLabel lbladhaar = new JLabel("Aadhaar Number");
        lbladhaar.setBounds(400, 300, 200, 30);
        lbladhaar.setFont(new Font("Serif", Font.BOLD, 20));
        add(lbladhaar);

        tfadhaar = new JTextField();
        tfadhaar.setBounds(600, 300, 150, 30);
        add(tfadhaar);

        JLabel lblqualification = new JLabel("Qualification");
        lblqualification.setBounds(50, 350, 200, 30);
        lblqualification.setFont(new Font("Serif", Font.BOLD, 20));
        add(lblqualification);

        String[] qualifications = { "B.Tech", "BBA", "BCA", "B.Com", "BA" };
        cbqualification = new JComboBox<>(qualifications);
        cbqualification.setBounds(200, 350, 150, 30);
        add(cbqualification);

        JLabel lbldepartment = new JLabel("Department");
        lbldepartment.setBounds(400, 350, 200, 30);
        lbldepartment.setFont(new Font("Serif", Font.BOLD, 20));
        add(lbldepartment);

        String[] departments = { "Computer Science", "Electronics", "Commerce", "Arts" };
        cbdepartment = new JComboBox<>(departments);
        cbdepartment.setBounds(600, 350, 150, 30);
        add(cbdepartment);

        submit = new JButton("Submit");
        submit.setBounds(250, 550, 120, 30);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(450, 550, 120, 30);
        cancel.addActionListener(this);
        add(cancel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String name = tfname.getText();
            String fname = tffname.getText();
            String empId = labelempId.getText();
            String dob = dcdob.getDate() != null ? new SimpleDateFormat("yyyy-MM-dd").format(dcdob.getDate()) : "";
            String address = tfaddress.getText();
            String phone = tfphone.getText();
            String email = tfemail.getText();
            String x = tfclassX.getText();
            String xii = tfclassXII.getText();
            String adhaar = tfadhaar.getText();
            String qualification = (String) cbqualification.getSelectedItem();
            String department = (String) cbdepartment.getSelectedItem();

            try {
                String query = "INSERT INTO teacher (name, fname, empId, dob, address, phone, email, class_x, class_xii, adhaar, education, department) " +
                        "VALUES ('" + name + "','" + fname + "','" + empId + "','" + dob + "','" + address + "','" + phone + "','" + email + "','" +
                        x + "','" + xii + "','" + adhaar + "','" + qualification + "','" + department + "')";

                Conn c = new Conn();
                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Teacher Details Inserted Successfully");
                setVisible(false);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddTeacher();
    }
}
