package other.GUI1;

public class Subject {
    protected String name, teacher;
    public Student[] students = new Student[1];
    private int count = 0;

    public Subject(String name) {
        this.name = name;
    }

    public void setTeacher(Person p) {
        teacher = p.name;
    }

    public void addStudent(Person p) throws TooManyStudentsException{
        if(count + 1 > students.length)
            throw new TooManyStudentsException();
        students[count++] = new Student(p.name, p.surname);
    }

    public String toString() {
        String studnestName = "";
        for (int a = 0; a < students.length; a++)
            studnestName += students[a].name + " ";
        return name + " teacher " + teacher + " students: " + studnestName;
    }
}
