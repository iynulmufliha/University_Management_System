package university.management.system.pkg2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;

public class TeacherLeave extends JFrame implements ActionListener {

    Choice cempId, ctime;
    JDateChooser dcdate;
    JButton submit, cancel;

    TeacherLeave() {
        setSize(500, 550);
        setLocation(550, 100);
        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Apply Leave (Teacher)");
        heading.setBounds(40, 50, 300, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);

        JLabel lblempId = new JLabel("Search by Employee Id");
        lblempId.setBounds(60, 100, 200, 20);
        lblempId.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblempId);

        cempId = new Choice();
        cempId.setBounds(60, 130, 200, 20);
        cempId.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                String selectedempId = cempId.getSelectedItem();
                if (selectedempId != null) {
                    showTeacherData(selectedempId);
                }
            }
        });
        add(cempId);

        // Fetch empId numbers from the database
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT empId FROM teacher");
            while (rs.next()) {
                cempId.add(rs.getString("empId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Date label and date chooser
        JLabel lbldate = new JLabel("Select Leave Date");
        lbldate.setBounds(60, 170, 200, 20);
        lbldate.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lbldate);

        dcdate = new JDateChooser();
        dcdate.setBounds(60, 210, 200, 25);
        add(dcdate);

        // Time duration label and choice
        JLabel lbltime = new JLabel("Time Duration");
        lbltime.setBounds(60, 260, 200, 20);
        lbltime.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lbltime);

        ctime = new Choice();
        ctime.setBounds(60, 290, 200, 20);
        ctime.add("Full Day");
        ctime.add("Half Day");
        add(ctime);

        // Submit and Cancel buttons
        submit = new JButton("Submit");
        submit.setBounds(60, 350, 100, 25);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(200, 350, 100, 25);
        cancel.addActionListener(this);
        add(cancel);

        setVisible(true);
    }

    // Placeholder method to display teacher data (you can define the actual behavior)
    private void showTeacherData(String empId) {
        // Your code to display teacher details based on empId
        System.out.println("Teacher details for Employee Id: " + empId);
    }

    // ActionPerformed method to handle button clicks
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String empId = cempId.getSelectedItem();
            String date = ((JTextField) dcdate.getDateEditor().getUiComponent()).getText();
            String duration = ctime.getSelectedItem();

            // Validate date and check if all fields are filled
            if (date.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please select a date.");
                return;
            }
            
            if (empId.isEmpty() || duration.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all the fields.");
                return;
            }

            String query = "INSERT INTO teacherleave (empId, date, duration) VALUES ('" + empId + "', '" + date + "', '" + duration + "')";
            
            try {
                Conn c = new Conn();
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Leave Confirmed");
                setVisible(false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new TeacherLeave();
    }
}
