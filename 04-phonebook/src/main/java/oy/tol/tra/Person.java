package oy.tol.tra;

public class Person implements Comparable<Person> {
    private String firstName;
    private String lastName;

    public Person(final Person person) {
        this.firstName = new String(person.firstName);
        this.lastName = new String(person.lastName);
    }
    
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }

    @Override
    public String toString() {
        return getFullName();
    }

    /**
     * Calculates hash value for the Person object based on first and last names.
     * 
     * @return Hash value of the person.
     */
    @Override
    public int hashCode() {
        int hash = 31;
        String fallName = getFullName();
        for (int i=0;i<fallName.length();i++){
            hash = (hash * 31 +fallName.charAt(i));
        }
        return hash;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Person person = (Person) other;
        return firstName.equals(person.firstName) && lastName.equals(person.lastName);
    }

    /**
     * Compares two persons based on their full names.
     * 
     * @param other The other Person object to compare to.
     * @returns Returns 0 if the persons' full names are identical.
     *          Returns a negative value if this person's full name is lexicographically less than the other person's.
     *          Returns a positive value if this person's full name is lexicographically greater than the other person's.
     */
    @Override
    public int compareTo(Person other) {
        return getFullName().compareTo(other.getFullName());
    }
}