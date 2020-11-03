package sample.PersonPack;

public class Worker extends Person {
    private String placeOfWork;
    private String position; //должность
    private float salary; //оклад

    public Worker(String name, int age, String placeOfWork,
                  String position, float salary){
        super(name, age);
        this.placeOfWork = placeOfWork;
        this.position = position;
        this.salary = salary;
    }
@Override
    public float getPayoutAmount(){
        return salary;
    }

    @Override
    public String toString() {
        return super.toString() + " " + placeOfWork + " "
                + position + " salary = " + salary;
    }

    public Worker() {
    }

    public String getPlaceOfWork() {
        return placeOfWork;
    }

    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }
}
