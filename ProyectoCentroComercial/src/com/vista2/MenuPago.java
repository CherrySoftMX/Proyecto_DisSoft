package com.vista2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HikingCarrot7
 */
public class MenuPago extends JDialog
{

    /**
     * Creates new form MenuCarrito
     */
    public MenuPago()
    {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        GridBagConstraints gridBagConstraints;

        jPanel3 = new JPanel();
        filler1 = new Box.Filler(new Dimension(0, 5), new Dimension(0, 5), new Dimension(32767, 5));
        jLabel1 = new JLabel();
        filler2 = new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(32767, 10));
        jPanel6 = new JPanel();
        jSplitPane1 = new JSplitPane();
        jPanel1 = new JPanel();
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        jPanel2 = new JPanel();
        jLabel2 = new JLabel();
        jTextField1 = new JTextField();
        jLabel3 = new JLabel();
        jTextField2 = new JTextField();
        jLabel4 = new JLabel();
        jButton2 = new JButton();
        jComboBox1 = new JComboBox<>();
        jPanel5 = new JPanel();
        jLabel5 = new JLabel();
        jPanel4 = new JPanel();
        jButton1 = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(600, 325));
        setPreferredSize(new Dimension(600, 325));

        jPanel3.setLayout(new BoxLayout(jPanel3, BoxLayout.Y_AXIS));
        jPanel3.add(filler1);

        jLabel1.setFont(new Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Realizar pago");
        jLabel1.setAlignmentX(0.5F);
        jPanel3.add(jLabel1);
        jPanel3.add(filler2);

        getContentPane().add(jPanel3, BorderLayout.PAGE_START);

        jPanel6.setLayout(new BorderLayout());

        jSplitPane1.setDividerLocation(310);
        jSplitPane1.setDividerSize(7);
        jSplitPane1.setContinuousLayout(true);
        jSplitPane1.setOneTouchExpandable(true);

        jPanel1.setBorder(BorderFactory.createTitledBorder("Artículos del carrito"));
        jPanel1.setLayout(new BorderLayout());

        jTable1.setModel(new DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Artículo", "Precio"
            }
        ));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0)
        {
            jTable1.getColumnModel().getColumn(1).setMinWidth(90);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(90);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(90);
        }

        jPanel1.add(jScrollPane1, BorderLayout.CENTER);

        jSplitPane1.setLeftComponent(jPanel1);

        jPanel2.setBorder(BorderFactory.createTitledBorder("Detalles del pago"));
        jPanel2.setLayout(new GridBagLayout());

        jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel2.setText("Cliente:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel2.add(jLabel2, gridBagConstraints);

        jTextField1.setEnabled(false);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel2.add(jTextField1, gridBagConstraints);

        jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel3.setText("Total:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel2.add(jLabel3, gridBagConstraints);

        jTextField2.setEnabled(false);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel2.add(jTextField2, gridBagConstraints);

        jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel4.setText("Método de pago:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel2.add(jLabel4, gridBagConstraints);

        jButton2.setText("Realizar pago");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel2.add(jButton2, gridBagConstraints);

        jComboBox1.setModel(new DefaultComboBoxModel<>(new String[] { "Paypal", "Débito", "Crédito" }));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel2.add(jComboBox1, gridBagConstraints);

        jPanel5.setLayout(new BorderLayout());
        jPanel5.add(jLabel5, BorderLayout.CENTER);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel2.add(jPanel5, gridBagConstraints);

        jSplitPane1.setRightComponent(jPanel2);

        jPanel6.add(jSplitPane1, BorderLayout.CENTER);

        getContentPane().add(jPanel6, BorderLayout.CENTER);

        jPanel4.setLayout(new FlowLayout(FlowLayout.RIGHT));

        jButton1.setText("Cancelar");
        jPanel4.add(jButton1);

        getContentPane().add(jPanel4, BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Box.Filler filler1;
    private Box.Filler filler2;
    private JButton jButton1;
    private JButton jButton2;
    private JComboBox<String> jComboBox1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JPanel jPanel6;
    private JScrollPane jScrollPane1;
    private JSplitPane jSplitPane1;
    private JTable jTable1;
    private JTextField jTextField1;
    private JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
