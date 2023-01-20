package lab3;
import java.util.Objects;
public class Location
{
    public int xCoord;
    public int yCoord;

    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    public Location()
    {
        this(0, 0);
    }
    //Указывает, является ли другой объект "равным" этому.
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if (!(obj instanceof Location)){
            return false;
        }
        Location location = (Location) obj;
        return xCoord == location.xCoord && yCoord == location.yCoord;
    }
    //вычисляет значение для конкретного элемента класса
    public int hashCode() {
        return Objects.hash(xCoord, yCoord);
    }
}