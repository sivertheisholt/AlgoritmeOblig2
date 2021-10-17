package com.company.entities;

import java.util.Collection;

public interface Tree<E> extends Collection<E> {
    boolean search(E e); // ret. true dersom finst
    boolean insert(E e); // ret. true dersom OK
    boolean delete(E e); // ret. True dersom OK
    int getSize();

    // Vi såg på desse forrige veke
    @Override
    default boolean isEmpty() {
        return size() == 0;
    }
    @Override
    default boolean contains(Object e) {
        return search((E) e);
    }
    @Override
    default boolean add(E e){
        return insert(e);
    }
    @Override
    default boolean remove(Object e){
        return delete((E)e);
    }
    @Override
    default int size(){
        return getSize();
    }
}
