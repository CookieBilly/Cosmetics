

package ws.billy.CookieGadgets.utils;

public enum GInventory
{
    LAY_OUT_21("LAY_OUT_21", 0, new int[] { 10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34 }), 
    LAY_OUT_27("LAY_OUT_27", 1, new int[] { 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35 }), 
    LAY_OUT_36_I("LAY_OUT_36_I", 2, new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35 }), 
    LAY_OUT_36_II("LAY_OUT_36_II", 3, new int[] { 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44 });
    
    private int[] layOut;
    
    private GInventory(final String name, final int ordinal, final int[] layOut) {
        this.layOut = layOut;
    }
    
    public int[] getLayOut() {
        return this.layOut;
    }
    
    public static int getMaxPagesAmount(final int n, final int n2) {
        if (n2 == 0) {
            return 1;
        }
        if (n2 % n == 0) {
            return n2 / n;
        }
        return (int)Math.floor(n2 / n * 100.0) / 100 + 1;
    }
}
