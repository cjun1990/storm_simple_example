package com.cjun;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import com.esotericsoftware.minlog.Log;

/**
 * Spout�𵽺���繵ͨ�����ã������Դ�һ�����ݿ��а���ĳ�ֹ���ȡ���ݣ�Ҳ���Դӷֲ�ʽ������ȡ����
 * 
 */
public class SimpleSpout extends BaseRichSpout {
  // ���ڷ������ݵĹ�����
  private SpoutOutputCollector collector;

  // ģ�������
  private static String[] info = new String[] {
      // "comaple\t,12424,44w46,654,12424,44w46,654,",
      "lisi\t,435435,6537,12424,44w46,654,",
      // "lipeng\t,45735,6757,12424,44w46,654,",
      // "hujintao\t,45735,6757,12424,44w46,654,",
      // "jiangmin\t,23545,6457,2455,7576,qr44453",
      // "beijing\t,435435,6537,12424,44w46,654,",
      // "xiaoming\t,46654,8579,w3675,85877,077998,",
      // "xiaozhang\t,9789,788,97978,656,345235,09889,",
      // "ceo\t,46654,8579,w3675,85877,077998,",
      // "cto\t,46654,8579,w3675,85877,077998,",
      "zhansan\t,46654,8579,w3675,85877,077998,"};
  private static ArrayList list = new ArrayList();
  static {
    list.add("zhansan\t,46654,8579,w3675,85877,077998,");
    list.add("zhansan1\t,46654,8579,w3675,85877,077998,");
    list.add("zhansan1\t,46654,8579,w3675,85877,077998,");
    list.add("zhansan\t,46654,8579,w3675,85877,077998,");
    list.add("zhansan1\t,46654,8579,w3675,85877,077998,");
    list.add("zhansan1\t,46654,8579,w3675,85877,077998,");
    list.add("zhansan\t,46654,8579,w3675,85877,077998,");
    list.add("zhansan1\t,46654,8579,w3675,85877,077998,");
  }
  Random random = new Random();

  // ��ʼ��collector
  @Override
  public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {

    this.collector = collector;
  }

  /**
   * ��SpoutTracker���б����ã� ÿ����һ�ξͿ�����storm��Ⱥ�з���һ�����ݣ�һ��tupleԪ�飩�� �÷����ᱻ��ͣ�ĵ���
   */
  @Override
  public void nextTuple() {
    try {

      if (list.size() > 0) {
        int i = random.nextInt(list.size());
        String msg = (String) list.get(i);// �������
        list.remove(i);
        // ���÷��䷽��
        collector.emit(new Values(msg, ""));
        // ģ��ȴ�100ms
        Thread.sleep(100);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * �����ֶ�id����id�ڼ�ģʽ��û���ô������ڰ����ֶη����ģʽ���кܴ���ô��� ��declarer�����кܴ����ã� ���ǻ����Ե���declarer.declareStream()��
   * ������stramId����id��������������Ӹ��ӵ������˽ṹ
   */
  @Override
  public void declareOutputFields(OutputFieldsDeclarer declarer) {
    declarer.declare(new Fields("msg", "ds"));
    System.out.println("log---------------------------����SimpleSpout��declareOutputFields����");
    Log.error("log---------------------------����SimpleSpout��declareOutputFields����");
    // collector.emit(new Values(msg));����Ҫ��Ӧ
  }

}
