package lab3;

import java.util.*;

public class AStarState
{
    private Map2D map;
    //Инициализация открытых вершин
    private Map<Location, Waypoint> open_waypoints
    = new HashMap<Location, Waypoint> ();
    //Инициализация закрытых вершин
    private Map<Location, Waypoint> closed_waypoints
    = new HashMap<Location, Waypoint> ();

    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }
    public Map2D getMap()
    {
        return map;
    }
    public Waypoint getMinOpenWaypoint()
    {
        // Если открытых вершин нет, возвращает null.
        if (numOpenWaypoints() == 0)
            return null;
        // Инициализирует набор всех открытых вершин, 
        // переборирает набор вершин и храненит наилучшую вершину в переменной 
        // и стоимость этой вершины.
        Set open_waypoint_keys = open_waypoints.keySet();
        Iterator i = open_waypoint_keys.iterator();
        Waypoint best = null;
        float best_cost = Float.MAX_VALUE;
        
        // Сканирует все открытые вершины.
        while (i.hasNext())
        {
            // Сохраняет текущее местоположение.
            Location location = (Location)i.next();
            // Сохраняет текущую Вершину.
            Waypoint waypoint = open_waypoints.get(location);
            // Сохраняет общую стоимость для текущей вершины.
            float waypoint_total_cost = waypoint.getTotalCost();
            // Если стоимость для текущей вершины лучше (ниже)
            // чем сохраненная стоимость сохраненной вершины
            // сохраненная вершина заменяется
            if (waypoint_total_cost < best_cost)
            {
                best = open_waypoints.get(location);
                best_cost = waypoint_total_cost;
            }
            
        }
        // Возвращает вершину (с меньшей стоимостью).
        return best;
    }
    public boolean addOpenWaypoint(Waypoint newWP)
    {
        // Находит местоположение новой вершины.
        Location location = newWP.getLocation();
        // Проверяет, есть ли уже новая вершина в местоположении.
        if (open_waypoints.containsKey(location))
        {
            //Проверяет если точка есть лучшая ли это точка
            Waypoint current_waypoint = open_waypoints.get(location);
            if (newWP.getPreviousCost() < current_waypoint.getPreviousCost())
            {
                open_waypoints.put(location, newWP);
                return true;
            }
            return false;
        }
        open_waypoints.put(location, newWP);
        return true;
    }
    //Возвращает количество точек в наборе открытых вершин
    public int numOpenWaypoints()
    {
        return open_waypoints.size();
    }

    // Перемещает открытую вершину в закрытые
    public void closeWaypoint(Location loc)
    {
        Waypoint waypoint = open_waypoints.remove(loc);
        closed_waypoints.put(loc, waypoint);
    }
    public boolean isLocationClosed(Location loc)
    {
        return closed_waypoints.containsKey(loc);
    }
}