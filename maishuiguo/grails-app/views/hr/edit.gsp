<html>
	<head>
		<title>录入人员信息</title>
		<meta name="layout" content="main" />
	</head>
	<style type="text/css" media="screen">
		.reg-form li.field {
		border:1px solid
		white;
		height:46px;
		padding:8px 14px 4px;
		}
	  </style>

	<body>
		<g:set var="pt" value="${applicationContext.getBean('pullTool')}"></g:set>


		<form action="edit" method="post" enctype="multipart/form-data">
		    <div>
			<div class="abc">
				<span class="gaia ops gsl">
					个人基本信息
 	 			</span>
 	 			<g:hiddenField name="hrId" value="${fieldValue(bean:hr,field:'hrId')}" />
				<p>
					<label class="label" for="login">姓名:</label>
					<input type="text" name="name" value="${fieldValue(bean:hr,field:'name')}" />
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="name" />
						</p>
					</div>
				</p>
				<p>
					<label class="label" for="login">籍贯:</label>
					<input type="text" name="nation"
						value="${fieldValue(bean:hr,field:'nation')}" />
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="nation" />
						</p>
					</div>
				</p>
				<p>
					<label class="label" for="login">出生日期:</label>
					<g:datePicker name="age" precision="day" years="${1960..2020}"
						noSelection="['':'select']" value="${hr?.age}"/>
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="age" />
						</p>
					</div>
				</p>
				<p>
					<label class="label" for="login">身份证:</label>
					<input type="text" name="identity"
						value="${fieldValue(bean:hr,field:'identity')}" />
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="identity" />
						</p>
					</div>
				</p>
				<p>
					<label class="label" for="login">毕业时间:
				</label>
					<g:datePicker name="graduationDate" precision="day" years="${1960..2020}"
						noSelection="['':'select']" value="${hr?.graduationDate}"/>	
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="graduationDate" />
						</p>
					</div>
				</p>
				<p>
					<label class="label" for="login">毕业学校:
				</label>
					<input type="text" name="graduationSchool"
						value="${fieldValue(bean:hr,field:'graduationSchool')}" />
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="graduationSchool" />
						</p>
					</div>
				</p>
				<p>
					<label class="label" for="login">政治面貌:
				</label>
					<g:select name="politicalLandscape" from="${pt.politicalLandscapes}"
						value="${fieldValue(bean:hr,field:'politicalLandscape')}"
						optionValue="value" optionKey="key" />
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="politicalLandscape" />
						</p>
					</div>
				</p>



				<p>
					<label class="label" for="login">教育程度:</label>
					<g:select name="education" from="${pt.educations}"
						value="${fieldValue(bean:hr,field:'education')}" optionValue="value"
						optionKey="key" />
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="education" />
						</p>
					</div>
				</p>


				<p>
					<label class="label" for="login">性别:</label>
					<g:select name="education" from="${pt.sexs}"
						value="${fieldValue(bean:hr,field:'sex')}" optionValue="value"
						optionKey="key" />
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="sex" />
						</p>
					</div>
				</p>
				<p>
					<label class="label" for="login">婚姻状态:</label>
					<g:select name="marriage" from="${pt.marriages}"
						value="${fieldValue(bean:hr,field:'marriage')}" optionValue="value"
						optionKey="key" />
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="marriage" />
						</p>
					</div>
				</p>

				<p>
					<label class="label" for="login">专业:</label>
					<input type="text" name="professional"
						value="${fieldValue(bean:hr,field:'professional')}" />
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="professional" />
						</p>
					</div>
				</p>
				<p>
					<label class="label" for="login">学位:</label>
					<g:select name="degree" from="${pt.degrees}"
						value="${fieldValue(bean:hr,field:'degree')}" optionValue="value"
						optionKey="key" />
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="degree" />
						</p>
					</div>
				</p>
				<p>
					<label class="label" for="login">联系电话:</label>
					<input type="text" name="tel" value="${fieldValue(bean:hr,field:'tel')}" />
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="tel" />
						</p>
					</div>
				</p>
				<p>
					<label class="label" for="login">图片:</label>
					<input type="file" name="myFile"/>
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="name" />
						</p>
					</div>
				</p>


				
			</div>
			<div class="abc">

				<span class="gaia ops gsl">
					工作信息
  		</span>
				<p>
					<label class="label" for="login">岗位:</label>
					<input type="text" name="post" value="${fieldValue(bean:hr,field:'post')}" />
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="post" />
						</p>
					</div>
				</p>
				<p>
					<label class="label" for="login">人员类别:
				</label>
					<input type="text" name="categoriesPersonnel"
						value="${fieldValue(bean:hr,field:'categoriesPersonnel')}" />
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="categoriesPersonnel" />
						</p>
					</div>
				</p>

				<p>
					<label class="label" for="login">离职时间:
				</label>
					<g:datePicker name="separationDate" precision="day" years="${1960..2020}"
						noSelection="['':'select']" value="${hr?.separationDate}"/>	
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="separationDate" />
						</p>
					</div>
				</p>
				<p>
					<label class="label" for="login">获得职称时间:</label>
					<g:datePicker name="titleDate" precision="day" years="${1960..2020}"
						noSelection="['':'select']" value="${hr?.titleDate}"/>		
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="titleDate" />
						</p>
					</div>
				</p>

				<p>
					<label class="label" for="login">参加工作时间:</label>
					<g:datePicker name="jobDate" precision="day" years="${1960..2020}"
						noSelection="['':'select']" value="${hr?.jobDate}"/>	
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="jobDate" />
						</p>
					</div>
				</p>



				<p>
					<label class="label" for="login">用工形式:
				</label>
					<g:select name="employmentStatus" from="${pt.employmentStatuss}"
						value="${fieldValue(bean:hr,field:'employmentStatus')}"
						optionValue="value" optionKey="key" />
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="employmentStatus" />
						</p>
					</div>
				</p>

				<p>
					<label class="label" for="login">离职原因:
				</label>
					<input type="text" name="separationReson"
						value="${fieldValue(bean:hr,field:'separationReson')}" />
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="separationReson" />
						</p>
					</div>
				</p>
				<p>
					<label class="label" for="login">实习期:</label>
					<g:datePicker name="practiceDate" precision="day" years="${1960..2020}"
						noSelection="['':'select']" value="${hr?.practiceDate}"/>	
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="practiceDate" />
						</p>
					</div>
				</p>

				<p>
					<label class="label" for="login">聘任时间:
				</label>
					
					<g:datePicker name="appointmentDate" precision="day" years="${1960..2020}"
						noSelection="['':'select']" value="${hr?.appointmentDate}"/>	
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="appointmentDate" />
						</p>
					</div>
				</p>

				<p>
					<label class="label" for="login">工种:</label>
					<g:select name="jobs" from="${pt.jobss}"
						value="${fieldValue(bean:hr,field:'jobs')}" optionValue="value"
						optionKey="key" />
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="jobs" />
						</p>
					</div>
				</p>
				<p>
					<label class="label" for="login">转正时间:
				</label>
					
					<g:datePicker name="regularizationDate" precision="day" years="${1960..2020}"
						noSelection="['':'select']" value="${hr?.regularizationDate}"/>	
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="regularizationDate" />
						</p>
					</div>
				</p>


				<p>
					<label class="label" for="login">学历年限:</label>
					<input type="text" name="educationDate"
						value="${fieldValue(bean:hr,field:'educationDate')}" />
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="educationDate" />
						</p>
					</div>
				</p>

				<p>
					<label class="label" for="login">编号:</label>
					<input type="text" name="hrNo" value="${fieldValue(bean:hr,field:'hrNo')}" />
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="hrNo" />
						</p>
					</div>
				</p>

				<p>
					<label class="label" for="login">职称:</label>
					<g:select name="title" from="${pt.titles}"
						value="${fieldValue(bean:hr,field:'title')}" optionValue="value"
						optionKey="key" />
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="title" />
						</p>
					</div>
				</p>
				<p>
					<label class="label" for="login">行政级别:
				</label>
					<input type="text" name="executiveLevel"
						value="${fieldValue(bean:hr,field:'executiveLevel')}" />
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="executiveLevel" />
						</p>
					</div>
				</p>

				<p>
					<label class="label" for="login">部门:</label>
					<input type="text" name="department"
						value="${fieldValue(bean:hr,field:'department')}" />
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="department" />
						</p>
					</div>
				</p>


				<p>
					<label class="label" for="login">退休时间:
				</label>
					
					<g:datePicker name="retirementDate" precision="day" years="${1990..2020}"
						noSelection="['':'select']" value="${hr?.retirementDate}"/>		
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="retirementDate" />
						</p>
					</div>
				</p>
				<p>
					<label class="label" for="login">入职时间:</label>
					
					<g:datePicker name="entryDate" precision="day" years="${1990..2020}"
						noSelection="['':'select']" value="${hr?.entryDate}"/>	
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="entryDate" />
						</p>
					</div>
				</p>
			</div>
			<div class="abc">
				<span class="gaia ops gsl">
					工资信息
  		</span>



				<p>
					<label class="label" for="login">教龄补贴:
				</label>
					<input type="text" name="senioritySubsidies"
						value="${fieldValue(bean:hr,field:'senioritySubsidies')}" />
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="senioritySubsidies" />
						</p>
					</div>
				</p>




				<p>
					<label class="label" for="login">在岗补贴:
				</label>
					<input type="text" name="onJobSubsidies"
						value="${fieldValue(bean:hr,field:'onJobSubsidies')}" />
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="onJobSubsidies" />
						</p>
					</div>
				</p>
				<p>
					<label class="label" for="login">薪级工资:
				</label>
					<input type="text" name="salaryLevelWage"
						value="${fieldValue(bean:hr,field:'salaryLevelWage')}" />
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="salaryLevelWage" />
						</p>
					</div>
				</p>



				<p>
					<label class="label" for="login">电话补贴:
				</label>
					<input type="text" name="phoneSubsidies"
						value="${fieldValue(bean:hr,field:'phoneSubsidies')}" />
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="phoneSubsidies" />
						</p>
					</div>
				</p>
				<p>
					<label class="label" for="login">岗位工资:</label>
					<input type="text" name="postWage"
						value="${fieldValue(bean:hr,field:'postWage')}" />
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="postWage" />
						</p>
					</div>
				</p>


				<p>
					<label class="label" for="login">岗位补贴:</label>
					<input type="text" name="postSubsidy"
						value="${fieldValue(bean:hr,field:'postSubsidy')}" />
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="postSubsidy" />
						</p>
					</div>
				</p>
				<p>
					<label class="label" for="login">薪级:</label>
					<input type="text" name="salaryLevel"
						value="${fieldValue(bean:hr,field:'salaryLevel')}" />
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="salaryLevel" />
						</p>
					</div>
				</p>
				<p>
					<label class="label" for="login">综合补贴:
				</label>
					<input type="text" name="consolidatedSubsidies"
						value="${fieldValue(bean:hr,field:'consolidatedSubsidies')}" />
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="consolidatedSubsidies" />
						</p>
					</div>
				</p>

				<p>
					<label class="label" for="login">交通补贴:
				</label>
					<input type="text" name="trafficSubsidies"
						value="${fieldValue(bean:hr,field:'trafficSubsidies')}" />
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="trafficSubsidies" />
						</p>
					</div>
				</p>
				<p>
					<label class="label" for="login">生活补贴:</label>
					<input type="text" name="lifeSubsidies"
						value="${fieldValue(bean:hr,field:'lifeSubsidies')}" />
					<div class="errormsg">
						<p class="error">
							<g:renderErrors bean="${hr}" field="lifeSubsidies" />
						</p>
					</div>
				</p>
			</div>
			
			<div class="buttomDiv">
				<input type="SUBMIT" value="提交。" name="submitbutton" id="submitbutton" style="width: 10em;">
			</div>
			</div>

		</form>

	</body>
</html>
