package View;

import javax.swing.*;
import java.awt.*;

public class JoueurSelectedListRenderer extends DefaultListCellRenderer{
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component c = super.getListCellRendererComponent(list, value, index, false, false);

       if(isSelected){
           c.setBackground(new Color(78, 94, 92));

       }

        return c;
    }

}
