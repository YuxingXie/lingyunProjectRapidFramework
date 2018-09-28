<#include "/custom.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
<#assign pkJavaType = table.idColumn.javaType>
<#assign pkColumnName = table.idColumn.columnName?uncap_first>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="p" uri="/pageTag"%>
<%@ include file="/commons/taglibs.jsp"%>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<c:if test="${"$"}{path eq '/'}"><c:set var="path" value=""/></c:if>
<jsp:useBean id="rmAcceptanceInfo" class="com.zn.persistence.RmAcceptanceInfo" scope="request"></jsp:useBean>
<html lang="true">
<head>
<title></title>
<link rel="stylesheet" type="text/css" href="${'$'}{path}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${'$'}{path}/styles/main.css" />
<script type="text/javascript" src="${'$'}{path}/scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${'$'}{path}/scripts/json2.js"></script>
<script type="text/javascript" src="${'$'}{path}/components/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	  $(document).ready(function(){
			
		});
</script>

</head>
<html>
    
    <body>
    <div class="pageTitle">您正在处理的业务是:标准物质进出仓管理--标准物质验收管理--标准物质验收</div>
	<div class="editTable">
	
    <form:form action="${"$"}{path}/${moduleName}/new.${springMVCUrlMapping}" method="post"  commandName="${classNameLower}" >
        <!--edit-->
        <table width="1050" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td width="1%" class="c4"><img src="images/k0.gif" width="13" height="37"></td>
                <td width="18%" class="c4" nowrap="nowrap"><div align="center">新增${tableLocalName}</div></td>
                <td width="6%" background="images/bg_k2.gif"><img src="images/k1.gif" width="39" height="37"></td>
                <td width="72%" background="images/bg_k2.gif"><div align="right">
                    <input type="submit" class="btn" value="保存">
                    <input type="button" class="btn" value="返回" onClick="history.back();">
                </div></td>
                <td width="3%" background="images/bg_k2.gif"><div align="right"><img src="images/k2.gif" width="17" height="37"></div></td>
            </tr>
        </table>
       
             <table width="80%" cellspacing="1" cellpadding="0" border="0">
                   <tbody>
                   <#list table.columns as column>
                   	<#if !column.pk >
                   	<#assign columnHasForeignKey="0"/>
                               <#list table.importedKeys.associatedTables?values as foreignKey>
                                   <#assign fkSqlTable = foreignKey.sqlTable>
                                    <#assign fkTable	= fkSqlTable.className>
                                    <#assign fkPojoClass = fkSqlTable.className>
                                    <#assign fkPojoClassVar = fkPojoClass?uncap_first>
                                   <#list foreignKey.parentColumns?values as fkColumn>
                                           <#if fkColumn?replace("_","")?lower_case==column.columnNameLower?lower_case>
                       <tr>
                       <td class="tdLeft">${column.columnAlias}<#if !column.nullable><font color="red">*</font></#if>:</td>
					<td class="tdRight"> 
                       <form:select path="${column.columnNameLower}">
                       	   <form:option value="">--请选择--</form:option>
	                       <c:forEach var="${fkPojoClassVar}" items="${"$"}{${fkPojoClassVar}s}">
	                       <form:option value="${'$'}{${fkPojoClassVar}.${fkSqlTable.idColumn.columnNameLower}}">${'$'}{${fkPojoClassVar}.${fkSqlTable.idColumn.columnNameLower}}</form:option>
	                       </c:forEach>
                       </form:select>
                       <form:errors path="${column.columnNameLower}"/>
                     </td>
                     </tr>
                       					<#assign columnHasForeignKey="1"/>
                                           </#if>
                                   </#list>
                               </#list>
                               <#if columnHasForeignKey=="0">
                     <tr>
	                     <td class="tdLeft">${column.columnAlias}<#if !column.nullable><font color="red">*</font></#if>:</td>
					     <td class="tdRight"> 
		                     <form:input type="text" path="${column.columnNameLower}"<#if column.isDateTimeColumn> onfocus="WdatePicker()" readonly="readonly" class="Wdate"</#if>/>
		                     <form:errors path="${column.columnNameLower}"/>
	                     </td>
                     </tr>
                      			</#if>
                  		</#if>
                   </#list>
              </table>
    </form:form>
    <br>
    </body>
</html>