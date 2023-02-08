import edu.geekhub.homework.inject.Injectable;

import java.util.Objects;

class ObjectWithAnnotatedField {
    @Injectable
    private String field;

    public ObjectWithAnnotatedField(String field) {
        this.field = field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectWithAnnotatedField object = (ObjectWithAnnotatedField) o;
        return Objects.equals(field, object.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field);
    }
}
