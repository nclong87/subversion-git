<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String contextPath=getServletContext().getContextPath();
%>
<input type="hidden" id="rspageend" value="<s:property value="strpageend" />" />
<table class="tableContainer">
	<thead>
		<tr>
			<td colspan="6" align="center" bgcolor="white" style="color:black">
				<a class="link" style="padding-right:5px" href="javascript:selectpages(1)">Begin</a>
				<s:iterator status="stat" value="(ipagesindex-ipagesbefore).{ #this }" >
				   <a class="link" href="javascript:selectpages('<s:property value="ipagesbefore + #stat.count -1" />')"><s:property value="ipagesbefore + #stat.count - 1" /> </a>
				</s:iterator>
				<span style="font-weight:bold;color:red"><s:property value="ipagesindex" /></span>
				<s:iterator status="stat" value="ipagesnext.{ #this }" >
				   <a class="link" href="javascript:selectpages('<s:property value="ipagesindex + #stat.count" />')"><s:property value="ipagesindex+#stat.count" /> </a>
				</s:iterator>
				<s:if test="strpageend.isEmpty()==false" >
					<a class="link" style="padding-left:5px" href="javascript:selectpages('<s:property value="strpageend" />')">...<s:property value="strpageend" /></a>
				</s:if>
				<s:else>
					<a class="link" style="padding-left:5px" href="javascript:selectpages('end')">End</a>
				</s:else>				
			</td>
		</tr>
		<tr>
			<td>#</td>
			<td>ID</td>
			<td>User Name</td>
			<td>First Name</td>
			<td>Last Name</td>
			<td>Birth Day</td>
			<td>Action</td>
		</tr>
	</thead>	
	<tfoot >
		<tr>
			<td colspan="6" align="center" style="font-weight:bold">
				<a class="link" style="padding-right:5px" href="javascript:selectpages(1)">Begin</a>
				<s:iterator status="stat" value="(ipagesindex-ipagesbefore).{ #this }" >
				   <a class="link" href="javascript:selectpages('<s:property value="ipagesbefore + #stat.count -1" />')"><s:property value="ipagesbefore + #stat.count - 1" /> </a>
				</s:iterator>
				<span style="font-weight:bold;color:red"><s:property value="ipagesindex" /></span>
				<s:iterator status="stat" value="ipagesnext.{ #this }" >
				   <a class="link" href="javascript:selectpages('<s:property value="ipagesindex + #stat.count" />')"><s:property value="ipagesindex+#stat.count" /> </a>
				</s:iterator>
				<s:if test="strpageend.isEmpty()==false" >
					<a class="link" style="padding-left:5px" href="javascript:selectpages('<s:property value="strpageend" />')">...<s:property value="strpageend" /></a>
				</s:if>
				<s:else>
					<a class="link" style="padding-left:5px" href="javascript:selectpages('end')">End</a>
				</s:else>
			</td>
		</tr>		
	</tfoot>
	<tbody>
		<s:iterator value="persons" status="stat">
			<s:if test="#stat.odd == true">
				<tr class="alternateRow">
			</s:if>
			<s:else>
				<tr class="normalRow">
			</s:else>
					<td><s:property value="#stat.index+1" /></td>
					<td><s:property value="account.id" /></td>
					<td><s:property value="account.username" /></td>
					<td><s:property value="firstname" /></td>
					<td><s:property value="lastname" /></td>
					<td><s:property value="birthday" /></td>
					<td width="60px">
						<img src="<%=contextPath %>/images/edit.png" style="cursor:pointer;float:left" onclick="Edit(<s:property value="account.id" />);" width="20px" alt="Edit" title="Edit" />
						<img src="<%=contextPath %>/images/delete.png" style="cursor:pointer;float:right" onclick="Delete(<s:property value="account.id" />);" width="20px" alt="Delete" title="Delete" />
					</td>
		</s:iterator>
	</tbody>			
</table>