package com.kitezeng.springbootmall.dao.impl;

import com.kitezeng.springbootmall.dao.OrderDao;
import com.kitezeng.springbootmall.dto.OrderQueryParams;
import com.kitezeng.springbootmall.model.Order;
import com.kitezeng.springbootmall.model.OrderItem;
import com.kitezeng.springbootmall.rowmapper.OrderItemRowMapper;
import com.kitezeng.springbootmall.rowmapper.OrderRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<OrderItem> getOrderItemByOrderId(Integer orderId) {
        String sql = "select oi.order_item_id,oi.order_id,oi.product_id,oi.quantity,oi.amount,p.product_name,p.image_url "+
                "from order_item as oi "+
                "left join product as p ON oi.product_id = p.product_id "+
                "where oi.order_id = :orderId";

        Map<String,Object> map = new HashMap<>();
        map.put("orderId",orderId);
        List<OrderItem> orderItemList = namedParameterJdbcTemplate.query(sql,map,new OrderItemRowMapper());
        return orderItemList;
    }

    @Override
    public Order getOrderById(Integer orderId) {
        String sql = "select order_id, user_id,total_amount, created_date, last_modified_date "+
                "from `order` where order_id = :orderId";

        Map<String,Object> map = new HashMap<>();
        map.put("orderId",orderId);
        List<Order> orderList = namedParameterJdbcTemplate.query(sql,map,new OrderRowMapper());
        if(orderList.size()>0){
            return orderList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public Integer createOrder(Integer userId, Integer totalAmount) {
        String sql = "insert into `order`(user_id, total_amount, created_date, last_modified_date) "+
               "values (:userId, :totalAmount, :createdDate, :lastModifiedDate)";
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("totalAmount",totalAmount);
        Date now = new Date();
        map.put("createdDate",now);
        map.put("lastModifiedDate",now);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);
        int orderId = keyHolder.getKey().intValue();
        return orderId;
    }

    @Override
    public void deleteOrderByOrderId(Integer orderId) {
        String sql = "delete from `order` where order_id = :orderId";

        Map<String,Object> map = new HashMap<>();
        map.put("orderId",orderId);

        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public void createOrderItems(Integer orderId, List<OrderItem> orderItemList) {
        String sql = "insert into order_item(order_id, product_id, quantity, amount) " +
                "values (:orderId, :productId, :quantity, :amount)";
        MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[orderItemList.size()];
        for(int i = 0;i<orderItemList.size();i++){
            OrderItem orderItem = orderItemList.get(i);
            parameterSources[i] = new MapSqlParameterSource();
            parameterSources[i].addValue("orderId",orderId);
            parameterSources[i].addValue("productId",orderItem.getProductId());
            parameterSources[i].addValue("quantity",orderItem.getQuantity());
            parameterSources[i].addValue("amount",orderItem.getAmount());
        }

        namedParameterJdbcTemplate.batchUpdate(sql, parameterSources);
    }

    @Override
    public List<Order> getOrders(OrderQueryParams orderQueryParams) {
       String sql = "select order_id, user_id, total_amount,created_date, last_modified_date from `order` where 1=1";
       Map<String,Object> map = new HashMap<>();

       //查詢條件
        sql = addFilteringSql(sql,map,orderQueryParams);

        //排序
        sql = sql + " order by created_date DESC";

        //分頁
        sql = sql + " LIMIT :limit offset :offset";
        map.put("limit",orderQueryParams.getLimit());
        map.put("offset",orderQueryParams.getOffset());

        List<Order> orderList = namedParameterJdbcTemplate.query(sql,map,new OrderRowMapper());

        return orderList;
    }

    @Override
    public Integer countOrder(OrderQueryParams orderQueryParams) {
        String sql = "select count(*) from `order` where 1=1";
        Map<String,Object> map = new HashMap<>();

        //查詢條件
        sql = addFilteringSql(sql,map,orderQueryParams);

        Integer total = namedParameterJdbcTemplate.queryForObject(sql,map,Integer.class);

        return total;
    }

    private String addFilteringSql(String sql,Map<String,Object> map, OrderQueryParams orderQueryParams){
        if(orderQueryParams.getUserId() != null){
            sql = sql + " and user_id = :userId";
            map.put("userId",orderQueryParams.getUserId());
        }
        return sql;
    }
}
