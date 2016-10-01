/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pressurecompressor.containers;

import pressurecompressor.StringUtility;
import pressurecompressor.containers.linkedlist.LinkedList;

/**
 * A class that handles keeping a dictionary of strings mapped to numbers
 *
 * @author rimi
 */
public class Dictionary {

    private final LinkedList<String> lru;
    private int elements = 0;

    /**
     * Creates a new dictionary that can contain at maximum given amount of
     * elements
     *
     * @param amountOfElements
     */
    public Dictionary(int amountOfElements) {
        this.lru = new LinkedList<>();
    }

    /**
     * Returns the index of the given string from dictionary or null
     *
     * @param string
     * @return
     */
    public Integer get(String string) {
        if (string == null) {
            return null;
        }
        if (lru.remove(string)) {
            lru.pushBack(string);
            return elements;
        }
        return null;
    }

    /**
     * Returns the string at given index or null
     *
     * @param index Indexes start from 0
     * @return
     */
    public String get(int index) {
        if (index >= elements) {
            return null;
        }
        if (index < 0) {
            return null;
        }
        return dictionary[index];
    }

    /**
     * Adds a new string to the dictionary if it is not full.
     *
     * @param string
     */
    public void add(String string) {
        System.out.println(string);
        if (isFull()) {
            System.out.println("full");
            return;
        }
        if (string == null) {
            return;
        }
        dictionary[elements] = string;
        ++elements;
    }

    /**
     * Returns true if the dictionary is full and false otherwise
     *
     * @return
     */
    public boolean isFull() {
        return elements >= dictionary.length;
    }

    /**
     * Returns the dictionary as a string representation.
     *
     * The format of the string is [key]=value and these are separated by a
     * space. The string ends with a space.
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < elements; ++i) {
            sb.append("[").append(i).append("]=");
            sb.append(dictionary[i]);
            sb.append(" ");
        }
        return sb.toString();
    }

}
