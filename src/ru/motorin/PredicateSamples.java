package ru.motorin;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateSamples {

    public static List<Man> collectChildren(List<Man> parents, Predicate<Man> manPredicate) {
        return parents.stream()
                .flatMap(parent -> parent.getChild().stream())
                .filter(manPredicate)
                .distinct()
                .collect(Collectors.toList());
    }

    public static Predicate<Man> старшеЧем(int age) {
        return man -> man.getAge() >= age;
    }

    public static Predicate<Man> сИменем(String имя) {
        return man -> имя.equals(man.getFirstName());
    }


    public static void main(String[] args) {

        // Хороший вариант
        Predicate<Man> старше_чем_18 = child -> child.getAge() >= 18;
        Predicate<Man> с_именем_петя = child -> "Петя".equals(child.getFirstName());
        System.out.println("Дети старше 18 и никого не зовут Петя: " + collectChildren(
                Man.Родители(),
                с_именем_петя.negate().and(старше_чем_18)));

        // Еще более универсальный и параметризованный для удобного переиспользования вариант
        System.out.println("Дети старше 18: " + collectChildren(Man.Родители(), старшеЧем(18)));
        System.out.println("Ребенок с именем Петя: " + collectChildren(Man.Родители(),сИменем("Петя")));

        // Вариант, когда мы не хотим засорять код класса функциями предикатов (весь код только в этом методе)
        Function<Integer, Predicate<Man>> старшеЧем = возраст -> man -> man.getAge() >= возраст;
        Function<String, Predicate<Man>> сИменем = имя -> man -> имя.equals(man.getFirstName());
        System.out.println("Дети старше 18: " + collectChildren(Man.Родители(), старшеЧем.apply(2)));
        System.out.println("Ребенок с именем Петя: " + collectChildren(Man.Родители(),сИменем.apply("Петя")));
    }
}

class Man {
    private int age;
    private String firstName;
    private String lastName;
    private List<Man> child;

    Man(int age, String firstName, String lastName, List<Man> child) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.child = child;
    }

    public static List<Man> Родители() {
        Man ПетрПетров = Man.builder()
                .firstName("Петя")
                .lastName("Петров")
                .age(19).build();

        Man МашаПетрова = Man.builder()
                .firstName("Маша")
                .lastName("Петрова")
                .age(14).build();

        Man ИльПетров = Man.builder()
                .firstName("Илья")
                .lastName("Петров")
                .age(21).build();

        Man ВасилийПетров = Man.builder()
                .firstName("Вася")
                .lastName("Петров")
                .age(45)
                .child(Arrays.asList(ПетрПетров, МашаПетрова, ИльПетров)).build();

        return Arrays.asList(ВасилийПетров);
    }

    public static ManBuilder builder() {
        return new ManBuilder();
    }

    public int getAge() {
        return this.age;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public List<Man> getChild() {
        return this.child;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setChild(List<Man> child) {
        this.child = child;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Man)) return false;
        final Man other = (Man) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getAge() != other.getAge()) return false;
        final Object this$firstName = this.getFirstName();
        final Object other$firstName = other.getFirstName();
        if (this$firstName == null ? other$firstName != null : !this$firstName.equals(other$firstName)) return false;
        final Object this$lastName = this.getLastName();
        final Object other$lastName = other.getLastName();
        if (this$lastName == null ? other$lastName != null : !this$lastName.equals(other$lastName)) return false;
        final Object this$child = this.getChild();
        final Object other$child = other.getChild();
        if (this$child == null ? other$child != null : !this$child.equals(other$child)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Man;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getAge();
        final Object $firstName = this.getFirstName();
        result = result * PRIME + ($firstName == null ? 43 : $firstName.hashCode());
        final Object $lastName = this.getLastName();
        result = result * PRIME + ($lastName == null ? 43 : $lastName.hashCode());
        final Object $child = this.getChild();
        result = result * PRIME + ($child == null ? 43 : $child.hashCode());
        return result;
    }

    public String toString() {
        return "Man(age=" + this.getAge() + ", firstName=" + this.getFirstName() + ", lastName=" + this.getLastName() + ", child=" + this.getChild() + ")";
    }

    public static class ManBuilder {
        private int age;
        private String firstName;
        private String lastName;
        private List<Man> child;

        ManBuilder() {
        }

        public ManBuilder age(int age) {
            this.age = age;
            return this;
        }

        public ManBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public ManBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public ManBuilder child(List<Man> child) {
            this.child = child;
            return this;
        }

        public Man build() {
            return new Man(age, firstName, lastName, child);
        }

        public String toString() {
            return "Man.ManBuilder(age=" + this.age + ", firstName=" + this.firstName + ", lastName=" + this.lastName + ", child=" + this.child + ")";
        }
    }
}