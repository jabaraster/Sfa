/**
 * 
 */
package test;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * @author jabaraster
 */
@SuppressWarnings({ "static-method", "javadoc" })
public class PropertyTest {

    /**
     * @param pArgs 起動引数.
     * @throws Exception -
     */
    public static void main(final String[] pArgs) throws Exception {
        for (final PropertyDescriptor p : Introspector.getBeanInfo(ExBean.class).getPropertyDescriptors()) {
            System.out.println("---------"); //$NON-NLS-1$
            System.out.println(p.getReadMethod());
            System.out.println(p.getWriteMethod());
        }
    }

    public static class Bean {
        public int getInt() {
            return 0;
        }

        public boolean isPersisted() {
            return true;
        }
    }

    public static class ExBean extends Bean {
        @Override
        public int getInt() {
            return super.getInt();
        }

        public boolean getPersisted() {
            return super.isPersisted();
        }

        public String getString() {
            return null;
        }
    }
}
