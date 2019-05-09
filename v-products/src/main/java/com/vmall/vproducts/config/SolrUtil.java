package com.vmall.vproducts.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author：chengang
 * @version：1.0
 */

public class SolrUtil {

    private static SolrClient client;

    public static Map<String, Object> SolrName(String q, Integer page, Integer size) throws IOException, SolrServerException {
        SolrQuery params = new SolrQuery();

        // 查询条件
        params.set("q", q);

        // 排序
        params.addSort("vProductId", SolrQuery.ORDER.desc);
        // 分页
        params.setStart(page);
        params.setRows(size);
        // 默认域
        params.set("df", "vProductName");

        /*
        // 只查询指定域
        params.set("fl", "id,text");
        // 开启高亮
        params.setHighlight(true);
        // 设置前缀
        params.setHighlightSimplePre("<span style='color:red'>");
        // 设置后缀
        params.setHighlightSimplePost("</span>");
        */

        // solr数据库是 vmall
        QueryResponse queryResponse = client.query("vmall", params);
        SolrDocumentList results = queryResponse.getResults();

        // 数量，分页用
        // JS 使用 size=MXA 和 data.length 即可知道长度了
        long total = results.getNumFound();

        // 获取高亮显示的结果, 高亮显示的结果和查询结果是分开放的
        //Map<String, Map<String, List<String>>> highlight = queryResponse.getHighlighting(
        Map<String, Object> map = new HashMap<String, Object>();
        //获取数据条数
        map.put("total", total);
        //map.put("data", highlight);
        map.put("result",results);
        return map;
    }
}
