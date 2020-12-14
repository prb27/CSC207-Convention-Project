package Gateways.Interfaces;

import org.bson.Document;

import java.util.List;
import java.util.Map;

public interface IAdminDatabase {

    Map<String, List<String>> getAdmin();

}