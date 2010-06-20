package org.ertuo.linliqin.biz.excel;

import org.ertuo.linliqin.domain.hr.Hr;

import junit.framework.TestCase;

public class DomainTest extends TestCase {

	void test_generate_view(){
		Hr hr=new Hr()
		hr.getProperties().each{
			println(""" 
					<p>
                    <label class="label" for="login">${it.key}:</label>
                    <input type="text" name="${it.key}" value="{fieldValue(bean:hr,field:'${it.key}')}"/>
                <div class="msg show-error">
				<p class="error"><g:renderErrors bean="${hr}" field="${it.key}"/></p>
			</div>
			</p>""")
		}
	}
}
