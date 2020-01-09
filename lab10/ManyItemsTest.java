package lab10;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ManyItemsTest
{
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception
	{
		
	}
	
	
	@AfterAll
	static void tearDownAfterClass() throws Exception
	{
		
	}
	
	
	@Test
	void test()
	{
		
		ManyItems.main(new String[] {"D:\\.Moje\\Workspace\\code\\B.txt", "" });
		
	}
	
}
