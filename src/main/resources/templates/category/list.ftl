<html>
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">

    <#-- 边栏的siderbar-->
    <#include "../common/nav.ftl">

    <#-- 主体内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">

                <#-- 遍历数据库订主表的所有数据-->
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>
                                类目id
                            </th>
                            <th>
                                类目名字
                            </th>
                            <th>
                                类目编号
                            </th>
                            <th>
                                创建时间
                            </th>
                            <th>
                                修改时间
                            </th>
                            <th>
                                操作
                            </th>
                        </tr>
                        </thead>
                        <#list productCategorys as productCategory>
                            <tbody>
                            <tr>
                                <td>
                                    ${productCategory.categoryId}
                                </td>
                                <td>
                                    ${productCategory.categoryName}
                                </td>
                                <td>
                                    ${productCategory.categoryType}
                                </td>
                                <td>
                                    ${productCategory.createTime}
                                </td>
                                <td>
                                    ${productCategory.updateTime}
                                </td>
                                <td>
                                    <a href = "/sell/seller/category/index?categoryId=${productCategory.categoryId}">修改</a>
                                </td>
                            </tr>
                            </tbody>
                        </#list>
                    </table>
                </div>

            </div>
        </div>
    </div>
</div>

</body>
</html>
