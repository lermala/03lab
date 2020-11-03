package sample.PersonPack;

public class Student extends Person {
    private String education; //Название учебного заведения
    private int level; // курс
    private float scholarship; //стипендия

    public Student(String name, int age, String education,
                   int level, float scholarship){
        super(name, age);
        this.education = education;
        this.level = level;
        this.scholarship = scholarship;
    }

    @Override
    public float getPayoutAmount(){ return scholarship; }

    public Student() {
    }

    @Override
    public String toString() {
        return super.toString() + " " + education + " "
                + level + " course, scholarship = " + scholarship;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public float getScholarship() {
        return scholarship;
    }

    public void setScholarship(float scholarship) {
        this.scholarship = scholarship;
    }
}
