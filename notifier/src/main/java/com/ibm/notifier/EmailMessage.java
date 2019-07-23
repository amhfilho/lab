package com.ibm.notifier;

/**
 * Wrapper that represents a simple e-mail message
 * @author Antonio Mario Henriques Filho
 */
public class EmailMessage {
    private String from;
    private String to;
    private String subject;
    private String body;

    /**
     * Builds an {@code EmailMessage} object
     */
    public static class Builder {
        private String from;
        private String to;
        private String subject;
        private String body;

        public Builder from(String from){
            this.from = from;
            return this;
        }

        public Builder to(String to){
            this.to = to;
            return this;
        }

        public Builder subject(String subject){
            this.subject = subject;
            return this;
        }

        public Builder body(String body){
            this.body = body;
            return this;
        }

        public EmailMessage build(){
            return new EmailMessage(this);
        }
    }

    private EmailMessage(Builder builder){
        this.body = builder.body;
        this.from = builder.from;
        this.subject = builder.subject;
        this.to = builder.to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }
}
