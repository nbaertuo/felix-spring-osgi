
<div style="display:none">
<form method="post" action="https://login.taobao.com/member/login.jhtml" id="J_StaticForm" name="J_StaticForm" target="_blank">
		<div class="field">
			<label>账户名</label>
			<input type="text" tabindex="1" maxlength="32" value="nbaertuo" title="手机号/会员名/邮箱" class="login-text J_UserName J_Focused" id="TPL_username_1" name="TPL_username">
		</div>
		<div class="field">
			<label>密　码</label>
			<span id="J_StandardPwd" style="">
				<input type="hidden" value="keyiDax1e" tabindex="2" maxlength="20" class="login-text" name="TPL_password" id="TPL_password">
			</span>
			<span style="display: none;" class="password-edit" id="J_PasswordEdit">						
				<object width="190" height="26" codebase="https://img.alipay.com/download/2121/aliedit.cab#Version=2,1,2,1" classid="clsid:488A4255-3236-44B3-8F27-FA1AECAA8844" tabindex="3" id="Password_Edit_IE">
					<param value="0613110323" name="cm5ts">
					<param value="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDS92pDVyWNT7dzG9zH0opH44z9FayCZTX5iqGUxUjPi667IkyaqrsmDPqKsJp47lJ29lzs+Qv8zjPPdmnxjFteMrfpc4ui24gL1iZnchwX87Ox/+Xrm8HFmKlhmUO9n/QgTT+Nz1RGMEN1+HijvsoAhS0TS8XjSfzRkrwvK2pJQIDAQAB" name="cm5pk">
					<param value="4" name="CryptoMode">
					<embed width="190" height="26" tabindex="2" id="Password_Edit_NoIE" type="application/aliedit">
				</object>
			</span>				
			<strong style="display: none;" class="warning-tip" id="J_CapsLockTip">Caps Lock键正处于启用状态，<br>启用它可能导致密码输入错误。</strong>
		</div>
				<div class="safe">							
			<input type="checkbox" checked="checked" id="J_SafeLoginCheck"><label for="J_SafeLoginCheck">安全控件登录</label>
			<span id="J_LongLogin_l1" class="long-login" style="visibility: visible;">
				<input type="checkbox" class="J_LognLoginInput" id="J_LongLogin_1"><label for="J_LongLogin_1">两周内免登录</label>
			</span>
			<a href="http://service.taobao.com/support/help-11985.htm" target="_blank" title="去看看什么是免登录" id="J_LongLogin_l11" class="long-login-help" style="visibility: visible;">帮助</a>
			<div style="visibility: hidden;" class="login-tips">
				为了账户安全，请勿在公用电脑上勾选此项。
			</div>							
		</div>						
		<div class="submit">
			<input type="hidden" value="6e3e56d67eb5" name="_tb_token_">
			<input type="hidden" value="Authenticator" name="action">
			<input type="hidden" value="anything" name="event_submit_do_login">
						<input type="hidden" value="http://www.taobao.com/" name="TPL_redirect_url">											
			<input type="hidden" value="tbTop" name="from">
			<input type="hidden" value="2" name="fc">
			<input type="hidden" value="default" name="style">
						<input type="hidden" name="tid">
			<input type="hidden" value="000001" name="support">
			<input type="hidden" value="1,0,0,7" name="CtrlVersion">	
			<input type="hidden" value="3" name="loginType">
			<input type="hidden" value="" name="minititle">
			<input type="hidden" value="" name="minipara">
						<input type="hidden" value="" name="pstrong">
			<input type="hidden" value="-1" id="longLogin1" name="longLogin">
			<input type="hidden" value="" id="llnick1" name="llnick">												
						<input type="hidden" value="" name="sign">
			<input type="hidden" value="" name="need_sign">			
			<input type="hidden" value="" name="isIgnore">
						<input type="hidden" value="" name="popid">
			<input type="hidden" value="" name="callback">

						<input type="hidden" value="" name="not_duplite_str">				
			<input type="hidden" value="" name="need_user_id">	
			
						<input type="hidden" value="" name="from_encoding">	
			<button style="" tabindex="3" type="submit">无密登录</button>
			<input type="submit" value="提交" name="xxx" id="taobao_tijiao" >
		</div>
		
	</form>
</div>	
	<script>
	function taobao_login(us,pw,id){
		document.getElementById('TPL_username_1').value=us;
		document.getElementById('TPL_password').value=pw;	
		document.getElementById(id).style.background="greenyellow";
		J_StaticForm.submit()
	}
	</script>
