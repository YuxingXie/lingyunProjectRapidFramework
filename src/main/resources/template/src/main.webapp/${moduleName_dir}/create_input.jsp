<#include "/custom.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
<#assign pkJavaType = table.idColumn.javaType>
<#assign pkColumnName = table.idColumn.columnName?uncap_first>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page language="java" import="com.gzzn.model.sys.*"%>
<%@ page language="java" import="com.gzzn.project.webUtil.CommonFiled"%>
<%@ page language="java" import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="p" uri="/pageTag"%>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<jsp:useBean id="${classNameLower}" class="${basepackage}.${moduleName}.domain.${className}" scope="request"></jsp:useBean>

<%
String path= request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title></title>
        <base href="<%=basePath%>">
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <script language="javascript" src="${"$"}{path}/js/string.js"></script>
        <script language="javascript" src="${"$"}{path}/js/option.js"></script>
        <script type="text/javascript" src="${"$"}{path}/js/calender.js"></script>
        <script type="text/javascript" src="${"$"}{path}/js/public.js"></script>
        <script type="text/javascript" src="${"$"}{path}/js/My97DatePicker/WdatePicker.js"></script>
        <script language="javascript">
        </script>
        <style type="text/css">
            <!--
            body {
                margin-left: 0px;
                margin-top: 10px;
            }
            -->
        </style>
    </head>
    <body>
    <form action="${"$"}{path}/${moduleName}/new.${springMVCUrlMapping}" method="post"  commandName="${classNameLower}" >
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
        <table width="1050"  border="0" cellpadding="10" class="ctable">
            <tr>
                <td>
                    <table width="100%" border="0" align="center" cellpadding="2" cellspacing="0" bordercolor="AEC2D5">
                        <#list table.columns as column>
                            <#if !column.pk >
                                <#list table.importedKeys.associatedTables?values as foreignKey>
                                    <#assign fkSqlTable = foreignKey.sqlTable>
                                     <#assign fkTable	= fkSqlTable.className>
                                     <#assign fkPojoClass = fkSqlTable.className>
                                     <#assign fkPojoClassVar = fkPojoClass?uncap_first>
                                    <#list foreignKey.parentColumns?values as fkColumn>
                                        <tr>
                                            <td align="right" valign="middle" style="font-weight:bold">
                                                <#if fkColumn?replace("_","")?lower_case==column.columnNameLower?lower_case>
                                                    <p:select entityClass="${basepackage}.${moduleName}.domain.${fkPojoClass}" fieldName="${column.columnNameLower}" />
                                                </#if>
                                                </td>
                                            </tr>

                                    </#list>
                                </#list>
                                <tr>
                                    <td align="right" valign="middle" style="font-weight:bold">
                                        ${column.columnAlias}<#if !column.nullable><font color="red">*</font></#if>:
                                       </td>
                                    <td>
                                        <spring:bind path="${classNameLower}.${column.columnNameLower}">
                                            <input type="text" name="${column.columnNameLower}"<#if column.isDateTimeColumn> onfocus="WdatePicker()" readonly="readonly" class="Wdate"</#if>/>
                                            <c:forEach items="${'$'}{status.errorMessages}" var="error">
                                                <c:out value="${'$'}{error}"/>
                                            </c:forEach>
                                        </spring:bind>
                                    </td>
                                </tr>
                            </#if>
                        </#list>
                    </table>
                </td>
            </tr>
        </table>

        <!--view-->

    </form>
    <br>
    </body>
</html>