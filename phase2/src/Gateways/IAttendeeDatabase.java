package Gateways;

import java.util.List;
import java.util.Map;

public interface IAttendeeDatabase {

    List<Map<String, List<String>>> getAttendees();

}