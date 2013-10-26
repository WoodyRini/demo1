package metaGamerClasses;


public class Insertion {

    // use natural order and Comparable interface
    public static void sort(Player[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0 && (a[j-1].getPoints() < a[j].getPoints()); j--) {
                exch(a, j, j-1);
            }
        }
    }
    
    
    // is v < w ? Is this all unnecessary?
 /*   private static boolean less(Player v, Player w) {
    	int v1 = v.getPoints();
    	int w1 = w.getPoints();
        return ((Comparable)v1.compareTo((Comparable)w1) < 0);
    }*/
        
    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    
        
}