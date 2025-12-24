package dev.rikelmearaujo.devtools.classes;

import java.util.ArrayList;
import java.util.List;

public class Node<T, U> {
    T type;
    U value;
    Node<T, U> next;
    List<Node<T, U>> children;

    public Node(T type, U value) {
        this.type = type;
        this.value = value;
        this.next = null;
        this.children = new ArrayList<>();
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
    public List<Node<T, U>> getChildren() {
        return children;
    }
    public void setChildren(List<Node<T, U>> children) {
        this.children = children;
    }
    public void addChildren(Node<T, U> child) {
        this.children.add(child);
    }
    public void removeChildren(Node<T, U> child) {
        this.children.remove(child);
    }
    public int getChildrenSize() {
        if (this.hasChildren()) return 0;
        return this.children.size();
    }
    public boolean hasChildren() {
        return this.children != null && !this.children.isEmpty();
    }
    public boolean hasNext() {
        return this.next != null;
    }
    public void clear() {
        this.value = null;
        this.next = null;
        if (this.hasChildren()) {
            this.children.clear();
        }
        this.children = null;
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
        for(Node<T, U> child : this.children) {
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
        for(Node<T, U> child : this.children) {
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
    public Node<T, U> at (int index) {
        if (index <= 0) {
            return this;
        } 
        if (hasNext()) {
            return next.at(index-1);
        }
        return null;
    }
    public int append(int index, Node<T, U> node) {
        Node<T, U> prev = this.at(index);
        if (prev == null) {
            return -1;
        }
        Node<T, U> next = prev.getNext();
        node.setNext(next);
        prev.setNext(node);
        return 0;
    }

    public String toString(String ident) {
        StringBuilder sb = new StringBuilder();
        sb.append(ident).append("Node{type=").append(type)
          .append(", value=").append(value);
        if (this.hasNext()) {
            sb.append(",\n").append(ident).append(" next=\n");
            sb.append(this.next.toString(ident + "  "));
        }
        if (this.hasChildren()) {
            sb.append(",\n").append(ident).append(" children=[\n");
            for (Node<T, U> child : this.children) {
                sb.append(child.toString(ident + "  ")).append(",\n");
            }
            sb.append(ident).append("]");
        }
        sb.append("}");
        return sb.toString();
    }

}
