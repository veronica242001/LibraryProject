package models;

public abstract class Book {

        private String title;
        private Author author;
        private Section section;
        private Publisher publisher;


        public Book( String title, Author author, Section section, Publisher publisher) {

                this.title = title;
                this.author = author;
                this.section = section;
                this.publisher = publisher;
        }



        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public Author getAuthor() {
                return author;
        }

        public void setAuthor(Author author) {
                this.author = author;
        }

        public Section getSection() {
                return section;
        }

        public void setSection(Section section) {
                this.section = section;
        }

        public Publisher getPublisher() {
                return publisher;
        }

        public void setPublisher(Publisher publisher) {
                this.publisher = publisher;
        }

        @Override
        public String toString() {
                return "title='" + title + '\'' +
                        ", " + author +
                        ", section=" + section +
                        ", " + publisher;
        }
}
