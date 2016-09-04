package mcb.mail;

import mcb.persistenz.PersistenceStore;

public interface SendCompleteListener {

  void currentlySending(String infoText);

  PersistenceStore getPersistenceStore();

  void messagesNotSent();

  void messagesSent();

}
