package rjl.com.springbootcoursedesign;

public class Worker {

    private int id;
    private String wname;
    private String wno;
    private String wtype;

    public Worker() {
    }

    public Worker(int id, String wname, String wno, String wtype) {
        this.id = id;
        this.wname = wname;
        this.wno = wno;
        this.wtype = wtype;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", wname='" + wname + '\'' +
                ", wno='" + wno + '\'' +
                ", wtype='" + wtype + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }

    public String getWno() {
        return wno;
    }

    public void setWno(String wno) {
        this.wno = wno;
    }

    public String getWtype() {
        return wtype;
    }

    public void setWtype(String wtype) {
        this.wtype = wtype;
    }
}
