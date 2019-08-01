package com.ibm;

import javax.mail.*;
import java.io.IOException;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

public class Application extends TimerTask{

    private static final Logger log = Logger.getLogger(Application.class.getName());

    public static void main(String[] args ) {


        Timer timer=new Timer();
        timer.schedule(new Application(),Integer.parseInt(args[0]),1000*60);


    }


    @Override
    public void run() {
        try{
            Properties properties = new PropertiesManager().loadFrom(PropertiesManager.SMTP_PROPERTIES);
            StoreProvider storeProvider = new GmailStoreProvider(properties);
            Store store = storeProvider.getStore();
            Folder inbox = store.getFolder("inbox");
            EmailReader reader = new EmailReader(inbox);

            log.info("Starting Email Reader");
            log.info("Open connection...");
            inbox.open(Folder.READ_WRITE);
            if(reader.findUnreadTargetMessage(properties.getProperty("mail.subject"))!=null){
                log.info("Calling restart...");
                Runtime.getRuntime().exec("C:\\Windows\\System32\\schtasks.exe /run /tn \"UAC pass\\RestartTeamViewer\"");
            }
            inbox.close(true);
            //log.info("Sleeping "+interval/1000+" minute(s)...");
            //Thread.sleep(interval*60);
            log.info("Closed connection");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
