import java.util.Random;
import java.util.concurrent.Semaphore;

class Ship {
    private String type;
    private int capacity;

    public Ship(String type, int capacity) {
        this.type = type;
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }
}

class Dock {
    private String name;

    public Dock(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class ShipLoader {
    private static final int MAX_LOADING_TIME = 100;

    private Semaphore tunnelSemaphore;

    public ShipLoader(int tunnelCapacity) {
        tunnelSemaphore = new Semaphore(tunnelCapacity);
    }

    private Ship generateShip() {
        Random random = new Random();
        String[] shipTypes = {"Type1", "Type2", "Type3"};
        int[] capacities = {100, 200, 300};

        int randomIndex = random.nextInt(shipTypes.length);
        String type = shipTypes[randomIndex];
        int capacity = capacities[randomIndex];

        return new Ship(type, capacity);
    }

    private Dock getDockForShip(Ship ship) {
        String shipType = ship.getType();
        Dock dock;

        if (shipType.equals("Type1")) {
            dock = new Dock("Dock1");
        } else if (shipType.equals("Type2")) {
            dock = new Dock("Dock2");
        } else if (shipType.equals("Type3")) {
            dock = new Dock("Dock3");
        } else {
            dock = new Dock("DefaultDock");
        }

        return dock;
    }

    public void loadShip() {
        Ship ship = generateShip();
        Dock dock = getDockForShip(ship);

        try {
            tunnelSemaphore.acquire();
            System.out.println("Ship of type " + ship.getType() + " with capacity " + ship.getCapacity() + " arrived at dock " + dock.getName());

            int loadingTime = ship.getCapacity() * MAX_LOADING_TIME;
            System.out.println("Loading ship at dock " + dock.getName() + " for " + loadingTime + " milliseconds");
            Thread.sleep(loadingTime);

            System.out.println("Ship loaded at dock " + dock.getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            tunnelSemaphore.release();
        }
    }

    public void startLoading() {
        while (true) {
            loadShip();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        int tunnelCapacity = 3;
        ShipLoader shipLoader = new ShipLoader(tunnelCapacity);
        shipLoader.startLoading();
    }
}
