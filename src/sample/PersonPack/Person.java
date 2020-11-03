package sample.PersonPack;

public abstract class Person implements RequestInterface, java.io.Serializable {

    private String name;//фио
    private int age;

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    public abstract float getPayoutAmount();

    @Override
    public String toString() {
        return name + " age: " + age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
