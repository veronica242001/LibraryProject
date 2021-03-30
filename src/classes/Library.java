package classes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Library {
    private String name;
    private City city;
    private LinkedList<LibraryBook> libraryBooks;
    private LinkedList<BookForRent> booksForRent; // vom face mai mult operatii de add/remove
    private ArrayList<Librarian> librarians;
    private ArrayList<Reader> readers;


    public Library(String name, City city) {
        this.name = name;
        this.city = city;
        this.libraryBooks = new LinkedList<>();
        this.booksForRent = new LinkedList<>();
        this.librarians = new ArrayList<>();
        this.readers = new ArrayList<>();


    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public City getCity() {

        return city;
    }

    public void setCity(City city) {

        this.city = city;
    }

    public LinkedList<LibraryBook> getLibraryBooks() {

        return libraryBooks;
    }

    public void setLibraryBooks(LinkedList<LibraryBook> libraryBooks) {

        this.libraryBooks = libraryBooks;
    }

    public LinkedList<BookForRent> getBooksForRent() {

        return booksForRent;
    }

    public void setBooksForRent(LinkedList<BookForRent> booksForRent) {

        this.booksForRent = booksForRent;
    }

    public ArrayList<Librarian> getLibrarians() {

        return librarians;
    }

    public void setLibrarians(ArrayList<Librarian> librarians) {

        this.librarians = librarians;
    }

    public ArrayList<Reader> getReaders() {

        return readers;
    }

    public void setReaders(ArrayList<Reader> readers) {

        this.readers = readers;
    }


    //---------------------------

    public void addLibraryBook(LibraryBook libraryBook) {

        libraryBooks.add(libraryBook);
    }

    public void addBookForRent(BookForRent bookForRent) {

        booksForRent.add(bookForRent);
    }

    //---------------------------
    public BookForRent getBookForRentByTitle(String title) {
        for (BookForRent b : booksForRent) {
            if (b.getTitle().equals(title)) {
                return b;
            }
        }
        return null;
    }


    //---------------------
    public Librarian getLibrarianByName(String firstNameLibrarian, String lastNameLibrarian) {
        for (Librarian l : librarians) {
            if (l.getFirstName().equals(firstNameLibrarian) && l.getLastName().equals(lastNameLibrarian)) {
                return l;
            }
        }
        return null;

    }

    //-----------------------------
    public Reader getReaderByName(String firstNameReader, String lastNameReader) {
        for (Reader r : readers) {
            if (r.getFirstName().equals(firstNameReader) && r.getLastName().equals(lastNameReader)) {
                return r;
            }
        }
        return null;
    }


    //--------------------------------
    public boolean removeBooksByAuthor(String firstName, String lastName) {
        boolean rmv = false;
       int i = 0;
       while( i < booksForRent.size()){
           BookForRent b = booksForRent.get(i);
            if (b.getAuthor().getFirstName().equals(firstName) && b.getAuthor().getLastName().equals(lastName)) {
                booksForRent.remove(b);

                rmv = true;
            }
            else
            {
                i++;
            }
        }
       i= 0;
        while( i < libraryBooks.size()){
            LibraryBook b = libraryBooks.get(i);
            if (b.getAuthor().getFirstName().equals(firstName) && b.getAuthor().getLastName().equals(lastName)) {
                libraryBooks.remove(b);
                rmv = true;
            }
            else
            {
                i++;
            }
        }
        return rmv;
    }

    //-------------------------
    public void increaseTheSalary(float p) {
        for (Librarian lb : librarians) {
            float salary = lb.getSalary();
            salary += salary * p;
            lb.setSalary(salary);

        }

    }

    //----------------
    public void addLibrarian( Librarian librarian){

        librarians.add(librarian);
    }

    //----------------
    public void addReader(Reader reader) {

        readers.add(reader);
    }
}






