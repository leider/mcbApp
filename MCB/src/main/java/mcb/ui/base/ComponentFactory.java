package mcb.ui.base;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.binding.value.AbstractValueModel;
import com.jgoodies.binding.value.BufferedValueModel;

/**
 * temporary class to make future changes in JGoodies easier
 *
 */
public class ComponentFactory {

  public static JCheckBox createCheckBox(BufferedValueModel bufferedModel, String markedText) {
    return BasicComponentFactory.createCheckBox(bufferedModel, markedText);
  }

  public static JComboBox createComboBox(SelectionInList<String> selectionInList) {
    return BasicComponentFactory.createComboBox(selectionInList);
  }

  public static JFormattedTextField createIntegerField(AbstractValueModel model) {
    return BasicComponentFactory.createIntegerField(model);
  }

  public static JList createList(SelectionInList<?> selectionInList) {
    return BasicComponentFactory.createList(selectionInList);
  }

  public static JTextArea createTextArea(BufferedValueModel bufferedModel, boolean commitOnFocusLost) {
    return BasicComponentFactory.createTextArea(bufferedModel, commitOnFocusLost);
  }

  public static JTextField createTextField(BufferedValueModel bufferedModel, boolean commitOnFocusLost) {
    return BasicComponentFactory.createTextField(bufferedModel, commitOnFocusLost);
  }

}
