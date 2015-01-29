package com.cjun;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.topology.TopologyBuilder;

/**
 * @author xuer
 * @date 2014-10-16 - 上午10:44:36
 * @Description storm入门的例子
 */
public class SimpleTopology {
  public static void main(String[] args) {
    try {
      // 实例化TopologyBuilder类。
      TopologyBuilder topologyBuilder = new TopologyBuilder();
      // 设置喷发节点并分配并发数，该并发数将会控制该对象在集群中的线程数
      topologyBuilder.setSpout("SimpleSpout", new SimpleSpout(), 4);
      // 设置数据处理节点并分配并发数。指定该节点接收喷发节点的策略为随机方式。
      topologyBuilder.setBolt("SimpleBolt", new SimpleBolt(), 2).shuffleGrouping("SimpleSpout");
      // shuffleGrouping传的参数是指定要从哪个地方接收消息源

      topologyBuilder.setBolt("SimpleBolt2", new SimpleBolt2(), 3).shuffleGrouping("SimpleBolt")
          .shuffleGrouping("SimpleSpout");

      topologyBuilder.setBolt("SimpleBolt3", new SimpleBolt3(), 3).shuffleGrouping("SimpleBolt");
      Config config = new Config();

      // .TOPOLOGY_DEBUG(setDebug), 当它被设置成true的话， storm会记录下每个组件所发射的每条消息
      // 这在本地环境调试topology很有用， 但是在线上这么做的话会影响性能
      config.setDebug(true);
      if (args != null && args.length > 0) {

        // TOPOLOGY_WORKERS(setNumWorkers) 定义你希望集群分配多少个工作进程给你来执行这个topology.,
        // topology里面的每个组件会被需要线程来执行
        config.setNumWorkers(2);
        StormSubmitter.submitTopology(args[0], config, topologyBuilder.createTopology());
      } else {
        // 这里是本地模式下运行的启动代码。
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
