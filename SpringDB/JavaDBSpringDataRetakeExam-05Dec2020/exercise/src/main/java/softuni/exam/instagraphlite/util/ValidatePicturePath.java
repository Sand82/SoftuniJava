package softuni.exam.instagraphlite.util;

import java.util.ArrayList;
import java.util.List;

public class ValidatePicturePath {
    public static ValidatePicturePath validatePicturePath;
    private static List<String> allowedPictureFormat = List.of("png", "jpeg", "jpg", "gif", "svg", "webp", "pjp", "pjpeg", "jfif", "avif", "apng", "img", "bmp", "digi");

    public static List<String> validatePath(String profilePicture) {

        List<String> errors = new ArrayList<>();

        if (profilePicture == null || profilePicture.trim().isEmpty() || profilePicture.length() < 4 || !profilePicture.contains(".")) {

            errors.add("Password should be more than 4 symbols");

        } else {

            String[] formatMatrix = profilePicture.split("\\.");

            String format = formatMatrix[formatMatrix.length - 1];

            if (!allowedPictureFormat.contains(format)) {

                errors.add("Wrong picture format");
            }
        }

        return errors;
    }
}
