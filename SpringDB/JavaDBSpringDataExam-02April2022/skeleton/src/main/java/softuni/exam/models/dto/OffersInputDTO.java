package softuni.exam.models.dto;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "offers")
@XmlAccessorType(XmlAccessType.FIELD)
public class OffersInputDTO {

    @XmlElement(name = "offer")
    List<OfferInputDTO> offers;

    public OffersInputDTO() {
    }

    public List<OfferInputDTO> getOffers() {
        return offers;
    }

    public void setOffers(List<OfferInputDTO> offers) {
        this.offers = offers;
    }
}
