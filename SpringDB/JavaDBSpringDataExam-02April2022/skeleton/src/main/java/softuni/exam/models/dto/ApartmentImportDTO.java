package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "apartment")
@XmlAccessorType(XmlAccessType.FIELD)
public class ApartmentImportDTO {

    @XmlElement
    private String apartmentType;
    @XmlElement
    private double area;
    @XmlElement
    private String town;

    public ApartmentImportDTO() {

    }

    public ApartmentImportDTO(String apartmentType, double area, String town) {
        this.apartmentType = apartmentType;
        this.area = area;
        this.town = town;

        validator();
    }

    public String getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public List<String> validator() {

        List<String> errors = new ArrayList<>();

        if (area < 40.00) {

            errors.add("Areas should be more than or equal to 40.00 square meters.");
        }

        if (!apartmentType.equals("two_rooms") && !apartmentType.equals("three_rooms") && !apartmentType.equals("four_rooms")) {

            errors.add(" Wrong apartment type.");
        }

        return errors;
    }
}
