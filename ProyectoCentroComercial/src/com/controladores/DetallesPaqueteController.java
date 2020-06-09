package com.controladores;

import com.controladores.util.DialogUtils;
import com.modelo.decorator.Articulo;
import com.modelo.decorator.PaqueteArticulo;
import com.vista.UIConstants;
import com.vista.VistaDetallesPaquete;
import java.awt.event.ActionEvent;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author HikingCarrot7
 */
public class DetallesPaqueteController implements UIConstants
{

    private final VistaDetallesPaquete vistaDetallesPaquete;
    private final PaqueteArticulo paqueteArticulo;

    public DetallesPaqueteController(VistaDetallesPaquete vistaDetallesPaquete, PaqueteArticulo paqueteArticulo)
    {
        this.vistaDetallesPaquete = vistaDetallesPaquete;
        this.paqueteArticulo = paqueteArticulo;
        initComponents();
        initArbolPaquete();
    }

    //<editor-fold defaultstate="collapsed" desc=" initComponents ">
    private void initComponents()
    {
        vistaDetallesPaquete.getBtnAceptar().addActionListener(this::accionBtnAceptar);

        rellanarCamposDetalles(paqueteArticulo);
    }

    private void initArbolPaquete()
    {
        JTree tree = vistaDetallesPaquete.getTreePaquete();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(paqueteArticulo.getDescripcion());
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        cargarDatosArbol(paqueteArticulo, root);
        tree.setModel(treeModel);
        tree.setCellRenderer(TREE_DETALLES_PAQUETE_RENDERER);
        tree.setRowHeight(25);
    }
    //</editor-fold>

    private void cargarDatosArbol(PaqueteArticulo paqueteArticuloRaiz, DefaultMutableTreeNode raiz)
    {
        for (Articulo articulo : paqueteArticuloRaiz.getArticulos())
            if (articulo instanceof PaqueteArticulo)
            {
                DefaultMutableTreeNode hijo = new DefaultMutableTreeNode(articulo.getDescripcion());
                raiz.add(hijo);
                cargarDatosArbol((PaqueteArticulo) articulo, hijo);

            } else
                raiz.add(new DefaultMutableTreeNode(articulo.getDescripcion()));
    }

    private void accionBtnAceptar(ActionEvent e)
    {
        DialogUtils.quitarDialog(vistaDetallesPaquete);
    }

    private void rellanarCamposDetalles(PaqueteArticulo paqueteArticulo)
    {
        vistaDetallesPaquete.getLblPaquete().setText(paqueteArticulo.getDescripcion());
        vistaDetallesPaquete.getTxtDescripcion().setText(paqueteArticulo.getDescripcion());
        vistaDetallesPaquete.getTxtCategoria().setText(paqueteArticulo.getCategoria());
        vistaDetallesPaquete.getTxtIdentificador().setText(paqueteArticulo.getIdentificador());
        vistaDetallesPaquete.getTxtPrecio().setText(String.format("$%,.2f", paqueteArticulo.getPrecio()));
        vistaDetallesPaquete.getTxtDescuento().setText(String.format("%,.2f", paqueteArticulo.getDescuento() * 100) + "%");
    }

}
