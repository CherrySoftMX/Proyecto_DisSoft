package com.vista;

import com.modelo.Cliente;
import com.modelo.decorator.Articulo;
import com.modelo.decorator.Paquete;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusListener;
import java.util.EventObject;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.TreeCellRenderer;

/**
 *
 * @author Nicolás
 */
public interface UIConstants
{

    public Color LIST_DEFAULT_BACKGROUND = (Color) UIManager.get("List.background");
    public Color COMBOBOX_DEFAULT_BACKGROUND = (Color) UIManager.get("ComboBox.background");
    public Color TABLE_DEFAULT_BACKGROUND = (Color) UIManager.get("Table.background");

    public Color LIST_SELECTION_BACKGROUND = (Color) UIManager.get("List.selectionBackground");
    public Color COMBOBOX_SELECTION_BACKGROUND = (Color) UIManager.get("ComboBox.selectionBackground");
    public Color TABLE_SELECTION_BACKGROUND = (Color) UIManager.get("Table.selectionBackground");

    public Border LIST_HIGHLIGHT_BORDER = (Border) UIManager.get("List.focusCellHighlightBorder");
    public Border TABLE_HIGHLIGHT_BORDER = (Border) UIManager.get("List.focusCellHighlightBorder");

    public Icon CLIENTE_ICON = new ImageIcon("img/cliente_icon.png");
    public Icon DEFAULT_ROW_ICON = new ImageIcon("img/right.gif");

    public Icon AJAX = new ImageIcon("img/ajax.png");
    public Icon AJAX_2 = new ImageIcon("img/ajax_2.png");
    public Icon SONBEAR = new ImageIcon("img/sonbear.jpg");
    public Icon SHADIC = new ImageIcon(("img/shadic.png"));
    public Icon MOHAMMED = new ImageIcon(("img/mohammed.jpg"));

    public Icon CARRITO_ICON = new ImageIcon(("img/carrito_icon.png"));
    public Icon DELETE_ICON = new ImageIcon(("img/delete.png"));
    public Icon PAQUETE_ICON = new ImageIcon(("img/box.png"));
    public Icon ARTICULO_ICON = new ImageIcon(("img/articulo_icon.png"));
    public Icon TIENDA_ICON = new ImageIcon(("img/tienda_icon.png"));

    public Icon CARRITO_USADO = new ImageIcon(("img/CarritoUsado.png"));
    public Icon CARRITO_LLENO = new ImageIcon(("img/CarritoLleno.png"));
    public Icon CARRITO_VACIO = new ImageIcon(("img/CarritoVacio.png"));
    public Icon CARRITO_CANCELADO = new ImageIcon(("img/CarritoCancelado.png"));
    public Icon CARRITO_NO_EXISTENTE = new ImageIcon(("img/Carrito_no_existente.png"));

    public TableCellRenderer MY_TABLE_HEADER_RENDERER = (table, value, isSelected, hasFocus, row, column) ->
    {
        final Color BACKGROUND_COLOR = new Color(70, 160, 190);

        JLabel label = new JLabel(String.valueOf(value));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Tahoma", Font.BOLD, 13));
        label.setSize(30, label.getWidth());
        label.setPreferredSize(new Dimension(6, label.getWidth()));

        label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE));
        label.setOpaque(true);
        label.setBackground(BACKGROUND_COLOR);
        label.setForeground(Color.white);

        return label;
    };

    public TableCellRenderer MY_TABLE_CELL_RENDERER = new DefaultTableCellRenderer()
    {

        @Override public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            final Color BACKGROUND_COLOR = new Color(200, 228, 235);
            final Color SELECTED_BACKGROUND_COLOR = BACKGROUND_COLOR.darker();

            JLabel label = new JLabel(String.valueOf(value));
            label.setHorizontalAlignment(SwingConstants.LEFT);
            label.setFont(new Font("Tahoma", Font.PLAIN, 13));
            label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.WHITE));
            label.setOpaque(true);

            if (!isSelected)
            {
                label.setBackground(row % 2 == 0 ? BACKGROUND_COLOR : Color.WHITE);
                label.setForeground(Color.BLACK);

            } else
            {
                label.setBackground(SELECTED_BACKGROUND_COLOR);
                label.setForeground(Color.WHITE);
            }

            return label;
        }
    };

    public DefaultCellEditor COLUMNA_NO_EDITABLE = new DefaultCellEditor(new JTextField())
    {
        @Override public boolean isCellEditable(EventObject anEvent)
        {
            return false;
        }
    };

    public FocusListener DEFAULT_FOCUS_LOST_BEHAVIOR = new FocusAdapter()
    {

    };

    public TableCellEditor TABLA_NO_EDITABLE = new TableCellEditor()
    {

        @Override public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
        {
            return null;
        }

        @Override public Object getCellEditorValue()
        {
            return null;
        }

        @Override public boolean isCellEditable(EventObject e)
        {
            return false;
        }

        @Override public boolean shouldSelectCell(EventObject anEvent)
        {
            return true;
        }

        @Override public boolean stopCellEditing()
        {
            return false;
        }

        @Override public void cancelCellEditing()
        {
        }

        @Override public void addCellEditorListener(CellEditorListener l)
        {
        }

        @Override public void removeCellEditorListener(CellEditorListener l)
        {
        }
    };

    public TableCellRenderer COLUMNA_TEXTO_CENTRADO = (table, value, isSelected, hasFocus, row, column) ->
    {
        JLabel label = new JLabel(String.valueOf(value));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(isSelected ? TABLE_SELECTION_BACKGROUND : TABLE_DEFAULT_BACKGROUND);

        if (hasFocus)
            label.setBorder(TABLE_HIGHLIGHT_BORDER);

        return label;
    };

    public TableCellRenderer ARTICULO_COLUMNA_RENDERER = (table, value, isSelected, hasFocus, row, column) ->
    {
        final Color BACKGROUND_COLOR = new Color(200, 228, 235);
        final Color SELECTED_BACKGROUND_COLOR = BACKGROUND_COLOR.darker();
        Articulo articulo = (Articulo) value;

        JLabel label = new JLabel(articulo.getDescripcion());
        label.setIcon(articulo instanceof Paquete ? PAQUETE_ICON : ARTICULO_ICON);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setFont(new Font("Tahoma", Font.PLAIN, 13));
        label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.WHITE));
        label.setOpaque(true);

        if (!isSelected)
        {
            label.setBackground(row % 2 == 0 ? BACKGROUND_COLOR : Color.WHITE);
            label.setForeground(Color.BLACK);

        } else
        {
            label.setBackground(SELECTED_BACKGROUND_COLOR);
            label.setForeground(Color.WHITE);
        }

        return label;
    };

    public TableCellRenderer TIENDA_COLUMNA_RENDERER = (table, value, isSelected, hasFocus, row, column) ->
    {
        final Color BACKGROUND_COLOR = new Color(200, 228, 235);
        final Color SELECTED_BACKGROUND_COLOR = BACKGROUND_COLOR.darker();

        JLabel label = new JLabel(String.valueOf(value));
        label.setIcon(TIENDA_ICON);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setFont(new Font("Tahoma", Font.PLAIN, 13));
        label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.WHITE));
        label.setOpaque(true);

        if (!isSelected)
        {
            label.setBackground(row % 2 == 0 ? BACKGROUND_COLOR : Color.WHITE);
            label.setForeground(Color.BLACK);

        } else
        {
            label.setBackground(SELECTED_BACKGROUND_COLOR);
            label.setForeground(Color.WHITE);
        }

        return label;
    };

    public ListCellRenderer<Cliente> LISTA_CLIENTES_RENDERER = (list, value, index, isSelected, cellHasFocus) ->
    {
        JLabel label = new JLabel(value.getNombre());

        String nombre = value.getNombre().toLowerCase();
        Icon icon;

        if (nombre.contains("eusebio"))
            icon = AJAX;
        else if (nombre.contains("emmanuel"))
            icon = SONBEAR;
        else if (nombre.contains("carlos"))
            icon = SHADIC;
        else if (nombre.contains("nicolás"))
            icon = MOHAMMED;
        else
            icon = CLIENTE_ICON;

        label.setIcon(icon);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setOpaque(true);
        label.setBackground(isSelected ? LIST_SELECTION_BACKGROUND : LIST_DEFAULT_BACKGROUND);
        label.setForeground(isSelected ? Color.WHITE : Color.BLACK);

        if (isSelected)
            label.setBorder(LIST_HIGHLIGHT_BORDER);

        return label;
    };

    public TreeCellRenderer TREE_DETALLES_PAQUETE_RENDERER = new TreeCellRenderer()
    {
        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus)
        {
            JLabel label = new JLabel(String.valueOf(value));
            label.setIcon(leaf ? ARTICULO_ICON : PAQUETE_ICON);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setOpaque(true);
            label.setBackground(selected ? TABLE_SELECTION_BACKGROUND : TABLE_DEFAULT_BACKGROUND);

            if (hasFocus)
                label.setBorder(TABLE_HIGHLIGHT_BORDER);

            return label;
        }
    };

}
