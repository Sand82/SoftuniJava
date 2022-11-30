package softuni.exam.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "offer")
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferInputDTO {

    @XmlElement
    @Positive
    private double price;

    @XmlElement(name = "agent")
    private AgentOfferInputDTO agent;

    @XmlElement(name = "apartment")
    private ApartmentOfferInputDTO apartment;

    @XmlElement
    @JsonFormat(pattern = "dd/MM/yyyy")
    private String publishedOn;

    public OfferInputDTO() {
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public AgentOfferInputDTO getAgent() {
        return agent;
    }

    public void setAgent(AgentOfferInputDTO agent) {
        this.agent = agent;
    }

    public ApartmentOfferInputDTO getApartment() {
        return apartment;
    }

    public void setApartment(ApartmentOfferInputDTO apartment) {
        this.apartment = apartment;
    }

    public String getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(String publishedOn) {
        this.publishedOn = publishedOn;
    }
}
