package classes;

public class Librarian  extends Person implements  Comparable<Librarian> {
    private String phoneNumber;
    private float salary;

    public Librarian( String firstName, String lastName, String phoneNumber, float salary) {
        super( firstName, lastName);
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }
    public Librarian( int id, String firstName, String lastName, String phoneNumber, float salary) {
        super(id, firstName, lastName);
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Librarian:" +super.toString()+
                ", phoneNumber='" + phoneNumber + '\'' +
                ", salary=" + salary ;
    }


    @Override
    public int compareTo(Librarian l) {
        return ( int)(- this.salary + l.salary);
    }
}
