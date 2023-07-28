package View;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class View extends JFrame {
    public JTextField titletxt;
    public JTextArea descriptiontxt;
    public JDateChooser dateChooser;
    public Choice priorityChoice;
    public JTable tasksTable;
    public JRadioButton isFinished;
    public JButton btnAdd;
    public JButton btnCancel;
    public JButton btnUpdate;
    public JButton btnDelete;
    public View(){
        JPanel contentPane = createMainPannel();

        createNewTaskView(contentPane);
        createOptionBtnView(contentPane);
        createTableTaskView(contentPane);
        createBtnDelete(contentPane);
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

    /**
     * form view to create new task
     *
     * @param contentPane provided layout view*/
    private void createNewTaskView(JPanel contentPane) {
        JPanel panelProject = new JPanel();
        panelProject.setBorder(new TitledBorder(null, "Add Task", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPane.add(panelProject);

        GroupLayout layout = new GroupLayout(panelProject);
        panelProject.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel nameLabel = createLabel("Name:");
        titletxt = new JTextField();

        JLabel descriptionLabel = createLabel("Description:");
        descriptiontxt = new JTextArea(5, 30);

        JLabel deadlineLabel = createLabel("Deadline:");
        dateChooser = new JDateChooser();

        JLabel priorityLabel = createLabel("Priority:");
        priorityChoice = new Choice();
        priorityChoice.add("High");
        priorityChoice.add("Mid");
        priorityChoice.add("Low");

        isFinished = new JRadioButton("Finished");

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(nameLabel)
                .addComponent(descriptionLabel)
                .addComponent(deadlineLabel)
                .addComponent(priorityLabel)
        );
        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(titletxt)
                .addComponent(descriptiontxt)
                .addComponent(dateChooser)
                .addComponent(priorityChoice)
                .addComponent(isFinished)
        );
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(nameLabel)
                .addComponent(titletxt)
        );
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(descriptionLabel)
                .addComponent(descriptiontxt)
        );
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(deadlineLabel)
                .addComponent(dateChooser)
        );
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(priorityLabel)
                .addComponent(priorityChoice)
        );
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(isFinished)
        );
        layout.setVerticalGroup(vGroup);
    }

    /**
     * form view to create option buttons
     *
     * @param contentPane provided layout view*/
    private void createOptionBtnView(JPanel contentPane) {
        JPanel panel = new JPanel();
        contentPane.add(panel);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0,5));

        btnAdd = new JButton("Add");
        panel.add(btnAdd);

        panel.add(Box.createHorizontalStrut(3));

        btnUpdate = new JButton("Update");
        btnUpdate.setEnabled(false);
        panel.add(btnUpdate);

        panel.add(Box.createHorizontalStrut(3));

        btnCancel = new JButton("Cancel");
        panel.add(btnCancel);
    }

    /**
     * form view to create task table view
     *
     * @param contentPane provided layout view*/
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
                        "NAME", "DESCRIPTION","DEADLINE", "PRIORITY", "FINISHED"
                }
        ));
        scrollPane.setViewportView(tasksTable);

    }
    /**
     * form view to create delete button
     *
     * @param contentPane provided layout view*/
    private void createBtnDelete(JPanel contentPane){
        JPanel panel = new JPanel();
        contentPane.add(panel);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0,5));

        btnDelete = new JButton("Delete");
        panel.add(btnDelete);
    }

    private static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        return label;
    }
}
