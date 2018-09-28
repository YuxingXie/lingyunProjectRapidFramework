<#include "/custom.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
<#assign pkJavaType = table.idColumn.javaType>
<#assign pkColumnName = table.idColumn.columnName?uncap_first>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ page language="java" import="com.gzzn.model.sys.*"%>
<%@ page language="java" import="com.gzzn.project.webUtil.CommonFiled"%>
<%@ page language="java" import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="p" uri="/pageTag"%>
<%

    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String powerStr = (String)session.getAttribute(CommonFiled.SESSION_LOGIN_USER_PERMISSION);
    String mCode =  "1011";//本菜单模块编号
%>
 <html>
    <head><meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title></title>
        <base href="http://localhost:80/wlw/">
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jQuery/jquery-1.8.3.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/option.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                $("[name='delete']").click(function(){
                    var url=$(this).attr("url");
                    if (confirm("确认删除吗？")){
                        window.location.href=url;
                    }
                });
                $("[name='batchDelete']").click(function(){
                    var items=new Array();
                    $("[name='items']:checked").each(function(){
                        items.push($(this).val());
                    });
                    if(items.length==0){
                        alert("请选择需要删除的条目!");
                        return;
                    }
                    var url=$(this).attr("url")+"&items="+items;
                    if (confirm("确认删除吗？")){
                       window.location.href(url);
                    }
                });
                $("[name='selectAll']").click(function(){
                    if($("[name='items']:checked").length<$("[name='items']").length){
                        $("[name='items']").attr("checked","checked");
                    }else if($("[name='items']:checked").length==$("[name='items']").length){
                        $("[name='items']").removeAttr("checked");
                    }
                });
            });
        </script>
        <style type="text/css">
            <!--
            body {
                margin-left: 0px;
                margin-top: 10px;
            }
            -->
        </style></head>

    <body>
    <form name="${classNameLower}SearchForm" id="${classNameLower}SearchForm" method="post" action="<%=request.getContextPath()%>/${moduleName}/search.${springMVCUrlMapping}" >
        <input id="curPage" name="curPage" type="hidden" value="1">
        <table width="1050" border="0" cellpadding="0" cellspacing="0" style= "word-break:keep-all">
            <tr>
                <td width="13" ><img src="images/k0.gif" width="13" height="37"></td>
                <td width="154" class="c4">用户管理</td>
                <td width="57" background="images/bg_k2.gif"><img src="images/k1.gif" width="39" height="37"></td>
                <td width="998" background="images/bg_k2.gif">                            <div align="right">
                    <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td width="76%">
                                            <label>关键字:</label><input name="key" type="text" id="key" value="">
                                     <#list table.columns as column>
                                        <#if !column.pk>
                                            <label>${column.columnAlias}</label>:<p:search entityClass="${basepackage}.${moduleName}.domain.${className}" fieldName="${column.columnNameLower}"/>
                                        </#if>
                                    </#list>
                                    <input name="${classNameLower}SearchBtn" type="submit" class="btn" value="查询"/>

                            </td>
                            <td>
                                <div align="right" class="line2">
                                    <input name="new5" type="button" class="btn" value="新建" onClick="javascript:window.location.href('<%=request.getContextPath()%>/${moduleName}/create_input.${springMVCUrlMapping}');" style="display:inline;">
                                    <input name="batchDelete" type="button" class="btn" value="删除" url="<%=request.getContextPath()%>/${moduleName}/${"$"}{${classNameLower}.${pkColumnName}}?_method=delete" style="display:inline;">
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
                </td>
                <td width="39" background="images/bg_k2.gif">
                    <div align="right"><img src="images/k2.gif" width="17" height="37"></div>                    </td>
            </tr>
        </table>
        <table width="1050" border="0" cellpadding="1" cellspacing="0" bordercolor="AEC2D5" class="list" >

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
                <tr style="display:inline">
                    <td colspan="${table.columns?size}" align="right" class="line2">
                        <p:PageTag
                                isDisplayGoToPage="true" isDisplaySelect="false"
                                totalPages='${"$"}{page.lastPageNumber}'
                                currentPage='${"$"}{empty param.page ? 1 : param.page}'
                                totalRecords='${"$"}{page.totalCount}'
                                ajaxUrl='<%=request.getContextPath()%>/${moduleName}/list.${springMVCUrlMapping}'
                                frontPath='' displayNum='5' />
                    </td>
                    <td class="line2"><span style="cursor:pointer" name="selectAll">全选/取消</span> </td>
                </tr>
        </table>
    </form>
    </body>
    </html>

