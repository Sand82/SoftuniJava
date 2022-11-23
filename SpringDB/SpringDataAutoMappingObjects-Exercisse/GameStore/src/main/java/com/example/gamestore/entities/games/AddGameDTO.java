package com.example.gamestore.entities.games;

import com.example.gamestore.entities.exceptions.ValidationException;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AddGameDTO {

    private String title;
    private BigDecimal price;

    private float size;

    private String trailer;

    private String thumbnailUrl;

    private String description;

    private LocalDate releaseDate;

    public AddGameDTO(String[] data) {
        this.title = data[1];
        this.price = BigDecimal.valueOf(Long.parseLong(data[2]));
        this.size = Float.parseFloat(data[3]);
        this.trailer = data[4];
        this.thumbnailUrl = data[5];
        this.description = data[6];
        this.releaseDate = LocalDate.parse(data[7]);

        this.validateData();
    }

    private void validateData() {

        char upperLetter = title.toCharArray()[0];

        if (!Character.isUpperCase(upperLetter)) {
            throw new ValidationException("First letter should be upper case.");
        }

        if (title.length() < 3 || title.length() > 100) {
            throw new ValidationException("Title should be between 3 and 100 symbols.");
        }

        BigDecimal minValue = BigDecimal.valueOf(0.0);

        if (price.compareTo(minValue) < 0) {
            throw new ValidationException("Price should be positive value.");
        }

        if (size < 0) {
            throw new ValidationException("Size should be positive value");
        }

        if (trailer.length() != 11) {
            throw new ValidationException("Invalid url.");
        }

        if (!thumbnailUrl.startsWith("http://") && !thumbnailUrl.startsWith("https://")) {
            throw new ValidationException("Invalid url");
        }

        if (description.length() < 20) {
            throw new ValidationException("Description should be mor than 20 symbols.");
        }
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public float getSize() {
        return size;
    }

    public String getTrailer() {
        return trailer;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}
