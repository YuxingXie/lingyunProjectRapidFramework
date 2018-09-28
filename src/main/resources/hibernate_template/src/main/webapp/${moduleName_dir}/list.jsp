<#include "/custom.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
<#assign pkJavaType = table.idColumn.javaType>
<#assign pkColumnName = table.idColumn.columnName?uncap_first>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.intelligent.business.web.action.basemanager.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@taglib prefix="p" uri="/pageTag"%>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<c:if test="${'$'}{path eq '/'}"><c:set var="path" value=""/></c:if>
<html>
	<head>
		<title></title>
		<meta http-equiv="cache-control" content="no-cache" /> 
		<script type="text/javascript" src="${'$'}{path}/scripts/jquery-1.8.3.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${'$'}{path}/styles/default.css" />
		<link rel="stylesheet" type="text/css" href="${'$'}{path}/styles/main.css" />
		<script type="text/javascript">
		
		${'$'}(document).ready(function(){
			${'$'}("[name='delete']").click(function(){
				var url=$(this).attr("url");
				if (confirm("确认删除吗？")){
					window.location.href=url;
				}
			});
		});
	</script>
</head>

    <body>
    <div class="pageTitle">您正在处理的业务是:标准物质进出仓管理--标准物质验收管理</div>
		<div class="list">
			<div class="listTopOperation">
				<div class="select">
    <form name="${classNameLower}SearchForm" id="${classNameLower}SearchForm" method="post" action="${'$'}{path}/${moduleName}/search.${springMVCUrlMapping}" >
        <div id="_list_Tbody" class="IUD" multi="true">
                                     <#list table.columns as column>
                                        <#if !column.pk>
                                            <label>${column.columnAlias}</label>:
                                            <input type="text" value="${"$"}{param.${column.columnNameLower}}" name="${column.columnNameLower}"/>
                                             
                                        </#if>
                                    </#list>
                                    <input name="${classNameLower}SearchBtn" type="submit" class="btn" value="查询"/>

                             <button type="submit">查询</button>
							
							<a href="create_input.ac">标准物质验收增加</a>
							</div>
                            
                      
				</form>
				</div>
				<div id="_list_Tbody" class="IUD" multi="true">
				</div>
			</div>
        <table border="0" cellspacing="1" cellpadding="0" width="100%" id="userTable">

            <tr>
                <td class="line">序号</td>
                <#list table.columns as column>
                <#if !column.pk>
                    <td class="line">${column.columnAlias}</td>
                </#if>
                </#list>
                    <td class="line">操作</td>
            </tr>
            <c:forEach var="${classNameLower}" items="${'$'}{page.result}" varStatus="status">
                <tr class="line1">
                    <td class="line1">${"$"}{status.count}<input type="checkbox" name='items'  value='${"$"}{${classNameLower}.${pkColumnName}}'/></td>
            <#list table.columns as column>
                <#if !column.pk>
                    <td class="line1">${"$"}{${classNameLower}.${column.columnNameLower}}</td>
                </#if>
            </#list>
                    <td class="line1">
                        <a href="javascript:void(0);" name="delete" url="<%=request.getContextPath()%>/${moduleName}/${"$"}{${classNameLower}.${pkColumnName}}?_method=delete">删除</a>
                        <a href="<%=request.getContextPath()%>/${moduleName}/${"$"}{${classNameLower}.${pkColumnName}}">修改</a>
                    </td>
                </tr>
            </c:forEach>
                <tr style="background-color: rgb(255, 255, 255);">
						<td align="center"  class="list_foot" colspan="${table.columns?size}" >
                        <p:PageTag
                                isDisplayGoToPage="true" isDisplaySelect="false"
                                totalPages='${"$"}{page.lastPageNumber}'
                                currentPage='${"$"}{empty param.page ? 1 : param.page}'
                                totalRecords='${"$"}{page.totalCount}'
                                ajaxUrl='${"$"}{path}/${moduleName}/list.${springMVCUrlMapping}'
                                frontPath='' displayNum='5' />
                    </td>
                   
                </tr>
        </table>
<div class="listBottomOperation"></div> 
		</div>
	</body>
</html>
