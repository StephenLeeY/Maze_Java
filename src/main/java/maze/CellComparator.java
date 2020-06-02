package maze;

import java.util.Comparator;
import java.util.List;

public class CellComparator implements Comparator<List<Cell>> {

	@Override
	public int compare(List<Cell> o1, List<Cell> o2) {
		if(o1.size() < o2.size()) {
			return 1;
		} else if(o1.size() > o2.size()) {
			return -1;
		} else {
			return 0;
		}
	}

}
