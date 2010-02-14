package mcb.persistenz;


public interface TransactionAction {

	void runIn(PersistenceActionPerformer persistenceActionPerformer);

}
