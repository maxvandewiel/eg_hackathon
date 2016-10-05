package nl.maxvandewiel.service.userregistration.toa;

/**
 * Created by max on 6/4/16.
 */
public interface Toa<T extends Object, E extends Object> {
    E assemble(T t);
}