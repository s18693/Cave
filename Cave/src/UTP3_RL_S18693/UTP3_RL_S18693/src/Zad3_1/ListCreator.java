package Zad3_1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListCreator<T> {

    public List<T> list;
    public List<T> select = new ArrayList<>();

    public ListCreator(List<T> list) {
        this.list = list;
    }

    public static <T> ListCreator collectFrom(List l) {
        return new ListCreator<T>(l);
    }

    /*
    public ListCreator<T> when(Selector<T> sel) {
        for (T e : list) {
            if (sel.select(e))
                select.add(e);
        }
        return this;
    }

    public <S> List mapEvery(Mapper<T, S> map) {
        ArrayList<S> ml = new ArrayList<>();
        for (T e : select)
            ml.add(map.map(e));
        return ml;
    }
*/

    public ListCreator<T> when(Predicate<T> lista) {
        for (T n : list)
            if (lista.test(n))
                select.add(n);
        return this;
    }

    public <S> List mapEvery(Function<T, S> lista) {
        ArrayList<S> ml = new ArrayList<>();
        for (T n : select)
            ml.add(lista.apply(n));
        return ml;
    }
}
