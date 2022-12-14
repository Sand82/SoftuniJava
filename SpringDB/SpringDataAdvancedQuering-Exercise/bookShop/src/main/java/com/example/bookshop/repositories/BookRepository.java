package com.example.bookshop.repositories;

import com.example.bookshop.dataTransferObjects.BookTitleEditionTypeAgeRestrictionPriceDTO;
import com.example.bookshop.entities.AgeRestriction;
import com.example.bookshop.entities.Book;
import com.example.bookshop.entities.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    List<Book> findByPriceLessThanOrPriceGreaterThan(BigDecimal lowerPrice, BigDecimal higherPrice);


    List<Book> findByReleaseDateBeforeOrReleaseDateAfter(LocalDate yearBefore, LocalDate yearAfter);

    List<Book> findByReleaseDateBefore(LocalDate date);

    List<Book> findByTitleContains(String input);

    List<Book> findByAuthorLastNameStartingWith(String input);

    @Query("SELECT COUNT(b) FROM books b WHERE length(b.title) > :length")
    int countOfBooksTitleLongerThen(int length);

    BookTitleEditionTypeAgeRestrictionPriceDTO findByTitle(String title);

    @Modifying
    @Transactional
    @Query("UPDATE books b SET b.copies = b.copies + :amount WHERE b.releaseDate > :date")
    int addCopiesToBooksAfter(LocalDate date, int amount);

    @Transactional
    void deleteByCopiesLessThan(int amount);
}
