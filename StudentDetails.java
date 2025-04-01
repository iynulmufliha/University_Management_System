package university.management.system.pkg2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class StudentDetails extends JFrame implements ActionListener {

    Choice crollno;
    JTable table;
    JButton search, print, add, update, cancel;

    StudentDetails() {
        // Set background color and layout
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Search by Roll Number");
        heading.setBounds(20, 20, 150, 20);
        add(heading);

        crollno = new Choice();
        crollno.setBounds(180, 20, 150, 20);
        crollno.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                String selectedRollno = crollno.getSelectedItem();
                if (selectedRollno != null) {
                    showStudentData(selectedRollno);
                }
            }
        });
        add(crollno);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT rollno FROM student");
            while (rs.next()) {
                crollno.add(rs.getString("rollno"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable();
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 150, 900, 500);
        add(jsp);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM student");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

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

        setSize(900, 700);
        setLocation(300, 100);
        setTitle("Student Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void showStudentData(String rollno) {
        String query = "SELECT * FROM student WHERE rollno = '" + rollno + "'";
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            String selectedRollno = crollno.getSelectedItem();
            if (selectedRollno != null) {
                showStudentData(selectedRollno);
            }
        } else if (ae.getSource() == print) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == add) {
            setVisible(false);
            new AddStudent(); // Open the AddStudent page
        } else if (ae.getSource() == update) {
            setVisible(false);
            //new UpdateStudent();
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new StudentDetails();
    }
}
