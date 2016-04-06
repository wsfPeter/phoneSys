package com.cienet.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;

public class WordAnalyze {

    private List<String> list;
    // 公共map
    private Map<String, List<String>> map;
    // 单选题
    private Map<String, List<String>> mapSingle;

    // 多选题map
    private Map<String, List<String>> mapMultiple;

    // 判断题map
    private Map<String, List<String>> mapJudge;

    public void analyze(InputStream stream) {
        // word文档路径
        try {
            // 创建存储word文档的对象
            HWPFDocument doc = new HWPFDocument(stream);
            // 用来获得word文档内容
            Range range = doc.getRange();
            // 文档段落数目
            int paragraphCount = range.numParagraphs();
            // 遍历段落读取数据
            int item = 1;
            int var = 0;
            for (int i = 0; i < paragraphCount; i++) {
                Paragraph pph = range.getParagraph(i);
                if (item == 1) {
                    list = new ArrayList<String>();
                }
                if (pph.text().trim().equals(Constants.Single_Choice_NAME)) {
                    map = new HashMap<String, List<String>>();
                    mapSingle = map;
                    continue;
                }
                if (pph.text().trim().equals(Constants.MULTIPLE_CHOICE_NAME)) {
                    map = new HashMap<String, List<String>>();
                    mapMultiple = map;
                    var = 0;
                    continue;
                }
                if (pph.text().trim().equals(Constants.JUDGE_NAME)) {
                    map = new HashMap<String, List<String>>();
                    mapJudge = map;
                    var = 0;
                    continue;
                }
                if (pph.text().trim().equals("")) {
                    item = 1;
                    map.put("" + var, list);
                    var++;
                    continue;
                }
                list.add(pph.text());
                item++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, List<String>> getMapJudge() {
        return mapJudge;
    }

    public Map<String, List<String>> getMapMultiple() {
        return mapMultiple;
    }

    public Map<String, List<String>> getMapSingle() {
        return mapSingle;
    }

    public void setMapJudge(Map<String, List<String>> mapJudge) {
        this.mapJudge = mapJudge;
    }

    public void setMapMultiple(Map<String, List<String>> mapMultiple) {
        this.mapMultiple = mapMultiple;
    }

    public void setMapSingle(Map<String, List<String>> mapSingle) {
        this.mapSingle = mapSingle;
    }
}
