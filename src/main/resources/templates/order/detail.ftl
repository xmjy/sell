<html>
<#include "../common/header.ftl">
<body>
    <div id="wrapper" class="toggled">

        <#-- 边栏的siderbar-->
        <#include "../common/nav.ftl">
        <#-- 订单详情页-->
        <div class="container">
            <div class="row clearfix">
        <#-- 只显示订单id,订单总金额-->
        <div class="col-md-4 column">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>
                        订单id
                    </th>
                    <th>
                        订单总金额
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        ${orderDTO.getOrderId()}
                    </td>
                    <td>
                        ${orderDTO.getOrderAmount()}
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

        <#-- 订单详情 -->
        <div class="col-md-12 column">
          <table class="table table-bordered">
            <thead>
            <tr>
                <th>
                    商品id
                </th>
                <th>
                    商品名称
                </th>
                <th>
                    价格
                </th>
                <th>
                    数量
                </th>
                <th>
                    总额
                </th>
            </tr>
            </thead>
            <#list orderDTO.getOrderDetailList() as orderDetail>
                <tbody>
                <tr>
                    <td>
                        ${orderDetail.getProductId()}
                    </td>
                    <td>
                        ${orderDetail.getProductName()}
                    </td>
                    <td>
                        ${orderDetail.getProductPrice()}
                    </td>
                    <td>
                        ${orderDetail.getProductQuantity()}
                    </td>
                    <td>
                        ${orderDetail.getProductQuantity()* orderDetail.getProductPrice()}
                    </td>
                </tr>
                </tbody>
            </#list>
        </table>
        </div>

        <#-- 操作订单-->
        <div class="col-md-12 column">
            <#if orderDTO.orderStatusEnum.getMessage() == "新订单">
            <a href="/sell/seller/order/finish?orderId=${orderDTO.getOrderId()}&page=${currentPage}&size=${size}#" type="button" class="btn btn-default btn-success">完结订单</a>
            <a href="/sell/seller/order/cancel?orderId=${orderDTO.getOrderId()}&page=${currentPage}&size=${size}#" type="button" class="btn btn-default btn-danger">取消订单</a>
            </#if>
        </div>


        </div>
        </div>
    </div>
</body>
</html>