package Views;
import Components.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import Components.Button;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.Date;
public class AddRecord extends View{
    public AddRecord() {
        this.setLayout(new BorderLayout());
        this.add(new Sidenav(), BorderLayout.WEST);

        JLabel title = new JLabel("Expense Record Table ");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Poppins", Font.BOLD, 24));
        JButton back = new JButton("Back");
        this.add(title, BorderLayout.NORTH);
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        // table model with columns
        String[] columnNames = {"Food", "Petrol", "Credit"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Get the text field values from ExpenseMain
        ExpenseMain expenseMain = (ExpenseMain) ViewManager.getInstance().getView("ExpenseMain");
        DefaultTableModel expenseTableModel = expenseMain.getTableModel();
        int rowCount = expenseTableModel.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            Object[] rowData = {
                    i + 1, // Assuming the ID should start from 1 and increment by 1
                    new Date(), // Use the current date for the Date column
                    expenseTableModel.getValueAt(i, 0),
                    expenseTableModel.getValueAt(i, 1),
                    expenseTableModel.getValueAt(i, 2)
            };
            tableModel.addRow(rowData);


        }

// Commit the transaction and close the Hibernate session
        session.getTransaction().commit();
        session.close();

        // Create the table
        JTable table = new JTable(tableModel);

        // Create the scroll pane and add the table to it
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to the center of the view
        this.add(Box.createVerticalStrut(10));
        this.add(back,BorderLayout.WEST);

        this.add(Box.createHorizontalStrut(5));
        this.add(scrollPane, BorderLayout.CENTER);

    }


    }

