import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

public class TestCases
{
   public static final double DELTA = 0.00001;

   /*
 *     * This test is just to get you started.
 *         */
   @Test
   public void testGetX()
   {
      assertEquals(1.0, new Point(1.0, 2.0).getX(), DELTA);
   }

   @Test
   public void testGetY()
   {
      assertEquals(4.0, new Point(0.0, 4.0).getY(), DELTA);
   }

   @Test
   public void testGetRadius()
   {
      assertEquals(5.0, new Point(3.0, 4.0).getRadius(), DELTA);
   }

   @Test
   public void testGetAngle1()
   {
      assertEquals(Math.PI/2, new Point(0.0, 1.0).getAngle(), DELTA);
   }

   @Test
   public void testGetAngle2()
   {
      assertEquals(Math.PI/4, new Point(1.0, 1.0).getAngle(), DELTA);
   }

   @Test
   public void testGetAngle3()
   {
      assertEquals(Math.PI*3/4, new Point(-1.0, 1.0).getAngle(), DELTA);
   }

   @Test
   public void testGetAngle4()
   {
      assertEquals(-Math.PI*3/4, new Point(-3.0, -3.0).getAngle(), DELTA);
   }

   @Test
   public void testRotate90()
   {
      Point newPoint = new Point(3.0, 1.0).rotate90();
      assertEquals(new Point(-1.0, 3.0).getX(), new Point(3.0, 1.0).rotate90().getX(), DELTA);     
      assertEquals(new Point(-1.0, 3.0).getY(), new Point(3.0, 1.0).rotate90().getY(), DELTA); 
   }

/*
 *     * The tests below here are to verify the basic requirements regarding
 *         * the "design" of your class.  These are to remain unchanged.
 *             */

   @Test
   public void testImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getX",
         "getY",
         "getRadius",
         "getAngle",
         "rotate90"
         );

      final List<Class> expectedMethodReturns = Arrays.asList(
         double.class,
         double.class,
         double.class,
         double.class,
         Point.class
         );

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0],
         new Class[0],
         new Class[0],
         new Class[0],
         new Class[0]
         );

      verifyImplSpecifics(Point.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   private static void verifyImplSpecifics(
      final Class<?> clazz,
      final List<String> expectedMethodNames,
      final List<Class> expectedMethodReturns,
      final List<Class[]> expectedMethodParameters)
      throws NoSuchMethodException
   {
      assertEquals("Unexpected number of public fields",
         0, Point.class.getFields().length);

      final List<Method> publicMethods = Arrays.stream(
         clazz.getDeclaredMethods())
            .filter(m -> Modifier.isPublic(m.getModifiers()))
            .collect(Collectors.toList());

      assertTrue("Unexpected number of public methods",
         expectedMethodNames.size()+1 >= publicMethods.size());

      assertTrue("Invalid test configuration",
         expectedMethodNames.size() == expectedMethodReturns.size());
      assertTrue("Invalid test configuration",
         expectedMethodNames.size() == expectedMethodParameters.size());

      for (int i = 0; i < expectedMethodNames.size(); i++)
      {
         Method method = clazz.getDeclaredMethod(expectedMethodNames.get(i),
            expectedMethodParameters.get(i));
         assertEquals(expectedMethodReturns.get(i), method.getReturnType());
      }
   }
}
