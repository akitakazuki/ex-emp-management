package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
	
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	
	
	/**
	 * @param loginform
	 * @param model
	 * @return　ログイン成功でemployee/showListへフォワード
	 * @return　失敗時はadministrator/loginへフォワード
	 */
	@RequestMapping("/login")
	public String login(@Validated LoginForm loginform,BindingResult result,Model model) {
		Administrator administrator= administratorService.login(loginform.getMailAddress(), loginform.getPassword());
		
		if (result.hasErrors() || administrator==null) {
			model.addAttribute("message", "メールアドレスまたはパスワードが不正です。");
			return  "administrator/login";
		}
//		if(administrator==null) {
//			model.addAttribute("message", "メールアドレスまたはパスワードが不正です。");
//			return "administrator/login";
//		}
			session.setAttribute("administratorName",administrator.getName());
			return "forward:employee/showList";
		
	}
	
	@RequestMapping("/")
	public String toLogin() {
		return "/administrator/login";
	}
	
	
	
	/**
	 * @return　
	 */
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();	
	}
	
	
	/**
	 * @param form
	 * @return　/
	 */
	@RequestMapping("/insert")
	public String insert(@Validated InsertAdministratorForm form,BindingResult result) {
		
		if(result.hasErrors()) {
			return"administrator/insert";
		}
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(form, administrator);
		administrator.setName(form.getName());
		administratorService.insert(administrator);
		return "redirect:/";	
	}
	
	
	 //登録フォームへ
	@RequestMapping("/toInsert")
	public String toInsert(Model model) {
		return "/administrator/insert";
	}
	
	
	/**
	 * sessionスコープ準備
	 */
	@Autowired
	private HttpSession session;
	
	
	/**
	 * @return ログアウト画面へリダイレクト
	 */
	@RequestMapping("/logout")
 	public String logout() {
		session.invalidate();
		return "redirect:/";
 	}
}
