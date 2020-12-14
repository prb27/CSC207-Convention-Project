package Gateways;

import org.bson.Document;

import java.util.List;
import java.util.Map;

public interface ISpeakerDatabase {

    List<Map<String, List<String>>> getSpeakers();

}