public class Ola {
    Node head;

    @Override
    public String toString() {
        return "Ola{" +
                "head=" + head +
                '}';
    }

    static class Node {
        Integer data;
        Node next;

        Node(Integer d) {
            this.data = d;
        }
    }

    public static void main(String[] args) {
        Ola o = new Ola();
        o.head = new Node(1);
        o.head.next = new Node(2);
        o.head.next.next = new Node(3);
        o.head.next.next.next = new Node(4);
        o.head.next.next.next.next = new Node(5);
        printList(o.head);
        Node reverse = reverse(o.head);

        printList(reverse);
//        System.out.println(reverse.data.toString()+","+reverse.next.data+","+reverse.next.next.data);
//        System.out.println(o.head+","+o.head.next+","+o.head.next.next);

    }

    //1 -> 2 -> 3 -> 4 -> 5 -> 6
    private static Node reverse(Node head) {
        Node temp = null;
        Node prev = null;
        while (head != null){
            temp = head.next; //
            head.next = prev;
            prev = head;
            head = temp;

        }
        return prev;
    }
    //1 -> 2 -> 3 -> 4 -> 5 -> 6
    private static Node reverseUnsolved(Node head) {
        Node temp = null;
        Node prev = null;
        while (head.next != null){
            temp = head.next; //
            head.next = head; // you are doing circular referencing here
//            prev = head;
            head = temp;

        }
        return head;
    }

    //1 -> 2 -> 3
    private static Node reverseNew(Node node) {
        Node temp = null;
        Node current = node;
        Node prev = null;
        while (current != null){
            temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        return prev;
    }

     static Node reverse1(Node node)
    {
        Node prev = null;
        Node current = node;
        Node next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        node = prev;
        return node;
    }

    // prints content of double linked list
    static void printList(Node node)
    {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

}
