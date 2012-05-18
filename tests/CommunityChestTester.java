import junit.framework.TestCase;
import javax.swing.*;
import org.junit.*;

/** Class to test all methods inside the CommunityChest class.
 *
 *  @author Daniel Neel */
public class CommunityChestTester extends TestCase {
    @Test
    public void testCommunityChest() {
	CommunityChest testCommChestCard = new CommunityChest(1);
	assertEquals(testCommChestCard.getType(), 1);
	assertEquals(testCommChestCard.getText(), "Advance to Go (Collect $200)");

	testCommChestCard = new CommunityChest(2);
	assertEquals(testCommChestCard.getType(), 2);
	assertEquals(testCommChestCard.getText(), "Bank error in your favor – collect $200");

	testCommChestCard = new CommunityChest(3);
	assertEquals(testCommChestCard.getType(), 3);
	assertEquals(testCommChestCard.getText(), "Doctor's fees – Pay $50");

	testCommChestCard = new CommunityChest(4);
	assertEquals(testCommChestCard.getType(), 4);
	assertEquals(testCommChestCard.getText(), "Get Out of Jail Free – this card may be kept until needed, or sold");

	testCommChestCard = new CommunityChest(5);
	assertEquals(testCommChestCard.getType(), 5);
	assertEquals(testCommChestCard.getText(), "Go to Jail – go directly to jail – Do not pass Go, do not collect $200");

	testCommChestCard = new CommunityChest(6);
	assertEquals(testCommChestCard.getType(), 6);
	assertEquals(testCommChestCard.getText(), "It is your birthday - Collect $10 from each player");
 

	testCommChestCard = new CommunityChest(7);
	assertEquals(testCommChestCard.getType(), 7);
	assertEquals(testCommChestCard.getText(), "Grand Opera Night – collect $50 from every player for opening night seats");

	testCommChestCard = new CommunityChest(8);
	assertEquals(testCommChestCard.getType(), 8);
	assertEquals(testCommChestCard.getText(), "Income Tax refund – collect $20");

	testCommChestCard = new CommunityChest(9);
	assertEquals(testCommChestCard.getType(), 9);
	assertEquals(testCommChestCard.getText(), "Life Insurance Matures – collect $100");

	testCommChestCard = new CommunityChest(10);
	assertEquals(testCommChestCard.getType(), 10);
	assertEquals(testCommChestCard.getText(), "Pay Hospital Fees of $100");

	testCommChestCard = new CommunityChest(11);
	assertEquals(testCommChestCard.getType(), 11);
	assertEquals(testCommChestCard.getText(), "Pay School Fees of $50");

	testCommChestCard = new CommunityChest(12);
	assertEquals(testCommChestCard.getType(), 12);
	assertEquals(testCommChestCard.getText(), "Receive $25 Consultancy Fee");

	testCommChestCard = new CommunityChest(13);
	assertEquals(testCommChestCard.getType(), 13);
	assertEquals(testCommChestCard.getText(), "You are assessed for street repairs – $40 per house, $115 per hotel");

	testCommChestCard = new CommunityChest(14);
	assertEquals(testCommChestCard.getType(), 14);
	assertEquals(testCommChestCard.getText(), "You have won second prize in a beauty contest– collect $10");

	testCommChestCard = new CommunityChest(15);
	assertEquals(testCommChestCard.getType(), 15);
	assertEquals(testCommChestCard.getText(), "You inherit $100");

	testCommChestCard = new CommunityChest(16);
	assertEquals(testCommChestCard.getType(), 16);
	assertEquals(testCommChestCard.getText(), "From sale of stock you get $50");

	testCommChestCard = new CommunityChest(17);
	assertEquals(testCommChestCard.getType(), 17);
	assertEquals(testCommChestCard.getText(), "Holiday Fund matures - Receive $100");

	// Make sure un-allowed card values don't affect text, etc.
	testCommChestCard = new CommunityChest(18);
	assertEquals(testCommChestCard.getType(), 18);
	assertEquals(testCommChestCard.getText(), null);

	testCommChestCard = new CommunityChest(-1);
	assertEquals(testCommChestCard.getType(), -1);
	assertEquals(testCommChestCard.getText(), null);

	testCommChestCard = new CommunityChest(0);
	assertEquals(testCommChestCard.getType(), 0);
	assertEquals(testCommChestCard.getText(), null);
    }
}