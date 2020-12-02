/**
 * Unit test that runs all of the unit tests for the lab.
 */
import org.junit.runners.Suite;
import org.junit.runner.RunWith;

@RunWith(Suite.class)
@Suite.SuiteClasses({TestLinear.class, TestDouble.class, TestChained.class, TestLinkedList.class, TestArraySet.class})
public class TestSuite {
  //nothing
}