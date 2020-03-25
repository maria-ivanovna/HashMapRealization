import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MyHashMapV2Test {
    private MyHashMapV2 myHMap;
    static int keys[] = new int[10000];
    static double values[] = new double[10000];

    public MyHashMapV2 getMyHMap() {
        return myHMap;
    }
    /*  Заполняет объект класса MyHashMapV2 значениями    */
    @BeforeAll
    void setMyHMap(){
        myHMap = new MyHashMapV2(10000);

        for (int i = 0; i<10000; i++){
            keys[i] = i;
            values[i] = (double) i + 100;
        }
        for(int i : keys){
            myHMap.put(keys[i], values[i]);
        }
    }

    /*  Проверка заменяемости элементов     */
    @Test
    void get1() {
        MyHashMapV2 myHashMapV2 = new MyHashMapV2();
        myHashMapV2.put(1, 11);
        myHashMapV2.put(2, 22);
        myHashMapV2.put(3, 33);
        myHashMapV2.put(2, 222);

        double expectedElement1 = 33;
        double actualElement1 = myHashMapV2.get(3);
        double expectedElement2 = 11;
        double actualElement2 = myHashMapV2.get(1);
        double expectedElement3 = 222;
        double actualElement3 = myHashMapV2.get(2);

        assertEquals(expectedElement1, actualElement1);
        assertEquals(expectedElement2, actualElement2);
        assertEquals(expectedElement3, actualElement3);
    }
    /*  Проверка вставки и получения элемента   */
    @Test
    void get_put() {
        double actual[] = new double[10000];
        for(int i : keys){
            actual[i] = getMyHMap().get(i);
        }
        assertArrayEquals(values, actual);
    }
    /*  Проверка наличия ключа  */
    @Test
    void isContainsKey() {
        boolean expectedFalse = false;
        boolean actualFalse = getMyHMap().isContainsKey(-1);
        boolean expectedTrue = true;
        boolean actualTrue = getMyHMap().isContainsKey(9999);
        assertEquals(expectedFalse, actualFalse);
        assertEquals(expectedTrue, actualTrue);
    }
    @Test
        /*  Возврат полного размера объекта класса MyHashMapV2 */
    void sizeOfMyHashMap(){

        MyHashMapV2 autoSize = new MyHashMapV2();
        int expected4AutoSize = 16;
        int actual4AutoSize = autoSize.getSetSize();
        int expected4MySize = 10000;
        int actual4MySize = getMyHMap().getSetSize();

        assertEquals(expected4AutoSize, actual4AutoSize);
        assertEquals(expected4MySize, actual4MySize);
    }

    /*  Возврат к-ва элементов в объекте класса MyHashMapV2 */
    @Test
    void quantityElements() {
        int e = 10000;
        int a = getMyHMap().quantityElements();
        assertEquals(e, a);
    }
}