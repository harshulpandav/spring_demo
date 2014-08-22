import java.util.Random;
import java.util.Scanner;
public class InputService {
	public int getIntValue() {
		Random r=new Random();
		int val=r.nextInt();
		System.out.println("Returning value = "+val);
		return val;
	}
}
