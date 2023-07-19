package View;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class View extends JFrame {
    public JTextField txtName;
    public JTextArea descriptiontxt;
    public JDateChooser dateChooser;
    public Choice priorityChoice;
    public JTable tasksTable;
    public View(){

        JPanel contentPane = createMainPannel();

        createNewTaskView(contentPane);

        createAddOrCancelView(contentPane);

        createTableTaskView(contentPane);
    }
    private JPanel createMainPannel() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,450,521);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        return contentPane;
    }

    private void createNewTaskView(JPanel contentPane) {
        JPanel panelProject = new JPanel();
        panelProject.setBorder(new TitledBorder(null, "Add Task", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPane.add(panelProject);
        panelProject.setLayout(new GridLayout(8, 8));

        createLabel("Name:", panelProject);

        txtName = new JTextField();
        txtName.setHorizontalAlignment(SwingConstants.LEFT);
        panelProject.add(txtName);
        txtName.setColumns(10);

        spaceBetweenElements(panelProject);

        createLabel("Description:", panelProject);

        descriptiontxt = new JTextArea();
        panelProject.add(descriptiontxt);

        spaceBetweenElements(panelProject);

        createLabel("Deadline:", panelProject);

        dateChooser = new JDateChooser();
        panelProject.add(dateChooser);

        spaceBetweenElements(panelProject);

        createLabel("Priority:", panelProject);

        priorityChoice = new Choice();
        panelProject.add(priorityChoice);
    }

    private void createAddOrCancelView(JPanel contentPane) {
        JPanel panel = new JPanel();
        contentPane.add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        Component horizontalGlue = Box.createHorizontalGlue();
        panel.add(horizontalGlue);

        JButton btnAdd = new JButton("Add");
        panel.add(btnAdd);

        JButton btnCancel = new JButton("Cancel");
        panel.add(btnCancel);
    }

    private void createTableTaskView(JPanel contentPane) {
        JPanel panelData = new JPanel();
        contentPane.add(panelData);
        panelData.setBorder(new TitledBorder(null, "Tasks", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelData.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        panelData.add(scrollPane);

        tasksTable = new JTable();
        tasksTable.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "NAME", "DESCRIPTION","DEADLINE", "Priority"
                }
        ));
        scrollPane.setViewportView(tasksTable);
    }

    /*This method create vertical margin between gui elements
     *  -receives the panel on which the margin is to be created */
    private static void spaceBetweenElements(JPanel panelProject) {
        Component verticalStrut = Box.createVerticalStrut(5);
        panelProject.add(verticalStrut);
        verticalStrut = Box.createVerticalStrut(5);
        panelProject.add(verticalStrut);
    }

    private static void createLabel(String text, JPanel panelProject) {
        JLabel namelbl = new JLabel(text);
        panelProject.add(namelbl);
    }
}
