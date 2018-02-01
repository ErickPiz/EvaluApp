package pizware.evaluapp.Models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Piz on 17/01/2018.
 */

public class Course extends RealmObject{

    @PrimaryKey
    private String courseName;
    private Group groupName;
    private User user;

    private RealmList<EvaluationItem> evItems;

    public Course() {
    }

    public Course(String courseName, Group groupName, User user) {
        this.courseName = courseName;
        this.groupName = groupName;
        this.user = user;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Group getGroupName() {
        return groupName;
    }

    public void setGroupName(Group groupName) {
        this.groupName = groupName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RealmList<EvaluationItem> getEvItems() {
        return evItems;
    }

    public void setEvItems(RealmList<EvaluationItem> eItems) {
        this.evItems = eItems;
    }

    public void setEvItems(EvaluationItem evItems){}
}
