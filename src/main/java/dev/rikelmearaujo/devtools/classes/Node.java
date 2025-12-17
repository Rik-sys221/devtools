package dev.rikelmearaujo.devtools.classes;

import java.util.ArrayList;
import java.util.List;

public class Node<T, U> {
    T type;
    U value;
    Node<T, U> next;
    List<Node<T, U>> childrens;

    public Node(T type, U value) {
        this.type = type;
        this.value = value;
        this.next = null;
        this.childrens = new ArrayList<>();
    }

    public U getValue() {   
        return value;
    }
    public void setValue(U value) {
        this.value = value;
    }
    public Node<T, U> getNext() {
        return next;
    }
    public void setNext(Node<T, U> next) {
        this.next = next;
    }
    public List<Node<T, U>> getChildrens() {
        return childrens;
    }
    public void setChildrens(List<Node<T, U>> childrens) {
        this.childrens = childrens;
    }
    public void addChildren(Node<T, U> child) {
        this.childrens.add(child);
    }
    public void removeChildren(Node<T, U> child) {
        this.childrens.remove(child);
    }
    public int getChildrensSize() {
        if (this.hasChildrens()) return 0;
        return this.childrens.size();
    }
    public boolean hasChildrens() {
        return this.childrens != null && !this.childrens.isEmpty();
    }
    public boolean hasNext() {
        return this.next != null;
    }
    public void clear() {
        this.value = null;
        this.next = null;
        if (this.hasChildrens()) {
            this.childrens.clear();
        }
        this.childrens = null;
    }
    public Node<T, U> last() {
        Node<T, U> current = this;
        while (current.hasNext()) {
            current = current.getNext();
        }
        return current;
    }
    public Node<T, U> searchChildByType(T type, int depth) {
        if (depth <= 0) {
            return null;
        }
        for(Node<T, U> child : this.childrens) {
            if (child.type.equals(type)) {
                return child;
            }
            Node<T, U> result = child.searchChildByType(type, depth - 1);
            if (result != null) {
                return result;
            }
        }
        return null;
    }
    public Node<T, U> searchChildByValue(U value, int depth) {
        if (depth <= 0) {
            return null;
        }
        for(Node<T, U> child : this.childrens) {
            if (child.value.equals(value)) {
                return child;
            }
            Node<T, U> result = child.searchChildByValue(value, depth - 1);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    public String toString(String ident) {
        StringBuilder sb = new StringBuilder();
        sb.append(ident).append("Node{type=").append(type)
          .append(", value=").append(value);
        if (this.hasNext()) {
            sb.append(",\n").append(ident).append(" next=\n");
            sb.append(this.next.toString(ident + "  "));
        }
        if (this.hasChildrens()) {
            sb.append(",\n").append(ident).append(" childrens=[\n");
            for (Node<T, U> child : this.childrens) {
                sb.append(child.toString(ident + "  ")).append(",\n");
            }
            sb.append(ident).append("]");
        }
        sb.append("}");
        return sb.toString();
    }

}
