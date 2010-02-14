package mcb.persistenz;

import mcb.model.McbModel;

import org.apache.log4j.Logger;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

public class PersistenceActionPerformer {

	private Session session;
	private Session readOnlySession;
	static final Logger LOGGER = Logger.getLogger(PersistenceActionPerformer.class.getName());

	public void delete(McbModel model) {
		PersistenceActionPerformer.LOGGER.info("DELETE of " + model.toLogString());
		this.session.delete(model);
	}

	private void logSaveAction(McbModel altesModel, McbModel model) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("SPEICHERN von ");
		buffer.append(model.toLogString());
		buffer.append(" ");
		if (altesModel != null) {
			buffer.append("(vorher: ");
			buffer.append(altesModel.toLogString());
			buffer.append(")");
		}
		PersistenceActionPerformer.LOGGER.info(buffer.toString());
	}

	public void performInTransaction(TransactionAction action) {
		this.session = HibernateStarter.getSessionFactory().openSession();
		this.readOnlySession = HibernateStarter.getSessionFactory().openSession();
		Transaction transe = this.session.beginTransaction();
		action.runIn(this);
		transe.commit();
		ApplicationData.closeSession(this.session);

	}

	public void saveOrUpdate(McbModel model) {
		McbModel oldModel = null;
		if (model.getId() != null) {
			oldModel = (McbModel) this.readOnlySession.load(model.getClass(), model.getId());
		}
		this.logSaveAction(oldModel, model);
		this.session.saveOrUpdate(model);
	}

}
