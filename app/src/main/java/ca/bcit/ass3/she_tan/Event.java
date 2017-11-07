package ca.bcit.ass3.she_tan;

/**
 * Created by lulu on 2017-10-31.
 */

public class Event {
    private int _eventId;
    private String _name;
    private String _date;
    private String _time;

    public Event(String name, String date, String time) {
        setName(name);
        setDate(date);
        setTime(time);
    }

    public Event(int _eventId, String name, String date, String time) {
        setId(_eventId);
        setName(name);
        setDate(date);
        setTime(time);
    }

    public int getId() {
        return _eventId;
    }

    public void setId(int _id) {
        this._eventId = _id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public String getDate() {
        return _date;
    }

    public void setDate(String _date) {
        this._date = _date;
    }

    public String getTime() {
        return _time;
    }

    public void setTime(String _time) {
        this._time = _time;
    }

    @Override
    public String toString() {
        return _name + " " + _date + " " + _time;
    }
}

