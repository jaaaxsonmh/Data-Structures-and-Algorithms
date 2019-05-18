package src.assignment3;

public class Room {

    private boolean northDoorOpen;
    private boolean eastDoorOpen;
    private boolean southDoorOpen;
    private boolean westDoorOpen;

    public Room() {

    }

    public void openDoor(Direction door) {
        switch (door) {
            case NORTH:
                northDoorOpen = true;
                break;
            case SOUTH:
                southDoorOpen = true;
                break;
            case EAST:
                eastDoorOpen = true;
                break;
            case WEST:
                westDoorOpen = true;
                break;
        }
    }

    public boolean hasOpenDoor() {
        return northDoorOpen || eastDoorOpen || southDoorOpen || westDoorOpen;
    }

    public boolean isDoorOpen(Direction door) {
        switch (door) {
            case NORTH:
                return northDoorOpen;
            case SOUTH:
                return southDoorOpen;
            case EAST:
                return eastDoorOpen;
            case WEST:
                return westDoorOpen;
            default:
                return false;
        }
    }
}