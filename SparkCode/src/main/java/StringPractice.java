public class StringPractice {

    public void somemethod(){
        System.out.println("This is parent");
    }
}
class Some extends StringPractice{
    public void somemethod(){
        System.out.println("This is child");
    }

    public static void main(String[] args) {
        Some s = new Some();

s.somemethod();
    }
}


