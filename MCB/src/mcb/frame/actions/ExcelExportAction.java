package mcb.frame.actions;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import mcb.persistenz.ExcelExporter;

public class ExcelExportAction extends AbstractAction {
	private final Component parent;
	private static final long serialVersionUID = 8568897588247326614L;

	public ExcelExportAction(Component parent, String name) {
		super(name);
		this.parent = parent;
	}

	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new FileFilter() {

			@Override
			public boolean accept(File f) {
				return f.getName().endsWith(".xls");
			}

			@Override
			public String getDescription() {
				return "Excel";
			}
		});
		int result = chooser.showSaveDialog(this.parent);
		if (result == JFileChooser.APPROVE_OPTION) {
			new ExcelExporter().exportiereAdressen(chooser.getSelectedFile());
		}
	}
}