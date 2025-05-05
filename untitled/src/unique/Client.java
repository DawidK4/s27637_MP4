package unique;

import java.util.HashSet;
import java.util.Set;

public class Client {
    private String clientNumber;
    private String email;

    private static Set<String> existingClientNumbers = new HashSet<>();
    private static Set<String> existingEmails = new HashSet<>();

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        if (clientNumber == null) {
            throw new IllegalArgumentException("Client number cannot be null.");
        }

        if (this.clientNumber != null) {
            existingClientNumbers.remove(this.clientNumber);
        }

        if (!existingClientNumbers.add(clientNumber)) {
            throw new IllegalArgumentException("Client number must be unique: " + clientNumber);
        }

        this.clientNumber = clientNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null.");
        }

        if (this.email != null) {
            existingEmails.remove(this.email);
        }

        if (!existingEmails.add(email)) {
            throw new IllegalArgumentException("Email must be unique: " + email);
        }

        this.email = email;
    }
}
