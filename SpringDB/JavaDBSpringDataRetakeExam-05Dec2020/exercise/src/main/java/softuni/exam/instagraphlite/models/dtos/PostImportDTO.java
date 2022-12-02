package softuni.exam.instagraphlite.models.dtos;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "post")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostImportDTO {

    @XmlElement
    @Size(min = 21)
    private String caption;

    @XmlElement(name = "user")
    private PostUserImportDTO user;

    @XmlElement(name = "picture")
    private PostPictureImportDTO picture;

    public PostImportDTO() {
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public PostUserImportDTO getUser() {
        return user;
    }

    public void setUser(PostUserImportDTO user) {
        this.user = user;
    }

    public PostPictureImportDTO getPicture() {
        return picture;
    }

    public void setPicture(PostPictureImportDTO picture) {
        this.picture = picture;
    }
}
