package com.driver;

import com.driver.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Gmail extends Email {
    private int inboxCapacity;
    private List<Mail> inbox;
    private List<Mail> trash;

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        this.inbox = new ArrayList<>();
        this.trash = new ArrayList<>();
    }

    public void receiveMail(Date date, String sender, String message) {
        if (inbox.size() >= inboxCapacity) {
            moveOldestMailToTrash();
        }
        inbox.add(new Mail(date, sender, message));
    }

    private void moveOldestMailToTrash() {
        Mail oldestMail = inbox.get(0);
        inbox.remove(oldestMail);
        trash.add(oldestMail);
    }

    public void deleteMail(String message) {
        Mail mailToRemove = null;
        for (Mail mail : inbox) {
            if (mail.getMessage().equals(message)) {
                mailToRemove = mail;
                break;
            }
        }
        if (mailToRemove != null) {
            inbox.remove(mailToRemove);
            trash.add(mailToRemove);
        }
    }

    public String findLatestMessage() {
        if (inbox.isEmpty()) {
            return null;
        }
        Mail latestMail = inbox.get(inbox.size() - 1);
        return latestMail.getMessage();
    }

    public String findOldestMessage() {
        if (inbox.isEmpty()) {
            return null;
        }
        Mail oldestMail = inbox.get(0);
        return oldestMail.getMessage();
    }

    public int findMailsBetweenDates(Date start, Date end) {
        int count = 0;
        for (Mail mail : inbox) {
            if (mail.getDate().compareTo(start) >= 0 && mail.getDate().compareTo(end) <= 0) {
                count++;
            }
        }
        return count;
    }

    public int getInboxSize() {
        return inbox.size();
    }

    public int getTrashSize() {
        return trash.size();
    }

    public void emptyTrash() {
        trash.clear();
    }

    public int getInboxCapacity() {
        return inboxCapacity;
    }

    private class Mail {
        private Date date;
        private String senderId;
        private String message;

        public Mail(Date date, String senderId, String message) {
            this.date = date;
            this.senderId = senderId;
            this.message = message;
        }

        public Date getDate() {
            return date;
        }

        public String getMessage() {
            return message;
        }
    }

}