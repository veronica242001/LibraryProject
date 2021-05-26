package service;

import classes.*;

import java.io.IOException;
import java.util.*;

public class PublisherService {

    private static PublisherService publisherService = null;

    private Set<Publisher> publishers;
    private WriteCsv write = WriteCsv.getInstance();

    private PublisherService(){}

    public static PublisherService getInstance(){
        if(publisherService == null){
            publisherService = new PublisherService();
        }
        return publisherService;
    }
    public Publisher newPublisher(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Name:");
        String name = scanner.nextLine();

        System.out.println("Country:");
        String country = scanner.nextLine();

        System.out.println("City Name:");
        String cityName = scanner.nextLine();

        City city = new City(cityName, country);
        Publisher publisher = new Publisher(name, city);
        return publisher;
    }

    //----------------
    public void addNewPublisher() throws IOException {

        Publisher publisher = newPublisher();
        addPublisher(publisher);
    }

    public Set<Publisher> getPublishers() {
        return publishers;
    }

    public void setPublishers(Set<Publisher> publishers) {

        this.publishers = publishers;
    }

    //---------
    public void addPublisher(Publisher p) throws IOException {
        publishers.add(p);
        ArrayList<Publisher> pb = new ArrayList<>();
        for (Publisher pp : publishers) {
            pb.add(pp);
        }
        write.WriteToCsv("src/resources/RW/publisher_data.csv", pb);
    }

    // ------------
    public void allPublishers() {
        for (Publisher p : publishers)
            System.out.println(p);
    }
    //--------------
    public Publisher getPublisherByName(String name) {
        for (Publisher p : publishers) {
            if (p.getName().equals(name))
                return p;
        }
        return null;
    }

    //---------------
    public void sectionsByPublisher(LibraryService libraryService) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("===Publishers available in the database===");
        allPublishers();
        System.out.println("Publisher : (name) ");
        String name = scanner.nextLine();

        Publisher publisher = getPublisherByName(name);

        if (publisher != null) {
            Set<Section> sections = new HashSet<>();

            LinkedList<LibraryBook> libraryBooks = libraryService.getLibrary().getLibraryBooks();
            LinkedList<BookForRent> booksForRent = libraryService.getLibrary().getBooksForRent();

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

}
