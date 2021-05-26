package service;

import classes.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class LibraryService {
    private static LibraryService libraryService = null;

    private ArrayList<LoanForm> forms;

    private Library library;
    private WriteCsv write = WriteCsv.getInstance();
    private PublisherService publisherService = PublisherService.getInstance();

    private LibraryService(){}
    public static LibraryService getInstance() {
        if (libraryService == null) {
            libraryService = new LibraryService();
        }
        return libraryService;
    }

   public void updateInfo(Library library) {

        this.library = library;
        forms = new ArrayList<LoanForm>();

       HashSet<Publisher> publishers = new HashSet<Publisher>();
        for (BookForRent bk : library.getBooksForRent()) {
            publishers.add(bk.getPublisher());
        }
        for (LibraryBook lbk : library.getLibraryBooks()) {
            publishers.add(lbk.getPublisher());
        }
        publisherService.setPublishers(publishers);

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


    //------------
    public void addLibraryBook() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Title:");
        String title = scanner.nextLine();

        System.out.println("===About author===");
        Author author = addAuthor();

        System.out.println("===Publishers available in the database===");
        for (Publisher p : publisherService.getPublishers())
            System.out.println(p);

        System.out.println("Publisher(name):");

        String publisherName = scanner.nextLine();
        Publisher publisher = publisherService.getPublisherByName(publisherName);

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

                write.WriteToCsv("src/resources/RW/libraryBook_data.csv", library.getLibraryBooks());
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
        for (Publisher p : publisherService.getPublishers())
            System.out.println(p);

        System.out.println("Publisher(name):");

        String publisherName = scanner.nextLine();
        Publisher publisher = publisherService.getPublisherByName(publisherName);

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

                write.WriteToCsv("src/resources/RW/bookForRent_data.csv", library.getBooksForRent());
            } else {
                System.out.println("This section doesn't exist in the database");
            }

        }

    }

    public Library getLibrary() {
        return library;
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
        write.WriteToCsv("src/resources/RW/bookForRent_data.csv", library.getBooksForRent());
        write.WriteToCsv("src/resources/RW/libraryBook_data.csv", library.getLibraryBooks());

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

        write.WriteToCsv("src/resources/RW/forms_data.csv", forms);
    }


}
