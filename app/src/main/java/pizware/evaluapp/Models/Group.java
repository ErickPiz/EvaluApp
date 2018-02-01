package pizware.evaluapp.Models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Piz on 17/01/2018.
 */

public class Group extends RealmObject{
    @PrimaryKey
    private String name;
    private RealmList<Student> students;

    public Group() {
    }

    public Group(String name) {
        this.name = name;
    }

    public Group(String name, RealmList<Student> students) {
        this.name = name;
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Student> getStudents() {
        return students;
    }

    public void setStudents(RealmList<Student> students) {
        this.students = students;
    }

    public void setStudent(Student student){}
}
