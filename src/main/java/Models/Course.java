package Models;

public class Course {
    public String id;
    public String title;
    public int credit;
    public int tuitionPerCredit;
    public int subTotal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getTuitionPerCredit() {
        return tuitionPerCredit;
    }

    public void setTuitionPerCredit(int tuitionPerCredit) {
        this.tuitionPerCredit = tuitionPerCredit;
    }

    public int getSubTotal(){
        return subTotal;
    }

    public void setSubTotal() {
        this.subTotal = getCredit() * getTuitionPerCredit();
    }
}
