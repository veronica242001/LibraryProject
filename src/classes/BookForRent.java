package classes;

public class BookForRent extends Book{
        public String status; // available/unavailable

    public BookForRent(String title, Author author, Section section, Publisher publisher, String status) {
        super( title, author, section, publisher);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




    @Override
    public String toString() {

        return "BookForRent: " + super.toString()+
                "status='" + status + '\'';
    }
}
