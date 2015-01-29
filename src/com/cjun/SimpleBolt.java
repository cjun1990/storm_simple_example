package com.cjun;

import java.util.Map;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import com.esotericsoftware.minlog.Log;

/**
 * 接收喷发节点(Spout)发送的数据进行简单的处理后，发射出去。
 * 
 * @author Administrator
 * 
 */
public class SimpleBolt extends BaseBasicBolt {

  private static final long serialVersionUID = 1L;
  private Integer count = 0;

  @Override
  public void prepare(Map stormConf, TopologyContext context) {
    System.out.println("bolt初始化");
    Log.error("log------------------------------bolt初始化");
  }


  @Override
  public void execute(Tuple input, BasicOutputCollector collector) {
    // System.out.println(input.getSourceStreamId() + "----------------------------");
    if (count == 0) {
      // try {
      // // Thread.sleep(5000);
      // } catch (InterruptedException e) {
      // // TODO Auto-generated catch block
      // e.printStackTrace();
      // }
    }
    count++;
    try {
      // 得到spout信息源发送的消息
      String msg = input.getString(1);
      System.out.println(count + "！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
      System.out.println(msg.isEmpty() + "pppppppppppppppppppppppp");
      if (msg != null) {
        // System.out.println("msg="+msg);
        collector.emit(new Values(msg + "msg is processed!aaaaaaaaaaaaaa"));
        Log.error("bolt1-----------log-------------------------------------------" + msg);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public void declareOutputFields(OutputFieldsDeclarer declarer) {
    declarer.declare(new Fields("info"));
    System.out.println("log---------------------------调用SimpleBolt的declareOutputFields方法");
    Log.error("log---------------------------调用SimpleBolt的declareOutputFields方法");
  }
}
