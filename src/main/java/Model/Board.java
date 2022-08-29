package Model;

import java.util.LinkedList;

public class Board {
    private LinkedList<Field> fields;

    public LinkedList<Field> getFields() {
        return fields;
    }

    public void setFields(LinkedList<Field> fields) {
        this.fields = fields;
    }
}
