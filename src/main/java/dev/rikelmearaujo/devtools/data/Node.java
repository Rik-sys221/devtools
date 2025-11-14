package dev.rikelmearaujo.devtools.data;

import java.util.ArrayList;
import java.util.List;

public class Node<T, U> {
    private U type;
    private T value;
    private List<Node<T, U>> childrens;

    public Node(U type, T value, List<Node<T, U>> childrens) {
        this.type = type;
        this.value = value;
        this.childrens = childrens;
    }
    public Node(U type, T value) {
        this.type = type;
        this.value = value;
        this.childrens = new ArrayList<>();
    }

    public void add(Node<T, U> node) {
        this.childrens.add(node);
    }

    public void remove(Node<T, U> node) {
        this.childrens.remove(node);
    }

    public Node<T, U> getChild(int index) {
        return this.childrens.get(index);
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public List<Node<T, U>> getChildrens() {
        return childrens;
    }

    @Override
    public String toString() {
        String childrenStr = childrens.stream()
                .map(Node::toString)
                .collect(java.util.stream.Collectors.joining(", "));

        return "{ type: " + type + ", value: " + value + ", childrens: [" + childrenStr + "] }";
    }

}
