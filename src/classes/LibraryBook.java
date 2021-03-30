package classes;

public class LibraryBook extends Book{   // aceste carti nu pot imprumutate, pot fi citite doar in incinta bibliotecii

    public LibraryBook( String title, Author author, Section section, Publisher publisher) {
        super( title, author, section, publisher);
    }

    @Override
    public String toString() {

        return "LibraryBook: " + super.toString();
    }
}
