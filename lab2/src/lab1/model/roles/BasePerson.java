package lab1.model.roles;

public abstract class BasePerson {
    protected final String name;
    protected final String surname;
    protected final int age;
    protected final PersonType personType;

    public BasePerson(PersonType type) {
        name = "Tom";
        surname = "Hankock";
        age = 25;
        personType = type;
    }

    public BasePerson(String name, String surname, int age, PersonType type) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.personType = type;
    }
}
