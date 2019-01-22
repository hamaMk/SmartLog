package elements.rogue.smartlog.types;

import java.util.Date;

public class Log {

    private String situation;
    private String descrition;
    private String comment;
    private String date;

    public Log() {
    }

    public Log(String situation, String descrition, String comment) {
        this.situation = situation;
        this.descrition = descrition;
        this.comment = comment;
    }

    public Log(String situation, String descrition, String comment, String date) {
        this.situation = situation;
        this.descrition = descrition;
        this.comment = comment;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getDescrition() {
        return descrition;
    }

    public void setDescrition(String descrition) {
        this.descrition = descrition;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
