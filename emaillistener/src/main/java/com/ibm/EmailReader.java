package com.ibm;

import javax.mail.*;
import javax.mail.search.FlagTerm;
import java.io.IOException;
import java.util.logging.Logger;

public class EmailReader {

    private Store store;
    private Folder folder;
    private static final Logger log = Logger.getLogger(EmailReader.class.getName());

    public EmailReader(Folder folder){
       this.folder=folder;
    }


    /**
     * Search for the unread message that contains the specified subject. Returns null if no message is found
     * @param subject the search term inside subject
     * @return the found message
     * @throws MessagingException
     */
    public Message findUnreadTargetMessage(String subject) throws MessagingException{
        Message[] unreadMessages= folder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
        for(Message m:unreadMessages){
            if(m.getSubject().contains(subject)){
                log.info("Find target message. Marking as read...");
                m.setFlag(Flags.Flag.SEEN,true);
                return m;
            }
        }
        return null;
    }



}
