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
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
public class MenuCarrito extends JDialog
{

    /**
     * Creates new form MenuCarrito
     */
    public MenuCarrito(Window owner)
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
        jPanel7 = new JPanel();
        lblTotal = new JLabel();
        jPanel2 = new JPanel();
        jLabel2 = new JLabel();
        txtNombre = new JTextField();
        jLabel3 = new JLabel();
        txtDescripcion = new JTextField();
        jLabel4 = new JLabel();
        txtPrecio = new JTextField();
        btnEliminarDelCarrito = new JButton();
        jPanel5 = new JPanel();
        lblCarrito = new JLabel();
        jPanel4 = new JPanel();
        btnSalir = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detalles del carrito");
        setMinimumSize(new Dimension(600, 440));
        setPreferredSize(new Dimension(600, 440));

        jPanel3.setLayout(new BoxLayout(jPanel3, BoxLayout.Y_AXIS));
        jPanel3.add(filler1);

        jLabel1.setFont(new Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Detalles del carrito");
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

        jPanel7.setLayout(new BorderLayout());

        lblTotal.setText("Total:");
        lblTotal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel7.add(lblTotal, BorderLayout.CENTER);

        jPanel1.add(jPanel7, BorderLayout.PAGE_END);

        jSplitPane1.setLeftComponent(jPanel1);

        jPanel2.setBorder(BorderFactory.createTitledBorder("Detalles del artículo"));
        jPanel2.setLayout(new GridBagLayout());

        jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel2.setText("Nombre:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel2.add(jLabel2, gridBagConstraints);

        txtNombre.setEnabled(false);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel2.add(txtNombre, gridBagConstraints);

        jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel3.setText("Descripción:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel2.add(jLabel3, gridBagConstraints);

        txtDescripcion.setEnabled(false);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel2.add(txtDescripcion, gridBagConstraints);

        jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel4.setText("Precio:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel2.add(jLabel4, gridBagConstraints);

        txtPrecio.setEnabled(false);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel2.add(txtPrecio, gridBagConstraints);

        btnEliminarDelCarrito.setText("Eliminar artículo(s) del carrito");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel2.add(btnEliminarDelCarrito, gridBagConstraints);

        jPanel5.setLayout(new BorderLayout());

        lblCarrito.setHorizontalAlignment(SwingConstants.CENTER);
        lblCarrito.setIcon(new ImageIcon(getClass().getResource("/com/img/Carrito_no_existente.png"))); // NOI18N
        jPanel5.add(lblCarrito, BorderLayout.CENTER);

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

        btnSalir.setText("Salir");
        jPanel4.add(btnSalir);

        getContentPane().add(jPanel4, BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JButton getBtnEliminarDelCarrito()
    {
        return btnEliminarDelCarrito;
    }

    public JButton getBtnSalir()
    {
        return btnSalir;
    }

    public JLabel getLblCarrito()
    {
        return lblCarrito;
    }

    public JLabel getLblTotal()
    {
        return lblTotal;
    }

    public JTable getTablaArticulosCarrito()
    {
        return tablaArticulosCarrito;
    }

    public JTextField getTxtDescripcion()
    {
        return txtDescripcion;
    }

    public JTextField getTxtNombre()
    {
        return txtNombre;
    }

    public JTextField getTxtPrecio()
    {
        return txtPrecio;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton btnEliminarDelCarrito;
    private JButton btnSalir;
    private Box.Filler filler1;
    private Box.Filler filler2;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JPanel jPanel6;
    private JPanel jPanel7;
    private JScrollPane jScrollPane1;
    private JSplitPane jSplitPane1;
    private JLabel lblCarrito;
    private JLabel lblTotal;
    private JTable tablaArticulosCarrito;
    private JTextField txtDescripcion;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
