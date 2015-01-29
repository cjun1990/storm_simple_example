package com.cjun;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.topology.TopologyBuilder;

/**
 * @author xuer
 * @date 2014-10-16 - ����10:44:36
 * @Description storm���ŵ�����
 */
public class SimpleTopology {
  public static void main(String[] args) {
    try {
      // ʵ����TopologyBuilder�ࡣ
      TopologyBuilder topologyBuilder = new TopologyBuilder();
      // �����緢�ڵ㲢���䲢�������ò�����������Ƹö����ڼ�Ⱥ�е��߳���
      topologyBuilder.setSpout("SimpleSpout", new SimpleSpout(), 4);
      // �������ݴ���ڵ㲢���䲢������ָ���ýڵ�����緢�ڵ�Ĳ���Ϊ�����ʽ��
      topologyBuilder.setBolt("SimpleBolt", new SimpleBolt(), 2).shuffleGrouping("SimpleSpout");
      // shuffleGrouping���Ĳ�����ָ��Ҫ���ĸ��ط�������ϢԴ

      topologyBuilder.setBolt("SimpleBolt2", new SimpleBolt2(), 3).shuffleGrouping("SimpleBolt")
          .shuffleGrouping("SimpleSpout");

      topologyBuilder.setBolt("SimpleBolt3", new SimpleBolt3(), 3).shuffleGrouping("SimpleBolt");
      Config config = new Config();

      // .TOPOLOGY_DEBUG(setDebug), ���������ó�true�Ļ��� storm���¼��ÿ������������ÿ����Ϣ
      // ���ڱ��ػ�������topology�����ã� ������������ô���Ļ���Ӱ������
      config.setDebug(true);
      if (args != null && args.length > 0) {

        // TOPOLOGY_WORKERS(setNumWorkers) ������ϣ����Ⱥ������ٸ��������̸�����ִ�����topology.,
        // topology�����ÿ������ᱻ��Ҫ�߳���ִ��
        config.setNumWorkers(2);
        StormSubmitter.submitTopology(args[0], config, topologyBuilder.createTopology());
      } else {
        // �����Ǳ���ģʽ�����е��������롣
        // config.setMaxTaskParallelism(1);
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("simple", config, topologyBuilder.createTopology());
        // cluster.killTopology("simple");
        // Thread.sleep(2000);
        // cluster.shutdown();
      }


    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
