package rjl.com.springbootcoursedesign;

public class Check {

    private int id;
    private String sno;
    private String sname;
    private String result;
    private String wname;


    public Check() {
    }

    public Check(int id, String sno, String sname, String result, String wname) {
        this.id = id;
        this.sno = sno;
        this.sname = sname;
        this.result = result;
        this.wname = wname;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }
}
