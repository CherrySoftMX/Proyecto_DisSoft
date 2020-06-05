package com.controladores;

import com.vista2.UIConstants;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Nicolás
 */
public final class ButtonCellEditor extends DefaultCellEditor implements UIConstants
{

    private JButton button;

    public ButtonCellEditor()
    {
        this(new JTextField());
    }

    private ButtonCellEditor(JTextField textField)
    {
        super(textField);
        button = new JButton();
        button.setIcon(DEFAULT_ROW_ICON);

        button.addActionListener(e -> fireEditingStopped());

        setClickCountToStart(1);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
    {
        return button;
    }

}
