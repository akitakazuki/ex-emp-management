package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

@Controller
@RequestMapping("/")
public class AdministratorController {
	
	@Autowired
	private AdministratorService administratorService;
	private HttpSession session;
	
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	
	//ログイン画面
	@RequestMapping("/login")
	public String login(LoginForm loginform,Model model) {
		Administrator administrator= administratorService.login(loginform.getMailAddress(), loginform.getPassword());
		
		if(administrator==null) {
			model.addAttribute("message", "メールアドレスまたはパスワードが不正です。");
			return "administrator/login";
		}else {
			session.setAttribute("administratorName",administrator.getName());
			return "forward:/employee/showList";
		}
	}
	
	@RequestMapping("/")
	public String toLogin() {
		return "/administrator/login";
	}
	
	
	
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();	
	}
	//登録画面
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(form, administrator);
		administratorService.insert(administrator);
		return "toInsert";	
	}
	//登録画面リダイレクト
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "/administrator/insert";
		
	}
}
