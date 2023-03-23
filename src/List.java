public class List {

    private static class Node{
        Node next;
        String value;
        Node head;

        public Node(String value){
            this.value = value;
        }

        public Node(Node next, Node head) {
            this.next = next;
            this.head = head;
        }
    }

    private Node last;
    private int size;

    public void add (String value){
        if (last == null) setFirst(value);
        else addNew(value);
        size++;
    }

    private void setFirst (String value){
        var firstNode = new Node(value);
        last = firstNode;
        last.head =firstNode;
    }

    private void addNew (String value){
        var newNode = new Node(value);
        last.next = newNode;
        newNode.head = last.head;
        last.head = null;
        last = newNode;
    }

    public boolean remove (String value){
        if (last == null) return false;
        if (tryRemoveFirst(value)) return true;
        return remove(last.head, last.head.next, value);
    }

    private boolean tryRemoveFirst(String value){
        if (last.head.value.equals(value)) {
            if (last.head.next == null) last = null;
            else last.head = last.head.next;
            size--;
            return true;
        }
        return false;
    }

    private boolean remove (Node perv, Node curr, String value) {
        if (curr == null) return false;

        if (curr.value.equals(value)) {
            if(curr.head == null) {
                perv.next = curr.next;
            }else {
                perv.next = null;
                perv.head = curr.head;
            }
            size--;
            return false;
        }
        return remove(curr, curr.next, value);
    }
    public int size() {return size;}
}
