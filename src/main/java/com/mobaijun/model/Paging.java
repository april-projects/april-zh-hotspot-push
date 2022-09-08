package com.mobaijun.model;

/**
 * software：IntelliJ IDEA 2022.1
 * class name: Paging
 * class description：
 *
 * @author MoBaiJun 2022/9/7 17:20
 */
public class Paging {
    private boolean isEnd;
    private String next;
    private String previous;

    public Paging() {
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    @Override
    public String toString() {
        return "Paging{" +
                "isEnd=" + isEnd +
                ", next='" + next + '\'' +
                ", previous='" + previous + '\'' +
                '}';
    }
}
