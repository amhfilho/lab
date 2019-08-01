package com.ibm;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import java.util.Properties;

public class GmailStoreProvider implements StoreProvider{

    private Properties properties;
    private Store store;

    public GmailStoreProvider(Properties properties){
        this.properties = properties;
    }

    @Override
    public Store getStore() {
        try {
            Session session = Session.getDefaultInstance(properties, null);
            store = session.getStore("imaps");
            store.connect(properties.getProperty("mail.smtp.host"),
                    properties.getProperty("mail.account"),
                    properties.getProperty("mail.password"));
            return store;
        } catch (MessagingException e){
            throw new EmailReaderException(e.getMessage(),e);
        }
    }
}
