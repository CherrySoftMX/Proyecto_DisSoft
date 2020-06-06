package com.vista;

import com.modelo.Cliente;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HikingCarrot7
 */
public class Menu extends JFrame
{

    public Menu()
    {
        initLookAndFeel();
        initComponents();
    }

    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    private void initLookAndFeel()
    {
        /* Set the Nimbus look and feel
        /*If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try
        {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
                if ("Windows".equals(info.getName()))
                {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    //</editor-fold>

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        GridBagConstraints gridBagConstraints;

        jPanel1 = new JPanel();
        filler3 = new Box.Filler(new Dimension(0, 5), new Dimension(0, 5), new Dimension(32767, 5));
        jLabel1 = new JLabel();
        filler1 = new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(32767, 10));
        jLabel2 = new JLabel();
        filler2 = new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(32767, 10));
        jSplitPane2 = new JSplitPane();
        jSplitPane1 = new JSplitPane();
        jPanel5 = new JPanel();
        jScrollPane2 = new JScrollPane();
        listClientes = new JList<>();
        jPanel8 = new JPanel();
        lblClienteSeleccionado = new JLabel();
        panelCarrito = new JPanel();
        jPanel7 = new JPanel();
        btnSolicitarCarrito = new JButton();
        btnPagarCarrito = new JButton();
        jPanel9 = new JPanel();
        lblCarrito = new JLabel();
        panelTiendas = new JPanel();
        jScrollPane1 = new JScrollPane();
        tablaTiendas = new JTable();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Centro comercial");
        setMinimumSize(new Dimension(630, 430));
        setPreferredSize(new Dimension(630, 430));

        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
        jPanel1.add(filler3);

        jLabel1.setFont(new Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Centro comercial");
        jLabel1.setAlignmentX(0.5F);
        jPanel1.add(jLabel1);
        jPanel1.add(filler1);

        jLabel2.setIcon(new ImageIcon(getClass().getResource("/com/img/logo_fmat.png"))); // NOI18N
        jLabel2.setAlignmentX(0.5F);
        jPanel1.add(jLabel2);
        jPanel1.add(filler2);

        getContentPane().add(jPanel1, BorderLayout.PAGE_START);

        jSplitPane2.setDividerLocation(160);
        jSplitPane2.setDividerSize(7);
        jSplitPane2.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jSplitPane2.setContinuousLayout(true);
        jSplitPane2.setOneTouchExpandable(true);

        jSplitPane1.setDividerLocation(300);
        jSplitPane1.setDividerSize(7);
        jSplitPane1.setContinuousLayout(true);
        jSplitPane1.setOneTouchExpandable(true);

        jPanel5.setBorder(BorderFactory.createTitledBorder("Clientes disponibles"));
        jPanel5.setLayout(new BorderLayout());

        listClientes.setBackground(new Color(240, 240, 240));
        jScrollPane2.setViewportView(listClientes);

        jPanel5.add(jScrollPane2, BorderLayout.CENTER);

        jPanel8.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel8.setLayout(new BorderLayout());

        lblClienteSeleccionado.setText("Seleccione a un cliente...");
        jPanel8.add(lblClienteSeleccionado, BorderLayout.PAGE_START);

        jPanel5.add(jPanel8, BorderLayout.PAGE_END);

        jSplitPane1.setLeftComponent(jPanel5);

        panelCarrito.setBorder(BorderFactory.createTitledBorder("Carrito"));
        panelCarrito.setLayout(new GridLayout(1, 2));

        jPanel7.setLayout(new GridBagLayout());

        btnSolicitarCarrito.setText("Solicitar carrito");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 15, 5, 15);
        jPanel7.add(btnSolicitarCarrito, gridBagConstraints);

        btnPagarCarrito.setText("Pagar carrito");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 15, 5, 15);
        jPanel7.add(btnPagarCarrito, gridBagConstraints);

        panelCarrito.add(jPanel7);

        jPanel9.setLayout(new BorderLayout());

        lblCarrito.setHorizontalAlignment(SwingConstants.CENTER);
        lblCarrito.setIcon(new ImageIcon(getClass().getResource("/com/img/Carrito_no_existente.png"))); // NOI18N
        lblCarrito.setOpaque(true);
        jPanel9.add(lblCarrito, BorderLayout.CENTER);

        panelCarrito.add(jPanel9);

        jSplitPane1.setRightComponent(panelCarrito);

        jSplitPane2.setLeftComponent(jSplitPane1);

        panelTiendas.setBorder(BorderFactory.createTitledBorder("Tiendas disponibles"));
        panelTiendas.setLayout(new BorderLayout());

        tablaTiendas.setModel(new DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Tienda", "Ir a tienda"
            }
        ));
        jScrollPane1.setViewportView(tablaTiendas);
        if (tablaTiendas.getColumnModel().getColumnCount() > 0)
        {
            tablaTiendas.getColumnModel().getColumn(1).setMinWidth(70);
            tablaTiendas.getColumnModel().getColumn(1).setPreferredWidth(70);
            tablaTiendas.getColumnModel().getColumn(1).setMaxWidth(70);
        }

        panelTiendas.add(jScrollPane1, BorderLayout.CENTER);

        jSplitPane2.setRightComponent(panelTiendas);

        getContentPane().add(jSplitPane2, BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JButton getBtnPagarCarrito()
    {
        return btnPagarCarrito;
    }

    public JButton getBtnSolicitarCarrito()
    {
        return btnSolicitarCarrito;
    }

    public JLabel getLblCarrito()
    {
        return lblCarrito;
    }

    public JLabel getLblClienteSeleccionado()
    {
        return lblClienteSeleccionado;
    }

    public JList<Cliente> getListClientes()
    {
        return listClientes;
    }

    public JPanel getPanelCarrito()
    {
        return panelCarrito;
    }

    public JPanel getPanelTiendas()
    {
        return panelTiendas;
    }

    public JTable getTablaTiendas()
    {
        return tablaTiendas;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton btnPagarCarrito;
    private JButton btnSolicitarCarrito;
    private Box.Filler filler1;
    private Box.Filler filler2;
    private Box.Filler filler3;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPanel jPanel1;
    private JPanel jPanel5;
    private JPanel jPanel7;
    private JPanel jPanel8;
    private JPanel jPanel9;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JSplitPane jSplitPane1;
    private JSplitPane jSplitPane2;
    private JLabel lblCarrito;
    private JLabel lblClienteSeleccionado;
    private JList<Cliente> listClientes;
    private JPanel panelCarrito;
    private JPanel panelTiendas;
    private JTable tablaTiendas;
    // End of variables declaration//GEN-END:variables
}
