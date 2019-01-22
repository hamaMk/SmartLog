package elements.rogue.smartlog.types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Weekly {

    private List<Log> logs = new ArrayList<>();
    private Date date;
    private int week;

    public Weekly() {
    }

    public Weekly(List<Log> logs, Date date, int week) {
        this.logs = logs;
        this.date = date;
        this.week = week;
    }

    public List<Log> getLogs() {
        return logs;
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }

    public void addLog(Log log){
        logs.add(log);
    }

    public Log getLog(int index){
        return logs.get(index);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }
}
