package builderPattern;

import java.time.Period;

public class Contact {

    private String name;
    private String number;
    private String company;
    private String title;
    private String email;
    private String website;
    private String birthday;

    public Contact(String name, String number, String company, String title, String email, String website, String birthday) {
        this.setName(name);
        this.setNumber(number);
        this.company = company;
        this.title = title;
        this.email = email;
        this.website = website;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().equals("") || name.length() < 2) {
            throw new IllegalStateException("The name must contain at least 2 characters!");
        }

        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        if (number == null || number.trim().equals("") || number.length() < 2) {
            throw new IllegalStateException("The name must contain at least 2 characters!");
        }

        this.number = number;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private String number;
        private String company;
        private String title;
        private String email;
        private String website;
        private String birthday;

        public Builder whitName(String name) {
            this.name = name;
            return this;
        }

        public Builder whitNumber(String number) {
            this.number = number;
            return this;
        }

        public Builder whitCompany(String company) {
            this.company = company;
            return this;
        }

        public Builder whitTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder whitEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder whitWebsite(String website) {
            this.website = website;
            return this;
        }

        public Builder whitBirthday(String birthday) {
            this.birthday = birthday;
            return this;
        }

        public Contact build() {
            return new Contact(name, number, company, title, email, website, birthday);
        }
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", company='" + company + '\'' +
                ", title='" + title + '\'' +
                ", email='" + email + '\'' +
                ", website='" + website + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
