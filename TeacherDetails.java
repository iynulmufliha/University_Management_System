package university.management.system.pkg2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class TeacherDetails extends JFrame implements ActionListener {

    Choice cempId;
    JTable table;
    JButton search, print, add, update, cancel;

    TeacherDetails() {
        // Set background color and layout
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Search by Employee Id");
        heading.setBounds(20, 20, 150, 20);
        add(heading);

        cempId = new Choice();
        cempId.setBounds(180, 20, 150, 20);
        cempId.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                String selectedempId = cempId.getSelectedItem();
                if (selectedempId != null) {
                    showTeacherData(selectedempId);  // Correct method call
                }
            }
        });
        add(cempId);

        // Populate Choice with employee IDs
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT empId FROM teacher");  // Fetching empId from teacher table
            while (rs.next()) {
                cempId.add(rs.getString("empId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Table setup
        table = new JTable();
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 150, 900, 500);
        add(jsp);

        // Initial display of all teacher data
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM teacher");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Buttons
        search = new JButton("Search");
        search.setBounds(20, 70, 100, 30);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBounds(140, 70, 100, 30);
        print.addActionListener(this);
        add(print);

        add = new JButton("Add");
        add.setBounds(260, 70, 100, 30);
        add.addActionListener(this);
        add(add);

        update = new JButton("Update");
        update.setBounds(380, 70, 100, 30);
        update.addActionListener(this);
        add(update);

        cancel = new JButton("Cancel");
        cancel.setBounds(500, 70, 100, 30);
        cancel.addActionListener(this);
        add(cancel);

        // Set frame properties
        setSize(900, 700);
        setLocation(300, 100);
        setTitle("Teacher Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Method to show teacher data based on empId
    public void showTeacherData(String empId) {
        String query = "SELECT * FROM teacher WHERE empId = '" + empId + "'";  // Corrected query to filter by empId
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));  // Display filtered data
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ActionListener for the buttons
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            String selectedempId = cempId.getSelectedItem();
            if (selectedempId != null) {
                showTeacherData(selectedempId);  // Search and display data based on empId
            }
        } else if (ae.getSource() == print) {
            try {
                table.print();  // Print the table
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == add) {
            setVisible(false);
            new AddTeacher(); // Open the AddTeacher page
        } else if (ae.getSource() == update) {
            setVisible(false);
            // Implement the update functionality
            // new UpdateTeacher(); // Uncomment and implement UpdateTeacher class
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new TeacherDetails();
    }
}
