/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pressurecompressor.containers.nodetypes;

/**
 * A tuple
 *
 * @author rochet2_2
 * @param <F> type of first element
 * @param <S> type of second element
 */
public class Pair<F, S> extends Object {

    /**
     * Creates a new Pair with given values as first and second
     *
     * @param first
     * @param second
     */
    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }
    public F first;
    public S second;

}
