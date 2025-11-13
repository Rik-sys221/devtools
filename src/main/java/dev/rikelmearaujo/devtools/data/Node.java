package dev.rikelmearaujo.devtools.data;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    private T value;
    private List<Node<T>> childrens;
    
    public Node(T value, List<Node<T>> childrens) {
        this.value = value;
        this.childrens = childrens;
    }
    public Node(T value) {
        this.value = value;
        this.childrens = new ArrayList<>();
    }
    
    public void add(Node<T> node) {
        this.childrens.add(node);
    }

    public void remove(Node<T> node) {
        this.childrens.remove(node);
    }

    public Node<T> getChild(int index) {
        return this.childrens.get(index);
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public List<Node<T>> getChildrens() {
        return childrens;
    }

}
