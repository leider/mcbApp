package mcb.frame.actions;

import java.awt.event.ActionEvent;

import mcb.frame.SimpleFrame;
import mcb.panel.McbAction;
import mcb.persistenz.ApplicationData;
import mcb.persistenz.filter.AdresseFilter;

import com.jgoodies.binding.beans.Model;

public class FilterAction<T extends Model> extends McbAction {

	private static final long serialVersionUID = -5779635312416205673L;
	private AdresseFilter filter;
	private SimpleFrame<T> frame;

	public FilterAction(AdresseFilter filter, SimpleFrame<T> simpleFrame) {
		super(filter.getLabel(), filter.getKeyMask());
		this.filter = filter;
		this.frame = simpleFrame;
	}

	public void actionPerformed(ActionEvent e) {
		ApplicationData.setFilter(this.filter);
		this.frame.updateListe();
	}

}
