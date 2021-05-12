package service;

import classes.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class LibraryService {
    private ArrayList<LoanForm> forms;
    private Set<Publisher> publishers;

    private Library library;
    private WriteCsv write = WriteCsv.getInstance();


    public LibraryService(Library library) {

        this.library = library;
        forms = new ArrayList<LoanForm>();

        publishers = new HashSet<Publisher>();
        for (BookForRent bk : library.getBooksForRent()) {
            publishers.add(bk.getPublisher());
        }
        for (LibraryBook lbk : library.getLibraryBooks()) {
            publishers.add(lbk.getPublisher());
        }

    }

    //---------------
    public void addNewReader() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("First Name:");
        String FirstName = scanner.nextLine();

        System.out.println("Last Name:");
        String LastName = scanner.nextLine();

        System.out.println("Phone Number:");
        String phoneNumber = scanner.nextLine();
        Reader reader = new Reader(FirstName, LastName, phoneNumber);
        library.addReader(reader);

        write.WriteToCsv("src/reader_data.csv", library.getReaders());


    }

    //---------------
    public void addNewLibrarian() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("First Name:");
        String FirstName = scanner.nextLine();

        System.out.println("Last Name:");
        String LastName = scanner.nextLine();

        System.out.println("Phone Number:");
        String phoneNumber = scanner.nextLine();

        System.out.println("Salary:");
        String s = scanner.nextLine();
        float salary = Float.parseFloat(s);

        Librarian lb = new Librarian(FirstName, LastName, phoneNumber, salary);
        library.addLibrarian(lb);

        write.WriteToCsv("src/librarian_data.csv", library.getLibrarians());


    }


    //----------------
    public void addNewPublisher() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Name:");
        String name = scanner.nextLine();

        System.out.println("Country:");
        String country = scanner.nextLine();

        System.out.println("City Name:");
        String cityName = scanner.nextLine();

        City city = new City(cityName, country);
        Publisher publisher = new Publisher(name, city);


        addPublisher(publisher);
    }


    //--------------
    public Author addAuthor() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("First Name:");
        String firstName = scanner.nextLine();

        System.out.println("Last Name:");
        String lastName = scanner.nextLine();

        System.out.println("OriginCountry:");
        String origin = scanner.nextLine();

        Author author = new Author(firstName, lastName, origin);
        return author;

    }


    // ------------
    public void allPublishers() {
        for (Publisher p : publishers)
            System.out.println(p);
    }

    //------------
    public void allReaders() {
        ArrayList<Reader> readers = library.getReaders();
        for (Reader reader : readers) {
            System.out.println(reader);
        }
    }

    //-------------
    public Publisher getPublisherByName(String name) {
        for (Publisher p : publishers) {
            if (p.getName().equals(name))
                return p;
        }
        return null;
    }


    //------------
    public void addLibraryBook() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Title:");
        String title = scanner.nextLine();

        System.out.println("===About author===");
        Author author = addAuthor();

        System.out.println("===Publishers available in the database===");
        allPublishers();
        System.out.println("Publisher(name):");

        String publisherName = scanner.nextLine();
        Publisher publisher = getPublisherByName(publisherName);

        if (publisher == null) {
            System.out.println("It doesn't exist in the database");

        } else {
            System.out.println("===Sections can be: History,Fiction, Crime, Science,Philosophy, Psychology=== ");
            List<String> sections = new ArrayList<>(Arrays.asList("HISTORY", "FICTION", "CRIME", "SCIENCE", "PHILOSOPHY", "PSYCHOLOGY"));
            String sectionName = scanner.nextLine();
            if (sections.contains(sectionName.toUpperCase())) {
                Section section = Section.valueOf(sectionName.toUpperCase());

                LibraryBook lbk = new LibraryBook(title, author, section, publisher);
                library.addLibraryBook(lbk);

                write.WriteToCsv("src/libraryBook_data.csv", library.getLibraryBooks());
            } else {
                System.out.println("This section doesn't exist in the database");
            }

        }

    }

    //------------
    public void addBookForRent() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Title:");
        String title = scanner.nextLine();

        System.out.println("===About author===");
        Author author = addAuthor();

        System.out.println("===Publishers available in the database===");
        allPublishers();
        System.out.println("Publisher(name):");

        String publisherName = scanner.nextLine();
        Publisher publisher = getPublisherByName(publisherName);

        if (publisher == null) {
            System.out.println("It doesn't exist in the database");

        } else {
            System.out.println("===Sections can be: History,Fiction, Crime, Science,Philosophy, Psychology=== ");
            List<String> sections = new ArrayList<>(Arrays.asList("HISTORY", "FICTION", "CRIME", "SCIENCE", "PHILOSOPHY", "PSYCHOLOGY"));
            String sectionName = scanner.nextLine();
            if (sections.contains(sectionName.toUpperCase())) {
                Section section = Section.valueOf(sectionName.toUpperCase());

                System.out.println("====Status(available/unavailable)========");
                String status = scanner.nextLine();
                BookForRent bk = new BookForRent(title, author, section, publisher, status);
                library.addBookForRent(bk);

                write.WriteToCsv("src/bookForRent_data.csv", library.getBooksForRent());
            } else {
                System.out.println("This section doesn't exist in the database");
            }

        }

    }


    //-----------
    public void allBooks() {
        System.out.println("===BooksForRent====");
        for (BookForRent bk : library.getBooksForRent()) {
            System.out.println(bk);
        }
        System.out.println("===LibraryBooks====");
        for (LibraryBook lb : library.getLibraryBooks()) {
            System.out.println(lb);
        }
    }


    //-------------
    public void getBooksForRentBySection() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Sections can be: History, Fiction, Crime, Science, Philosophy, Psychology === ");
        List<String> sections = new ArrayList<>(Arrays.asList("HISTORY", "FICTION", "CRIME", "SCIENCE", "PHILOSOPHY", "PSYCHOLOGY"));
        String sectionName = scanner.nextLine();
        if (sections.contains(sectionName.toUpperCase())) {

            Section section = Section.valueOf(sectionName.toUpperCase());
            boolean found = false;
            for (BookForRent b : library.getBooksForRent()) {
                if (b.getSection().equals(section)) {
                    System.out.println(b);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("There are no BooksForRent in this section");
            }
        } else {
            System.out.println("This section doesn't exist in database");
        }

    }

    //------------
    public void borrowBook() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" TitleBook:");
        String titleBook = scanner.nextLine();

        BookForRent bk = library.getBookForRentByTitle(titleBook);


        if (bk != null) {
            if (bk.getStatus().equals("available")) {

                System.out.println("FirstNameLibrarian");
                String firstNameLibrarian = scanner.nextLine();

                System.out.println("LastNameLibrarian");
                String lastNameLibrarian = scanner.nextLine();

                Librarian librarian = library.getLibrarianByName(firstNameLibrarian, lastNameLibrarian);

                if (librarian != null) {

                    System.out.println("FirstNameReader");
                    String firstNameReader = scanner.next();

                    System.out.println("LastNameReader: ");
                    String lastNameReader = scanner.next();

                    Reader reader = library.getReaderByName(firstNameReader, lastNameReader);

                    if (reader != null) {
                        System.out.println("Date: (YYYY-MM-DD)");
                        String date = scanner.next();

                        LocalDate loanDate = LocalDate.parse(date);
                        LoanForm loanForm = new LoanForm(bk, reader, librarian, loanDate);


                        addForm(loanForm);
                        System.out.println("It was added successfully.");

                    } else {
                        System.out.println("This person isn't registered.");
                    }

                } else {
                    System.out.println("This person isn't a librarian");
                }

            } else {
                System.out.println("Sorry, this book is unavailable");
            }

        } else {
            System.out.println("The book isn't in this library");

        }

    }


    //---------------
    public void removeBooksByAuthor() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("First Name:");
        String firstName = scanner.nextLine();

        System.out.println("Last Name:");
        String lastName = scanner.nextLine();

        if (library.removeBooksByAuthor(firstName, lastName)) {
            System.out.println("The books have been successfully deleted.");
        } else {
            System.out.println("This author doesn't exist in the database");
        }
        write.WriteToCsv("src/bookForRent_data.csv", library.getBooksForRent());
        write.WriteToCsv("src/libraryBook_data.csv", library.getLibraryBooks());

    }

    //------------
    public void increaseTheSalary() throws IOException {
        float p;
        Scanner scanner = new Scanner(System.in);
        System.out.println("The percentage - [0,1]");
        p = scanner.nextFloat();
        library.increaseTheSalary(p);

        System.out.println("The changes have been made successfully");
        System.out.println(library.getLibrarians());
        write.WriteToCsv("src/librarian_data.csv", library.getLibrarians());

    }

    //-------------
    public void averageSalary() {

        ArrayList<Librarian> librarians = library.getLibrarians();

        float sum = 0f;
        for (Librarian librarian : librarians) {
            sum += librarian.getSalary();
        }

        float avg = sum / librarians.size();
        System.out.println("The average Salary: " + avg);

    }

    //---------------
    public void sectionsByPublisher() {
        Scanner scanner = new Scanner(System.in);


        System.out.println("===Publishers available in the database===");
        allPublishers();
        System.out.println("Publisher : (name) ");
        String name = scanner.nextLine();

        Publisher publisher = getPublisherByName(name);

        if (publisher != null) {
            Set<Section> sections = new HashSet<>();

            LinkedList<LibraryBook> libraryBooks = library.getLibraryBooks();
            LinkedList<BookForRent> booksForRent = library.getBooksForRent();

            for (LibraryBook lb : libraryBooks) {
                if (lb.getPublisher().equals(publisher)) {
                    sections.add(lb.getSection());
                }
            }

            for (BookForRent bk : booksForRent) {
                if (bk.getPublisher().equals(publisher)) {
                    sections.add(bk.getSection());
                }
            }

            System.out.println(sections);

        } else {
            System.out.println("This name doesn't exist in database");
        }
    }

    //-----------------
    public void librariansDescendingBySalary() {
        ArrayList<Librarian> librarians;
        librarians = library.getLibrarians();

        Collections.sort(librarians);

        for (Librarian librarian : librarians) {
            System.out.println(librarian.getFirstName() + " " + librarian.getLastName() + " " + librarian.getSalary());
        }
    }

    //---------------
    public void allForms() {
        for (LoanForm f : forms) {
            System.out.println(f);
        }
    }

    //--------------
    public void addForm(LoanForm lf) throws IOException {

        forms.add(lf);

        write.WriteToCsv("src/forms_data.csv", forms);
    }

    //---------
    public void addPublisher(Publisher p) throws IOException {
        publishers.add(p);
        ArrayList<Publisher> pb = new ArrayList<>();
        for (Publisher pp : publishers) {
            pb.add(pp);
        }
        write.WriteToCsv("src/publisher_data.csv", pb);
    }
}
