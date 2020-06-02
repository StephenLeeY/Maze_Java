package custom;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		outer:
		while(true) {
			System.out.println("1");
			for(int i = 0; i < 5; i++) {
				System.out.println("2");
				break outer;
			}
		}
	}
}
