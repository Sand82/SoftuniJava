package com.example.bookshop.dataTransferObjects;

import com.example.bookshop.entities.AgeRestriction;
import com.example.bookshop.entities.EditionType;

import java.math.BigDecimal;

public interface BookTitleEditionTypeAgeRestrictionPriceDTO {

    BigDecimal getPrice();

    AgeRestriction getAgeRestriction();

    EditionType getEditionType();

    String getTitle();
}
