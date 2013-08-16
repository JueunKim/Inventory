/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.myClasses;

import java.awt.Component;
import java.awt.Container;

/**
 *
 * @author paul
 */
public class MyUtility {
    public static <T extends Container> T findParent(Component comp, Class<T> clazz)  {
      if (comp == null)
         return null;
      if (clazz.isInstance(comp))
         return (clazz.cast(comp));
      else
         return findParent(comp.getParent(), clazz);     
   }
}
