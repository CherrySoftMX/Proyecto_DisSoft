package com.vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
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

    public MenuPago(Window owner)
    {
        super(owner);
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
        tablaArticulosCarrito = new JTable();
        jPanel2 = new JPanel();
        jLabel2 = new JLabel();
        txtCliente = new JTextField();
        jLabel3 = new JLabel();
        txtTotal = new JTextField();
        jLabel4 = new JLabel();
        btnRealizarPago = new JButton();
        cmbMetodoPago = new JComboBox<>();
        jPanel5 = new JPanel();
        jLabel5 = new JLabel();
        jPanel4 = new JPanel();
        btnCancelar = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Realizar pago");
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
        jSplitPane1.setResizeWeight(0.5);
        jSplitPane1.setContinuousLayout(true);
        jSplitPane1.setOneTouchExpandable(true);

        jPanel1.setBorder(BorderFactory.createTitledBorder("Artículos del carrito"));
        jPanel1.setLayout(new BorderLayout());

        tablaArticulosCarrito.setModel(new DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Artículo", "Precio"
            }
        ));
        jScrollPane1.setViewportView(tablaArticulosCarrito);
        if (tablaArticulosCarrito.getColumnModel().getColumnCount() > 0)
        {
            tablaArticulosCarrito.getColumnModel().getColumn(1).setMinWidth(90);
            tablaArticulosCarrito.getColumnModel().getColumn(1).setPreferredWidth(90);
            tablaArticulosCarrito.getColumnModel().getColumn(1).setMaxWidth(90);
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

        txtCliente.setEnabled(false);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel2.add(txtCliente, gridBagConstraints);

        jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel3.setText("Total:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel2.add(jLabel3, gridBagConstraints);

        txtTotal.setEnabled(false);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel2.add(txtTotal, gridBagConstraints);

        jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel4.setText("Método de pago:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel2.add(jLabel4, gridBagConstraints);

        btnRealizarPago.setText("Realizar pago");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel2.add(btnRealizarPago, gridBagConstraints);

        cmbMetodoPago.setModel(new DefaultComboBoxModel<>(new String[] { "Paypal", "Débito", "Crédito" }));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel2.add(cmbMetodoPago, gridBagConstraints);

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

        btnCancelar.setText("Cancelar");
        jPanel4.add(btnCancelar);

        getContentPane().add(jPanel4, BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JButton getBtnCancelar()
    {
        return btnCancelar;
    }

    public JButton getBtnRealizarPago()
    {
        return btnRealizarPago;
    }

    public JComboBox<String> getCmbMetodoPago()
    {
        return cmbMetodoPago;
    }

    public JTable getTablaArticulosCarrito()
    {
        return tablaArticulosCarrito;
    }

    public JTextField getTxtCliente()
    {
        return txtCliente;
    }

    public JTextField getTxtTotal()
    {
        return txtTotal;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton btnCancelar;
    private JButton btnRealizarPago;
    private JComboBox<String> cmbMetodoPago;
    private Box.Filler filler1;
    private Box.Filler filler2;
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
    private JTable tablaArticulosCarrito;
    private JTextField txtCliente;
    private JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
