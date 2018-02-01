package pizware.evaluapp.Models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Piz on 11/01/2018.
 */

public class Student extends RealmObject{

    @PrimaryKey
    private String studentName;
    private Group group;

    public Student() {

    }

    public Student(String studentName, Group group) {
        this.studentName = studentName;
        this.group = group;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
