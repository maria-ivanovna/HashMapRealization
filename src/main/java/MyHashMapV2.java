public class MyHashMapV2 {
    private Pair [] table;
    private static int count = 0;
    private int sizeCount = 0;
    private int setSize;

    public int getSetSize() {
        return setSize;
    }

    public MyHashMapV2() {
        this.setSize = 16;
        table = new Pair[setSize];
    }
    public MyHashMapV2(int setSize) {
        this.setSize = setSize;
        table = new Pair[setSize];
    }
    /* хэш-функция */
    private int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    /* возвращает номер головы по значению хэш-функции */
    private int index(int hash) {
        return Math.abs(hash) % setSize;
    }

    /* Добавляет пару в множество */
    public void put(int key, double value){
        try{
            for (count = index(hash(key)); ; count++) {
                if(count == setSize) count = 0;
                if(table[count].isEmpty()) {
                    /*
                    Заготовка на DEELETE

                    table[count] = new Pair(key, value);
                    table[count].becomeFull();
                    sizeCount++*/

//                    throw new NullPointerException("Colizion");
                    return;
                }
                if(table[count].getKey() == key) {
                    table[count] = new Pair(key, value);
                    table[count].becomeFull();
                    return;
                }
            }
        }catch (NullPointerException ex){
            table[count] = new Pair(key, value);
            table[count].becomeFull();
            sizeCount++;
        }
    }

    /* Проверяет наличие пары с ключем key */
    public boolean isContainsKey(int key){
        boolean lock = false;
        try {
            for (int i = index(hash(key)); ; i++) {
                if (i == setSize && !lock){
                    i = 0;
                    lock = true;
                }
                if (table[i].getKey() == key) return true;
            }
        }catch (NullPointerException ex){
            return false;
        }
        catch (ArrayIndexOutOfBoundsException ex){
            return false;
        }
    }

    /* Извлекает значение */
    public double get(int key) {
            for (int i = index(hash(key)); ; i++) {
                if (i == setSize) i = 0;
                //if (!isContainsKey(key)) throw new RuntimeException("No such key!");
                if (table[i].getKey() == key) {
                    try {
                        return table[i].getValue();
                    }catch (NullPointerException ex){
                        System.out.println("No such key");
                    }
                }
            }
    }
    public int quantityElements(){
        return sizeCount;
    }
}
