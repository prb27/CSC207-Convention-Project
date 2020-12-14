package Gateways.Interfaces;

import org.bson.Document;

import java.util.*;

public interface IMultiEventDatabase {

    public List<Map<String, List<String>>> getMultiSpeakerEventList();

}