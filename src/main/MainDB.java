package main;

import models.City;
import models.Librarian;
import models.Publisher;
import models.Reader;
import config.DatabaseSetup;
import repository.CityRepository;
import repository.LibrarianRepository;
import repository.PublisherRepository;
import repository.ReaderRepository;
import service.LibrarianService;
import service.PublisherService;
import service.ReaderService;

import java.util.Scanner;

public class MainDB {
    public static LibrarianRepository librarianRepository = LibrarianRepository.getInstance();
    public static ReaderRepository readerRepository = ReaderRepository.getInstance();
    public static CityRepository cityRepository = CityRepository.getInstance();
    public static PublisherRepository publisherRepository = PublisherRepository.getInstance();
    public static LibrarianService librarianService = LibrarianService.getInstance();
    public static ReaderService readerService = ReaderService.getInstance();
    public static PublisherService publisherService = PublisherService.getInstance();
    private static void menuLoop()
    {
        Scanner scanner = new Scanner(System.in);

        //0 - main menu
        int menuLocation = 0;
        int input;
        while(true)
        {
            //Main menu
            if(menuLocation == 0)
            {
                System.out.println("--Main Menu--");
                System.out.println("Options:");
                System.out.println("1-Librarians");
                System.out.println("2-Readers");
                System.out.println("3-Cities");
                System.out.println("4-Publishers");

                System.out.println("0-Exit");

                //Read input
                menuLocation = scanner.nextInt();

                if(menuLocation == 0)
                {
                    break;
                }
            }
            else if(menuLocation == 1)      //Librarians
            {
                System.out.println("--Librarians Menu--");
                System.out.println("1-Add Librarian");
                System.out.println("2-Remove Librarian");
                System.out.println("3-See all Librarians");
                System.out.println("4-Update Salary");
                System.out.println("5-Display By Id");
                System.out.println("0-Back");

                input = scanner.nextInt();

                if(input == 1){
                    Librarian lb = librarianService.newLibrarian();
                    librarianRepository.insertLibrarian(lb);
                }
                else if(input == 2){
                    System.out.println("Enter the librarianId:");
                    int id = scanner.nextInt();
                    librarianRepository.removeLibrarian(id);
                }
                else if (input == 3)
                {
                  for(Librarian lb : librarianRepository.getLibrarians()){
                    System.out.println(lb);
                }
                }
                else if(input == 4){
                    System.out.println("Enter the librarianId:");
                    int id = scanner.nextInt();
                    System.out.println("Enter the new salary");
                    float salary = scanner.nextFloat();
                    librarianRepository.updateLibrarianSalary(salary,id);
                }
                else if(input == 5){
                    System.out.println("Enter the librarianId:");
                    int id = scanner.nextInt();
                    Librarian librarian = librarianRepository.getLibrarianById(id);
                    System.out.println(librarian);
                }
                else if(input == 0)
                    menuLocation = 0;
            }
            else if(menuLocation == 2)      //Reader
            {
                System.out.println("--Reader Menu--");
                System.out.println("1-Add Reader");
                System.out.println("2-Remove Reader");
                System.out.println("3-See all readers");
                System.out.println("4-Update firstName");
                System.out.println("5-Display By Id");
                System.out.println("0-Back");

                input = scanner.nextInt();

                if(input == 1)
                {
                    Reader reader = readerService.newReader();
                    readerRepository.insertReader(reader);
                }
                else if(input == 2)
                {
                    System.out.println("Enter the readerId:");
                    int id = scanner.nextInt();
                    readerRepository.removeReader(id);
                }
                else if (input == 3)
                {
                  for(Reader rd: readerRepository.getReaders()){
                      System.out.println(rd);
                  }
                }
                else if( input == 4){
                    System.out.println("Enter the ReaderId:");
                    int id = scanner.nextInt();
                    System.out.println("Enter the correct first name of the reader");
                    String firstName = scanner.next();
                    readerRepository.updateReaderFisrtName(firstName, id);
                }
                else if(input == 5){
                    System.out.println("Enter the readerId:");
                    int id = scanner.nextInt();
                    Reader reader = readerRepository.getReaderById(id);
                    System.out.println(reader);
                }
                else if(input == 0)
                    menuLocation = 0;
            }
            else if(menuLocation == 3)      //Clients
            {
                System.out.println("--Cities Menu--");
                System.out.println("1-Add City");
                System.out.println("2-Remove City");
                System.out.println("3-See all cities");
                System.out.println("4-Update the name of the city");
                System.out.println("5-Display By Id");
                System.out.println("0-Back");

                input = scanner.nextInt();

                if(input == 1)
                {
                    String name, country;
                    System.out.println(" City name:");
                    name = scanner.next();
                    System.out.println("Country name");
                    country = scanner.next();
                    cityRepository.insertCity( new City(name, country));
                }
                else if(input == 2)
                {
                    int id;
                    System.out.println("Enter CityID:");
                    id = scanner.nextInt();
                    cityRepository.removeCity(id);
                }
                else if (input == 3)
                {  for(City city: cityRepository.getCities()){
                    System.out.println(city);
                }
                }
                else if (input == 4)
                {   int id;
                    System.out.println("Enter CityID:");
                    id = scanner.nextInt();
                    String name;
                    System.out.println("Enter the new name");
                    name= scanner.next();
                    cityRepository.updateCityName(name, id);

                }
                else if ( input == 5){
                    int id;
                    System.out.println("Enter CityID:");
                    id = scanner.nextInt();
                    City city = cityRepository.getCityById(id);
                    System.out.println(city);
                }
                else if(input == 0)
                    menuLocation = 0;
            }
            else if(menuLocation == 4)      //Publishers
            {
                System.out.println("--Publishers Menu--");
                System.out.println("1-Add Publisher");
                System.out.println("2-Remove Publisher");
                System.out.println("3-See all publishers");
                System.out.println("4-Update name");
                System.out.println("5-Display By Id");
                System.out.println("0-Back");

                input = scanner.nextInt();

                if(input == 1)
                {   System.out.println("Name:");
                    String name = scanner.next();

                    System.out.println("CityId");
                    int id = scanner.nextInt();
                    City city = cityRepository.getCityById(id);
                    Publisher  publisher = new Publisher(name, city);
                    publisherRepository.insertPublisher(publisher);

                }
                else if(input == 2)
                {
                    int id;
                    System.out.println("Enter PublisherID:");
                    id = scanner.nextInt();
                    publisherRepository.removePublisher(id);
                }
                else if(input == 3)
                {
                   for(Publisher pb: publisherRepository.getPublishers()){
                       System.out.println(pb);
                   }
                }
                else if(input == 4)
                {    int id;
                    System.out.println("Enter PublisherId:");
                    id = scanner.nextInt();
                    String name;
                    System.out.println("Enter the new name");
                    name= scanner.next();
                    publisherRepository.updatePublisherName(name,id);
                }
                else if( input == 5){
                    int id;
                    System.out.println("Enter PublisherId:");
                    id = scanner.nextInt();
                    Publisher publisher = publisherRepository.getPublisherById(id);
                    System.out.println(publisher);
                }

                else if(input == 0)
                    menuLocation = 0;
            }

        }



    }


    public static void main(String[] args) {
        DatabaseSetup setUpData = new DatabaseSetup();
        setUpData.setUp();
        menuLoop();
    }
}
