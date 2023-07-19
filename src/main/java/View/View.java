package View;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class View extends JFrame {

    public View(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,450,521);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));


        // Create task view
        JPanel panelProject = new JPanel();
        panelProject.setBorder(new TitledBorder(null, "Add Task", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPane.add(panelProject);
        panelProject.setLayout(new GridLayout(8, 8));


        JLabel lblNewLabel = new JLabel("Name:");
        panelProject.add(lblNewLabel);

        JTextField txtName = new JTextField();
        txtName.setHorizontalAlignment(SwingConstants.LEFT);
        panelProject.add(txtName);
        txtName.setColumns(10);

        Component verticalStrut = Box.createVerticalStrut(5);
        panelProject.add(verticalStrut);
        verticalStrut = Box.createVerticalStrut(5);
        panelProject.add(verticalStrut);

        JLabel lblNewLabel_1 = new JLabel("Description:");
        panelProject.add(lblNewLabel_1);

        JTextArea txtDescription = new JTextArea();
        panelProject.add(txtDescription);

        verticalStrut = Box.createVerticalStrut(5);
        panelProject.add(verticalStrut);
        verticalStrut = Box.createVerticalStrut(5);
        panelProject.add(verticalStrut);


        JLabel lblNewLabel_2 = new JLabel("Deadline:");
        panelProject.add(lblNewLabel_2);

        JDateChooser dateChooser = new JDateChooser();
        panelProject.add(dateChooser);

        verticalStrut = Box.createVerticalStrut(5);
        panelProject.add(verticalStrut);
        verticalStrut = Box.createVerticalStrut(5);
        panelProject.add(verticalStrut);

        JLabel lblNewLabel_4 = new JLabel("Priority:");
        panelProject.add(lblNewLabel_4);

        Box horizontalBox = Box.createHorizontalBox();
        panelProject.add(horizontalBox);

        JRadioButton rdbtnHigh = new JRadioButton("High");
        rdbtnHigh.setAlignmentX(Component.CENTER_ALIGNMENT);
        rdbtnHigh.setHorizontalAlignment(SwingConstants.CENTER);
        horizontalBox.add(rdbtnHigh);

        JRadioButton rdbtnMid = new JRadioButton("Mid");
        rdbtnMid.setAlignmentX(Component.CENTER_ALIGNMENT);
        rdbtnMid.setHorizontalAlignment(SwingConstants.CENTER);
        horizontalBox.add(rdbtnMid);

        JRadioButton rdbtnLow = new JRadioButton("Low");
        rdbtnMid.setAlignmentX(Component.CENTER_ALIGNMENT);
        rdbtnMid.setHorizontalAlignment(SwingConstants.CENTER);
        horizontalBox.add(rdbtnLow);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rdbtnHigh);
        buttonGroup.add(rdbtnMid);
        buttonGroup.add(rdbtnLow);


        // Add options
        JPanel panel = new JPanel();
        contentPane.add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        Component horizontalGlue = Box.createHorizontalGlue();
        panel.add(horizontalGlue);

        JButton btnAdd = new JButton("Add");
        panel.add(btnAdd);

        JButton btnCancel = new JButton("Cancel");
        panel.add(btnCancel);
        // View all tasks
        JPanel panelData = new JPanel();
        contentPane.add(panelData);
        panelData.setBorder(new TitledBorder(null, "Tasks", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelData.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        panelData.add(scrollPane);

        JTable projectsTable = new JTable();
        projectsTable.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "NAME", "DESCRIPTION", "PROJECT MANAGER", "DEADLINE", "FINISHED"
                }
        ));
        scrollPane.setViewportView(projectsTable);
    }
}
