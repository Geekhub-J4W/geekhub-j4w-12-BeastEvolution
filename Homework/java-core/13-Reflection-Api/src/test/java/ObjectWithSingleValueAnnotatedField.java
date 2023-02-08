import edu.geekhub.homework.inject.Injectable;

import java.util.Objects;

class ObjectWithSingleValueAnnotatedField {
    @Injectable(propertyName = "fieldName")
    private String field;

    public ObjectWithSingleValueAnnotatedField(String field) {
        this.field = field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectWithSingleValueAnnotatedField that = (ObjectWithSingleValueAnnotatedField) o;
        return Objects.equals(field, that.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field);
    }

    @Override
    public String toString() {
        return "ObjectWithSingleValueAnnotatedField{" +
            "field='" + field + '\'' +
            '}';
    }
}
