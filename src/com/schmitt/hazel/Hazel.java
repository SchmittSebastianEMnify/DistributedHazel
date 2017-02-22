package com.schmitt.hazel;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;


public class Hazel {

  private static int node = 1;

  public static void main(String[] args) throws Exception {

    if (args.length == 1) {
      try {
        node = Integer.valueOf(args[0]);
      } catch (NumberFormatException e) {
        System.err.println("##############################################");
        System.err.println("##                                          ##");
        System.err.println("## Only integer values allowed as parameter ##");
        System.err.println("##                                          ##");
        System.err.println("##############################################");
        System.exit(-1);
      }
    }

    int count = 0;
    HazelcastInstance instance = Hazelcast.newHazelcastInstance();

    System.out.println(instance.getName());

    IMap<String, String> list = instance.getMap("test");
    System.out.println("Node: " + node);
    while (true) {
      Thread.sleep(2500);
      String value = "Node:" + node + ":" + ++count;
      list.put(value, "Val:" + value);

      System.out.println("--------------------------------");

      System.out.println("Wrote: " + value);

      System.out.println();

      System.out.println("Node 1 from Node " + node + " : " + list.get("Node:1:1") + " : "
          + list.size());
      System.out.println("Node 1 from Node " + node + " : " + list.get("Node:1:2") + " : "
          + list.size());
      System.out.println("Node 1 from Node " + node + " : " + list.get("Node:1:3") + " : "
          + list.size());
      System.out.println("Node 1 from Node " + node + " : " + list.get("Node:1:4") + " : "
          + list.size());

      System.out.println();

      System.out.println("Node 2 from Node " + node + " : " + list.get("Node:2:1") + " : "
          + list.size());
      System.out.println("Node 2 from Node " + node + " : " + list.get("Node:2:2") + " : "
          + list.size());
      System.out.println("Node 2 from Node " + node + " : " + list.get("Node:2:3") + " : "
          + list.size());
      System.out.println("Node 2 from Node " + node + " : " + list.get("Node:2:4") + " : "
          + list.size());

      System.out.println();

      System.out.println("Node 3 from Node " + node + " : " + list.get("Node:3:1") + " : "
          + list.size());
      System.out.println("Node 3 from Node " + node + " : " + list.get("Node:3:2") + " : "
          + list.size());
      System.out.println("Node 3 from Node " + node + " : " + list.get("Node:3:3") + " : "
          + list.size());
      System.out.println("Node 3 from Node " + node + " : " + list.get("Node:3:4") + " : "
          + list.size());

      count = count % 3 + 1;
    }
  }
}
