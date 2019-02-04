package linkedList;

public class Node<T> {

    private Node<T> previous;
    private T Data;
    private Node<T> next;

    public Node() {
    }

    public Node(T Data) {
        this.Data = Data;
    }

    public Node<T> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }

    public T getData() {
        return Data;
    }

    public void setData(T Data) {
        this.Data = Data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                ", Data=" + Data +
                '}';
    }
}
