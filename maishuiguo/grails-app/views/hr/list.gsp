
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
         <meta name="layout" content="main" />
         <title>职工列表</title>
    </head>
    <body>
        <div class="body">
           		<span class="gaia ops gsl">
					职工列表
 	 			</span>
            <g:if test="${flash.message}">
                 <div class="message">
                       ${flash.message}
                 </div>
            </g:if>
           <table>
               <tr>
                   
                                      
                        <th>姓名</th>
                                      
                        <th>出身日期</th>
                                      
                        <th>籍贯</th>
                   
                   <th></th>
               </tr>
               <g:each in="${hrs}">
                    <tr>
                       
                            <td>${it.name}</td>
                       
                            <td><g:formatDate format="yyyy-MM-dd" date=${it.birthplace}/></td>
                       
                            <td>${it.nation}</td>
                       
                       <td class="actionButtons">
                            <span class="actionButton"><g:link action="edit" id="${it.hrId}">编辑</g:link></span>
                       </td>
                    </tr>
               </g:each>
           </table>
        </div>
    </body>
</html>
            