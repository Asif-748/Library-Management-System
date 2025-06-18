
abstract class Person {
    protected String name;
    protected String email;
    protected String phone;
    protected int id;
    protected int age;
    protected int pin;

    public Person(String name, int age, String email, String phone, int id, int pin) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.id = id;
        this.pin = pin;
    }
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public abstract void displayInfo();
}
