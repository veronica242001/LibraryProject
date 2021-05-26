package classes;

public class Reader extends Person{

    private String phoneNumber;

    public Reader( String firstName, String lastName, String phoneNumber) {
        super( firstName, lastName);
        this.phoneNumber = phoneNumber;
    }
    public Reader( int id, String firstName, String lastName, String phoneNumber) {
        super(id, firstName, lastName);
        this.phoneNumber = phoneNumber;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Reader: " + super.toString()+
                ", phoneNumber= '" + phoneNumber +'\'';
    }
}
