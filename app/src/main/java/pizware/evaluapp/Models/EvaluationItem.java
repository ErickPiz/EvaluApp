package pizware.evaluapp.Models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Piz on 11/01/2018.
 */

public class EvaluationItem extends RealmObject{
    @PrimaryKey
    private String name;
    private int weighing;

    public EvaluationItem() {
    }

    public EvaluationItem(String name) {
        this.name = name;
        this.weighing = 0;
    }

    public EvaluationItem(String name, int weighing) {
        this.name = name;
        this.weighing = weighing;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeighing() {
        return weighing;
    }

    public void setWeighing(int weighing) {
        this.weighing = weighing;
    }
}

