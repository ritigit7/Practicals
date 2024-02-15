import java.util.HashMap;
// import java.util.Map.Entry;

public class Temp {
    public static void main(String[] args) throws ClassNotFoundException {
        // Class.forName("oracle.jdbc.driver.OracleDriver");
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 20);
        map.put(6, 2);

        System.out.println(map.keySet());
        System.out.println(map.values());

        System.out.println(map.values().toArray()[0]);
        // for (Entry<Integer, Integer> entry : map.entrySet()) {
        // System.out.println(entry.);
        // }

    }
}
