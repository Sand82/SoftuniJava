package CustomList_07;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sorter<T extends Comparable<T>> {

    public static void  sort(CustomList list ){
      Collections.sort(list.getElements());
    }

}
