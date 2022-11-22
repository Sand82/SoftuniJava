package com.example.bookshop;

import com.example.bookshop.dataTransferObjects.AuthorsBooksCopiesDTO;
import com.example.bookshop.dataTransferObjects.BookTitleEditionTypeAgeRestrictionPriceDTO;
import com.example.bookshop.entities.AgeRestriction;
import com.example.bookshop.entities.Author;
import com.example.bookshop.entities.Book;
import com.example.bookshop.entities.EditionType;
import com.example.bookshop.repositories.AuthorRepository;
import com.example.bookshop.repositories.BookRepository;
import com.example.bookshop.services.SeedService;
import org.hibernate.type.LocalDateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;

    private AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public ConsoleRunner(SeedService seedService, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.seedService = seedService;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        //seedService.seedAuthors();
        //seedService.seedCategories();
        //seedService.seedBooks();
        //seedService.seedAll();

        //this._01_BooksTitlesByAgeRestriction(scanner);
        //this._02_GoldenBooks();
        //this._03_BooksByPrice();
        //this._04_NotReleasedBooks(scanner);
        //this._05_BooksReleasedBeforeDate(scanner);
        //this._06_AuthorsSearch(scanner);
        //this._07_BooksSearch(scanner);
        //this._8_BookTitlesSearch(scanner);
        //this._9_CountBooks(scanner);
        //this._10_TotalBookCopies();
        //this._11_ReducedBook(scanner);
        //this._12_IncreaseBookCopies(scanner);
        this._13_RemoveBooks(scanner);
    }

    private void _13_RemoveBooks(Scanner scanner) {

        int number = Integer.parseInt(scanner.nextLine());

        bookRepository.deleteByCopiesLessThan(number);
    }

    private void _12_IncreaseBookCopies(Scanner scanner) {

        String dateInput = scanner.nextLine();

        DateTimeFormatter format = DateTimeFormatter.ofPattern(("dd MMM yyyy"));

        LocalDate date = LocalDate.parse(dateInput, format);

        int amount = Integer.parseInt(scanner.nextLine());

        int booksUpdated = bookRepository.addCopiesToBooksAfter(date, amount);

        System.out.println(String.format("%d books are released after %s so total of %d book copies were added",
                booksUpdated, date, amount * booksUpdated));
    }

    private void _11_ReducedBook(Scanner scanner) {

        String title = scanner.nextLine();

        BookTitleEditionTypeAgeRestrictionPriceDTO book = bookRepository.findByTitle(title);

        System.out.println(String.format("%s %s %s %.2f", book.getTitle(), book.getEditionType(), book.getAgeRestriction(), book.getPrice()));
    }

    private void _10_TotalBookCopies() {

        List<AuthorsBooksCopiesDTO> authors = authorRepository.findByAuthorByCopiesBooks();

        authors.forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName() + " - " + a.getTotalCopies()));
    }

    private void _9_CountBooks(Scanner scanner) {

        int number = Integer.parseInt(scanner.nextLine());

        int numbersOfBooks = bookRepository.countOfBooksTitleLongerThen(number);

        System.out.println("There are " + numbersOfBooks + " books with longer title than " + number + " symbols");
    }

    private void _8_BookTitlesSearch(Scanner scanner) {

        String input = scanner.nextLine();

        List<Book> books = bookRepository.findByAuthorLastNameStartingWith(input);

        books.forEach(b -> System.out.println(b.getTitle() + " (" + b.getAuthor().getFirstName() + " " + b.getAuthor().getLastName() + ")"));
    }

    private void _07_BooksSearch(Scanner scanner) {

        String input = scanner.nextLine();

        List<Book> books = bookRepository.findByTitleContains(input);

        printBooks(books);
    }

    private void _06_AuthorsSearch(Scanner scanner) {

        String input = scanner.nextLine();

        List<Author> authors = authorRepository.findByFirstNameEndingWith(input);

        authors.forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));
    }

    private void _05_BooksReleasedBeforeDate(Scanner scanner) {

        String dateInput = scanner.nextLine();

        DateTimeFormatter format = DateTimeFormatter.ofPattern(("dd-MM-yyyy"));

        LocalDate date = LocalDate.parse(dateInput, format);

        List<Book> books = bookRepository.findByReleaseDateBefore(date);

        printBooks(books);
    }

    private void _04_NotReleasedBooks(Scanner scanner) {

        int yearString = Integer.parseInt(scanner.nextLine());

        LocalDate yearBefore = LocalDate.of(yearString, 01, 01);

        LocalDate yearAfter = LocalDate.of(yearString, 12, 31);

        List<Book> books = bookRepository.findByReleaseDateBeforeOrReleaseDateAfter(yearBefore, yearAfter);

        printBooks(books);
    }

    private void _03_BooksByPrice() {

        BigDecimal lowerPrice = BigDecimal.valueOf(5.0);
        BigDecimal higherPrice = BigDecimal.valueOf(40.0);

        List<Book> books = bookRepository.findByPriceLessThanOrPriceGreaterThan(lowerPrice, higherPrice);

        books.forEach(b -> System.out.println(b.getTitle() + " - $" + b.getPrice()));
    }

    private void _02_GoldenBooks() {

        EditionType editionType = EditionType.GOLD;

        int copies = 5000;

        List<Book> books = bookRepository.findByEditionTypeAndCopiesLessThan(editionType, copies);

        printBooks(books);
    }

    private void _01_BooksTitlesByAgeRestriction(Scanner scanner) {

        String ageRestrictionInput = scanner.nextLine();

        AgeRestriction ageRestriction = AgeRestriction.valueOf(ageRestrictionInput.toUpperCase());

        List<Book> books = bookRepository.findByAgeRestriction(ageRestriction);

        printBooks(books);
    }

    private static void printBooks(List<Book> books) {
        books.forEach(b -> System.out.println(b.getTitle()));
    }
}
