public class Inheritance extends StringPractice1 {

    public void somemethod(){

        System.out.println("This is child");
    }

    public static void main(String[] args) {
        StringPractice1 inheritance = new Inheritance();
        inheritance.somemethod();
    }
}

class StringPractice1 {

    public void somemethod(){
        System.out.println("This is parent");
    }
}