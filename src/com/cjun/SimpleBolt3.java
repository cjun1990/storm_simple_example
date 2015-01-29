package com.cjun;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import com.esotericsoftware.minlog.Log;

/**
 * �����緢�ڵ�(Spout)���͵����ݽ��м򵥵Ĵ���󣬷����ȥ��
 * 
 * @author Administrator
 * 
 */
public class SimpleBolt3 extends BaseBasicBolt {

  @Override
  public void execute(Tuple input, BasicOutputCollector collector) {
    // System.out.println(input.getSourceStreamId() + "*****************************");
    try {
      // �õ�spout��ϢԴ���͵���Ϣ
      String msg = input.getString(0);
      // System.out.println(msg+"------------------------------3");
      if (msg != null) {
        // System.out.println("msg="+msg);
        collector.emit(new Values(msg + "ccccccccccccc"));
        Log.error("bolt3-----------log-------------------------------------------" + msg);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public void declareOutputFields(OutputFieldsDeclarer declarer) {
    declarer.declare(new Fields("info2"));
    System.out.println("log---------------------------����SimpleBolt3��declareOutputFields����");
    Log.error("log---------------------------����SimpleBolt3��declareOutputFields����");
  }

}
