# LibraryProject

Classes

1.Person (abstract)
  Class to model persons(used for readers, librarians, authors).

2.Author - extends Person

3.Librarian - extends Person

4.Reader - extends Person

5.Book( abstract)
  Class to model books(used for BookForRent and LibraryBook).

6.BookForRent - extends Book

7.LibraryBook - extends Book

8.Publisher

9.Library

10.City

11.LoanForm

12.Section ( Enum : "HISTORY", "FICTION", "CRIME", "SCIENCE", "PHILOSOPHY", "PSYCHOLOGY")

Interactions:

          -Add a new reader
          -Add new publisher
          -Add LibraryBook
          -See all publishers
          -See all Books
          -See all readers
          -See BooksForRent by section
          -See sections by a publisher
          -Borrow a book
          -Remove books by author
          -Increases the salary of librarians
          -Calculate the average salary
          -Librarians descending by salary
          -See all forms
