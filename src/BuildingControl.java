import java.util.ArrayList;

public class BuildingControl extends Thread {

    private Cabin[] cabins;
    DoorControl[][] doors;
    ArrayList<Integer> notifyList;


    public static void main(String[] args) throws InterruptedException {
        BuildingControl b = new BuildingControl();
        b.start();

        GUI g = new GUI();
        g.instantiate(args);
    }


    public void initialize() {
        cabins = new Cabin[4]; // run

        cabins[0] = new Cabin(0);
        cabins[1] = new Cabin(1);
        cabins[2] = new Cabin(2);
        cabins[3] = new Cabin(3);

        MapView.getInstance().setElevators(cabins);

        doors = new DoorControl[10][2];
        for (int i = 0; i < doors.length; i++) {
            for (int j = 0; j < doors[0].length; j++) {
                doors[i][j] = new DoorControl();
            }
        }
        notifyList = new ArrayList<Integer>();

    }

    boolean stoppedCabin(int id) {
        if (cabins[id].getCabinState() == States.CabinStates.Stopped) return true;
        return false;
    }

    void openDoor(int floor, int id) {
        doors[floor][id].start();
    }


    @Override
    public void run() {
        //Cabin ID has to be 0,1,2,3. GUI uses this to recognize the cabins.
        initialize();


        cabins[0].start();

        cabins[0].addStop(5);
        cabins[0].addStop(6);
        cabins[0].addStop(7);


        cabins[1].start();

        cabins[1].addStop(2);
        cabins[1].addStop(4);
        cabins[1].addStop(6);


        cabins[2].start();

        cabins[2].addStop(2);
        cabins[2].addStop(6);
        cabins[2].addStop(9);


        cabins[3].start();

        cabins[3].addStop(1);
        cabins[3].addStop(3);
        cabins[3].addStop(4);

        System.out.println("added everything");

    }

}
