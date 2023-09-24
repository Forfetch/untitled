package ships;

import ships.types.Size;
import ships.types.Type;

public class Ship {
    private int count;
    private Size size;
    private Type type;

    public Ship(Size syze, Type type) {
        this.size = syze;
        this.type = type;
    }
    public void add (int count){
        this.count += count;
    }
    public boolean countCheck () {
        if (count >= size.getValue()) {
            return false;
        }
        return true;
    }

    public int getCount() {
        return count;
    }

    public Size getSize() {
        return size;
    }

    public Type getType() {
        return type;
    }
}
