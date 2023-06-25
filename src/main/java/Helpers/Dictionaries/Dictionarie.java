package Helpers.Dictionaries;

import java.util.HashMap;
import java.util.Map;

public class Dictionarie {
    Map<Integer, Character> dec2alf;
    Map<Character, Integer> enc2alf;

    public Map<Integer, Character> getDecriptDict(){
        String az = "abcdefghijklmnopqrstuvwxyz";
        Map<Integer, Character> dec2alf = new HashMap<>();
        for(int i = 0; i<26; i++){
            dec2alf.put(i, az.charAt(i));
        }

        return dec2alf;
    }

    public Map<Character, Integer> getEncriptDict(){
        String az = "abcdefghijklmnopqrstuvwxyz";
        Map<Character, Integer> enc2alf = new HashMap<>();
        for(int i = 0; i<26; i++){
            enc2alf.put(az.charAt(i), i);
        }

        return enc2alf;
    }

    public <K,V> void printMap(Map<K,V> map){
        for(Map.Entry<K, V> entry : map.entrySet()){
            System.out.println(entry.getKey()+": "+entry.getValue());
        }
    }

    public Dictionarie(){
        this.dec2alf = getDecriptDict();
        this.enc2alf = getEncriptDict();
    }
}
