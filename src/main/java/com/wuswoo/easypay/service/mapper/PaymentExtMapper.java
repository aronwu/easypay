package com.wuswoo.easypay.service.mapper;

import com.wuswoo.easypay.service.model.Refund;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by wuxinjun on 16/11/2.
 */

public interface PaymentExtMapper extends PaymentResultMapper {

    @Select("SELECT order_id FROM payment WHERE payment_code=#{paymentCode} ORDER BY id DESC LIMIT 1")
    String getOrderIdByPaymentCode(@Param("paymentCode") String paymentCode);

    @Select("SELECT order_id FROM payment WHERE id = #{paymentId}")
    String getOrderIdByPaymentId(@Param("paymentId") Long paymentId);

    @Select("SELECT refund.* FROM refund, refund_result WHERE refund.id = refund_result.refund_id"
        + " AND refund_result.refund_code = #{refundCode} LIMIT 1")
    Refund getRefunByRefundCode(@Param("refundCode") String refundCode);

}
