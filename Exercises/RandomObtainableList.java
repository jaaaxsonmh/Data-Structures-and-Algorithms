import java.util.ArrayList;
import java.util.Random;

public class RandomObtainableList<E> extends ArrayList<E> implements RandomObtainable<E> {

    private Random random;
    
    RandomObtainableList()
    {
        super(); // sets up ArrayList contents
        random = new Random();
    }

    @Override
    public E getRandom() {
        
        // Generate random number from 0 to this.size - 1

        // GET and return random item from parent ArrayList
        // hint: look at the available functions from the "this" keyword
        
        return this.get(random.nextInt((this.size() - 1)));
    }

    @Override
    public boolean removeRandom() {
        
        // Generate random number from 0 to this.size - 1
    	
        // REMOVE and return random item from parent ArrayList
        // hint: look at the available functions from the "this" keyword
        
        return this.remove(random.nextInt((this.size() - 1))) != null;
    }
    
    public static void main(String [] args) throws Exception
	{
		RandomObtainableList<Object> roList = new RandomObtainableList<>();

		roList.add("Jack");
		roList.add("Murray");
		roList.add("Hosking");
		roList.add(1234);
		
		System.out.println("Starting List: ");
		for(Object e : roList)
		{
			System.out.println( e);
		}
		
		for(int i = 0; i < 10; i++)
		{
			//Ten Random Calls
			System.out.println( i + ": " + roList.getRandom());
		}
		
		System.out.println("Removed token? " + roList.removeRandom());
		System.out.println("Ending List: ");
		for(Object e : roList)
		{
			//Print contents
			System.out.println( e);
		}
		
	}
}