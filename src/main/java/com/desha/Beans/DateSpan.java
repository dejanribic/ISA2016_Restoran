package com.desha.Beans;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by Nenad on 3/1/2017.
 */
public class DateSpan {
    Timestamp start;
    Timestamp end;

    public DateSpan() {
    }

    public DateSpan(Timestamp start, Timestamp end) {
        this.start = start;
        this.end = end;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }
}
