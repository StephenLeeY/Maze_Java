package maze;

import java.util.Comparator;

public class WallComparator implements Comparator<Wall> {

	@Override
	public int compare(Wall o1, Wall o2) {
		if(o1.weight > o2.weight) {
			return 1;
		} else if(o1.weight < o2.weight) {
			return -1;
		} else {
			return 0;
		}
	}

}
