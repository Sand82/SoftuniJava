package softuni.exam.instagraphlite.models.dtos;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

public class PicturesImportDTO {


    private String path;

    @DecimalMin("500")
    @DecimalMax("60000")
    private double size;

    public PicturesImportDTO() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
