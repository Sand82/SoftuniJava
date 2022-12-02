package softuni.exam.instagraphlite.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "posts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostsImportDTO {

    @XmlElement(name = "post")
    List<PostImportDTO> posts;

    public PostsImportDTO() {
    }

    public List<PostImportDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostImportDTO> posts) {
        this.posts = posts;
    }
}
