package Telephony_05;

import java.util.List;

public class Smartphone implements Callable, Browsable {
    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    @Override
    public String browse() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < urls.size(); i++) {

            String url = urls.get(i);
            boolean isValidUrl = validateUrl(url);

            if (isValidUrl) {

                sb.append(String.format("Browsing: %s!", url));
            } else {

                sb.append("Invalid URL!");
            }

            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    @Override
    public String call() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < numbers.size(); i++) {

            String number = numbers.get(i);
            boolean isValidNumber = validateNumber(number);

            if (isValidNumber) {

                sb.append(String.format("Calling... %s", number));
            } else {

                sb.append("Invalid number!");
            }

            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    private boolean validateUrl(String url) {

        for (int i = 0; i < url.length(); i++) {

            char urlPart =  url.charAt(i);

            if (Character.isDigit(urlPart)) {
                return false;
            }
        }

        return true;
    }


    private boolean validateNumber(String number) {

        for (int i = 0; i < number.length(); i++) {

            char urlPart =  number.charAt(i);

            if (!Character.isDigit(urlPart)) {
                return false;
            }
        }

        return true;
    }
}
